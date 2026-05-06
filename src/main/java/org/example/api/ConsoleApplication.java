package org.example.api;
import org.example.servise.UserService;

import org.example.controller.UserController;
import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleApplication.class);
    private  final UserController userController;
    private  final Scanner scanner = new Scanner(System.in);

    public ConsoleApplication(UserController userController) {
        this.userController = userController;
    }

    public void start() {
        logger.info("Starting User Controller");
        while (true) {
            try {
                displayMenu();
                int choice = readIntInput("Enter your choice: ");

                switch (choice) {
                    case 1:
                        createUser();
                        break;
                    case 2:
                        getUserById();
                        break;
                    case 3:
                        getAllUsers();
                        break;
                    case 4:
                        updateUser();
                        break;
                    case 5:
                        deleteUser();
                        break;
                    case 6:
                        logger.info("Exiting application");
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                logger.error("Error in controller: {}", e.getMessage(), e);
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== User Service Console Application ===");
        System.out.println("1. Create User");
        System.out.println("2. Get User by ID");
        System.out.println("3. Get All Users");
        System.out.println("4. Update User");
        System.out.println("5. Delete User");
        System.out.println("6. Exit");
        System.out.println("======================================");
    }

    private void createUser() {
        System.out.println("\n--- Create New User ---");
        String name = readStringInput("Enter name: ");
        String email = readStringInput("Enter email: ");
        Integer age = readIntInput("Enter age: ");

        UserRequest userRequest = new UserRequest();
        userRequest.setName(name);
        userRequest.setEmail(email);
        userRequest.setAge(age);

        UserResponse response = userController.createUser(userRequest);

        System.out.println("User created successfully: " + response);
    }

    private void getUserById() {
        System.out.println("\n--- Get User by ID ---");
        Long id = readLongInput("Enter user ID: ");

        UserRequest userRequest = new UserRequest();
        userRequest.setId(id);

        UserResponse response = userController.getUserById(userRequest);
        System.out.println("User found: " + response);
    }

    private void getAllUsers() {
        System.out.println("\n--- All Users ---");
        List<UserResponse> users = userController.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.forEach(System.out::println);
        }
    }

    private void updateUser() {
        System.out.println("\n--- Update User ---");
        Long id = readLongInput("Enter user ID to update: ");
        String name = readStringInput("Enter new name: ");
        String email = readStringInput("Enter new email: ");
        Integer age = readIntInput("Enter new age: ");

        UserRequest userRequest = new UserRequest();
        userRequest.setId(id);
        userRequest.setName(name);
        userRequest.setEmail(email);
        userRequest.setAge(age);

        UserResponse userResponse = userController.updateUser(userRequest);
        System.out.println("User updated successfully: " + userResponse);
    }

    private void deleteUser() {
        System.out.println("\n--- Delete User ---");
        Long id = readLongInput("Enter user ID to delete: ");

        UserRequest userRequest = new UserRequest();
        userRequest.setId(id);
        userController.deleteUser(userRequest);
        System.out.println("User deleted successfully!");
    }

    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private Integer readIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = scanner.nextLine().trim();

                // Пустой ввод разрешён — возвращаем null
                if (input.isEmpty()) {
                    return null;
                }

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private Long readLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }



}
