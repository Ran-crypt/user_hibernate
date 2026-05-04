package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String name, String email, Integer age) {
        logger.info("Creating user: {}", email);

        // Валидация
        validateUserData(name, email, age);

        // Бизнес-правило: проверка уникальности email
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }

        // Создание и сохранение
        User user = new User(name, email, age);
        User savedUser = userRepository.save(user);
        logger.info("User created successfully with id: {}", savedUser.getId());

        return savedUser;
    }

    public User getUserById(Long id) {
        logger.info("Fetching user by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public User updateUser(Long id, String name, String email, Integer age) {
        logger.info("Updating user with id: {}", id);

        // Проверка существования
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        // Валидация
        validateUserData(name, email, age);

        // Бизнес-правило: email должен быть уникальным
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent() && !userByEmail.get().getId().equals(id)) {
            throw new IllegalArgumentException("Email " + email + " is already taken");
        }

        // Обновление полей
        existingUser.setName(name);
        existingUser.setEmail(email);
        existingUser.setAge(age);

        // Сохранение
        User updatedUser = userRepository.update(existingUser);
        logger.info("User updated successfully: {}", updatedUser.getId());

        return updatedUser;
    }

    public void deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);

        // Проверка существования
        if (!userRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }

        userRepository.delete(id);
        logger.info("User deleted successfully: {}", id);
    }

    private void validateUserData(String name, String email, Integer age) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (age == null || age < 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150");
        }
    }
}