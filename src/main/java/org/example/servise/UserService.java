package org.example.servise;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.dto.UserEvent;
import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.entity.User;
import org.example.kafka.UserEventProducer;
import org.example.mapper.UserMapper;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserEventProducer eventProducer;


    public UserService(UserRepository userRepository, UserEventProducer eventProducer) {
        this.userRepository = userRepository;
        this.eventProducer = eventProducer;
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        logger.info("Creating user: {}", userRequest.getEmail());
        User user = UserMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);
        logger.info("User created successfully with id: {}", savedUser.getId());
        //ДОБАВИЛ КАФКУ ДЛЯ ОТПРАВИ УВЕДОМЛЕНИЯ ПРИ СОЗДАНИЕ
        eventProducer.sendUserEvent(new UserEvent(savedUser.getEmail(), "CREATED"));

        return UserMapper.toResponse(savedUser);
    }

    @Transactional
    public UserResponse getUserById(Long id) {
        logger.info("Called getReservationById: id={}", id);
        User foundedUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Not found user by id =" + id
                ));
        logger.info("Founded user with id: {}", id);
        return UserMapper.toResponse(foundedUser);

    }

    @Transactional
    public List<UserResponse> getAllUser() {
        logger.info("Called getAllUser");
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        logger.info("Updating user with id: {}", id);
        User foundedUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found user by id =" + id));
        UserMapper.updateEntity(foundedUser, userRequest);
        User updated = userRepository.save(foundedUser);
        logger.info("User updated successfully with id: {}", foundedUser.getId());
        return UserMapper.toResponse(updated);
    }

    @Transactional
    public void deleteUser(Long id) {
        User foundedUser = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Not found user by id =" + id));
        logger.info("User deleted successfully with id: {}", id);
        eventProducer.sendUserEvent(new UserEvent(foundedUser.getEmail(), "DELETED"));

        userRepository.deleteById(id);
    }

//
//    public UserResponse createUser(UserRequest userRequest) {
//        logger.info("Creating user: {}", userRequest.getEmail());
//        // Валидация
//        validateCreateUserData(userRequest.getName(), userRequest.getEmail(), userRequest.getAge());
//
//        // Бизнес-правило: проверка уникальности email
//        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
//            throw new IllegalArgumentException("User with email " + userRequest.getEmail() + " already exists");
//        }
//
//        User user = UserMapper.toEntity(userRequest);
//
//        User savedUser = userRepository.save(user);
//        logger.info("User created successfully with id: {}", savedUser.getId());
//
//        return UserMapper.toResponse(savedUser);
//    }
//
//    public UserResponse getUserById(UserRequest userRequest) {
//        logger.info("Fetching user by id: {}", userRequest.getId());
//
//        User userById = UserMapper.toEntity(userRequest);
//        userById.setId(userRequest.getId());
//
//        User findedUserById = userRepository.findById(userById.getId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userById.getId()));
//
//        return UserMapper.toResponse(findedUserById);
//    }
//
//    public List<UserResponse> getAllUsers() {
//        logger.info("Fetching all users");
//        return userRepository.findAll().stream()
//                .map(user -> {
//                    UserResponse r = new UserResponse();
//                    r.setId(user.getId());
//                    r.setName(user.getName());
//                    r.setEmail(user.getEmail());
//                    r.setAge(user.getAge());
//                    r.setCreatedAt(user.getCreated_at());
//                    return r;
//                })
//                .collect(Collectors.toList());
//    }
//
//    public UserResponse updateUser(UserRequest userRequest) {
//        logger.info("Updating user with id: {}", userRequest.getId());
//
//        // Проверка существования
//        User existingUser = userRepository.findById(userRequest.getId())
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + userRequest.getId()));
//        //Optional<User> asd1 = userRepository.findById(userRequest.getId());
//        // Валидация
//        validateUpdateUserData(userRequest.getName(), userRequest.getEmail(), userRequest.getAge());
//
//        // Бизнес-правило: email должен быть уникальным
//        if (userRequest.getEmail() != null) {
//            Optional<User> userByEmail = userRepository.findByEmail(userRequest.getEmail());
//            if (userByEmail.isPresent() && !userByEmail.get().getId().equals(userRequest.getId())) {
//                throw new IllegalArgumentException("Email " + userRequest.getEmail() + " is already taken");
//            }
//        }
//
//        UserMapper.updateEntity(existingUser, UserMapper.toEntity(userRequest));
//
//        // Сохранение
//        User updatedUser = userRepository.update(existingUser);
//        logger.info("User updated successfully: {}", updatedUser.getId());
//
//        return UserMapper.toResponse(updatedUser);
//    }
//
//    public void deleteUser(UserRequest userRequest) {
//        logger.info("Deleting user with id: {}", userRequest.getId());
//
//        // Проверка существования
//        if (!userRepository.findById(userRequest.getId()).isPresent()) {
//            throw new IllegalArgumentException("User not found with id: " + userRequest.getId());
//        }
//        //User deletedUser = UserMapper.toEntity(userRequest);
//        userRepository.delete(userRequest.getId());
//        logger.info("User deleted successfully: {}", userRequest.getId());
//    }
//    ==================================================================================================
//    ВАЛИДАЦИЯ ПРОИСХОДИТ В НУТРИ ОБЪЕКТА И СЕРВИСА ПРИ ПОМОЩЬ @Validated И @@Valid
//    ______________________________________________________________________________
//    private void validateCreateUserData(String name, String email, Integer age) {
//        if (name == null || name.trim().isEmpty()) {
//            throw new IllegalArgumentException("Name cannot be empty");
//        }
//        if (email == null || !email.contains("@")) {
//            throw new IllegalArgumentException("Invalid email format");
//        }
//        if (age == null || age < 0 || age > 150) {
//            throw new IllegalArgumentException("Age must be between 0 and 150");
//        }
//    }
//
//    private void validateUpdateUserData(String name, String email, Integer age) {
//        boolean hasValidField = false;
//
//        if (name != null && !name.isEmpty()) {
//            hasValidField = true;
//            if (name.trim().isEmpty()) {
//                throw new IllegalArgumentException("Name cannot be empty");
//            }
//        }
//
//        if (email != null && !email.isEmpty()) {
//            hasValidField = true;
//            if (!email.contains("@")) {
//                throw new IllegalArgumentException("Invalid email format");
//            }
//        }
//
//        if (age != null) {
//            hasValidField = true;
//            if (age < 0 || age > 150) {
//                throw new IllegalArgumentException("Age must be between 0 and 150");
//            }
//        }
//
//        if (!hasValidField) {
//            throw new IllegalArgumentException("At least one field (name, email, or age) must be provided for update");
//        }
//    }
//    =============================================================================================================


}