package org.example;

import org.example.api.ConsoleApplication;
import org.example.controller.UserController;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    //private static final Scanner scanner = new Scanner(System.in);
    //private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public static void main(String[] args) {
        //logger.info("Starting Console Application");

        try {
            // Initialize Hibernate
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            // Initialize layers
            UserRepository userRepository = new UserRepository(sessionFactory);
            org.example.service.UserService userService = new org.example.service.UserService(userRepository);
            UserController userController = new UserController(userService);

            // Start the application
            userController.start();

        } catch (Exception e) {
            logger.error("Application error: {}", e.getMessage(), e);
            System.err.println("Failed to start application: " + e.getMessage());
        } finally {
            // Cleanup
            HibernateUtil.shutdown();
            logger.info("Application terminated");
        }
//        Это первичный вариант, оставляю чтобы мог впомнить шаги
//        boolean running = true;
//
//        while (true) {
//            printMenu();
//            String input = null;
//
//            while (true) {
//                try {
//                    input = scanner.nextLine();
//                    if (input.trim().isEmpty()) {
//                        System.out.println("Пожалуйста, введите число.");
//                        continue;
//                    }
//                    Integer.parseInt(input); // Проверка, что это число
//                    break;
//                } catch (NumberFormatException e) {
//                    System.out.println("Ошибка: введите корректное число.");
//                }
//            }
//
//            int caseNumber = Integer.parseInt(input);
//
//            switch (caseNumber) {
//                case 1:
//                    createUser();
//                    break;
//                case 2:
//                    readUser();
//                    break;
//                case 3:
//                    updateUser();
//                    break;
//                case 4:
//                    deleteUser();
//                    break;
//                case 5:
//                    listAllUsers();
//                    break;
//                case 6:
//                    System.out.println("Выход из приложения. До свидания!");
//                    scanner.close();
//                    System.exit(0);
//                    break;
//                default:
//                    System.out.println("Неверный выбор. Пожалуйста, выберите пункт от 1 до 6.");
//            }
//
//            System.out.println("\nНажмите Enter для продолжения...");
//            scanner.nextLine(); // Очистка буфера
//            scanner.nextLine(); // Ожидание Enter
        }





//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        User user1 = new User("Sergei", "serg@mail.ru", 22, LocalDateTime.now());
//
//        User user2 = new User("Asta", "стас@mail.ru", 40, LocalDateTime.now());
//
//
//        session.beginTransaction();
//
//        session.persist(user1);
//        session.persist(user2);
//
//        session.getTransaction().commit();
//
//        User userById1 = session.get(User.class, 1L);
//        System.out.println(userById1.toString());
//
//        User userById2 = (User) session.createQuery("SELECT s FROM User s WHERE s.id = :id", User.class)
//                .setParameter("id",2)
//                .getSingleResult();
//
//        System.out.println(userById2.toString());
//
//        session.beginTransaction();
//
//        User userByForUpdate = session.get(User.class, 1L);
//        userByForUpdate.setAge(66);
//        userByForUpdate.setName("Newby");
//
//        session.getTransaction().commit();
//
//        session.beginTransaction();
//
//        //User userByForDelet = session.get(User.class, 2L);
//
//        //session.remove(userByForDelet);
//
////        session.createQuery("" +
////                "DELETE FROM User u WHERE u.id = 2")
////                        .executeUpdate();
//
////        session.createNativeQuery("delete from User u where u.id = 2")
////                .executeUpdate();
//
//        session.getTransaction().commit();
//
//        List<User> users = session.createQuery("select u from User u", User.class)
//                        .list();
//
//        users.stream().forEach(user -> System.out.println(user));
//
//        User userByName = (User) session.createQuery("SELECT s FROM User s WHERE s.name = :name", User.class)
//                .setParameter("name","Newby")
//                .getSingleResult();
//
//        System.out.println(userByName);
//
//        session.close();


    }

//    public static void createUser(){
//
//        System.out.println("\n--- СОЗДАНИЕ НОВОГО ПОЛЬЗОВАТЕЛЯ ---");
//
//        System.out.print("Введите имя: ");
//        String name = scanner.nextLine();
//
//        System.out.print("Введите email: ");
//        String email = scanner.nextLine();
//
//        System.out.print("Введите возраст: ");
//        Integer age = scanner.nextInt();
//
//        User user = new User();
//        user.setName(name);
//        user.setEmail(email);
//        user.setAge(age);
//        user.setCreated_at(LocalDateTime.now());
//
//
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        session.persist(user);
//
//        session.getTransaction().commit();
//
//        session.close();
//
//        for(int i = 0; i<10; i++){
//            System.out.println(i);
//        }
//        int j = 0;
//        while (0 < 10){
//            System.out.println(j);
//            j++;
//        }
//    }
//
//    private static void readUser() {
//        System.out.println("\nВВЕДИТЕ ID ПОЛЬЗОВАТЕЛЯ");
//        Long id = scanner.nextLong();
//
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        User userById = session.get(User.class, id);
//        System.out.println("Найденный пользователь " + userById.toString());
//
//        session.getTransaction().commit();
//
//        session.close();
//
//    }
//
//    private static void updateUser() {
//        System.out.println("\nВВЕДИТЕ ID ПОЛЬЗОВАТЕЛЯ");
//        Long id = scanner.nextLong();
//
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        User userByForUpdate = session.get(User.class, id);
//        System.out.println("Введите новое имя");
//        String name = scanner.nextLine();
//        userByForUpdate.setName(name);
//        System.out.println("Введите новый эмейл");
//        String email = scanner.nextLine();
//        userByForUpdate.setEmail(email);
//        System.out.println("Введите новый возраст");
//        Integer age = scanner.nextInt();
//        userByForUpdate.setAge(age);
//        System.out.println("Пользователь обновлен " + userByForUpdate.toString());
//
//        session.getTransaction().commit();
//
//        session.close();
//        }
//
//    private static void deleteUser() {
//
//        System.out.println("\nВВЕДИТЕ ID ПОЛЬЗОВАТЕЛЯ");
//        Long id = scanner.nextLong();
//
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        User userByForDelet = session.get(User.class, id);
//
//        session.remove(userByForDelet);
//
//        System.out.println("Пользователь удален");
//
//        session.getTransaction().commit();
//
//        session.close();
//
//    }
//
//    private static void listAllUsers() {
//
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        List<User> users = session.createQuery("select u from User u", User.class)
//                        .list();
//
//        users.stream().sorted(Comparator.comparing(User::getId))
//                .forEach(user -> System.out.println(user));
//
//        session.getTransaction().commit();
//
//        session.close();
//
//    }
//
//    private static void printMenu() {
//        System.out.println("\n" + "=".repeat(50));
//        System.out.println("     USER MANAGEMENT SYSTEM");
//        System.out.println("=".repeat(50));
//        System.out.println("1. CREATE - Создать нового пользователя");
//        System.out.println("2. READ   - Найти пользователя по ID");
//        System.out.println("3. UPDATE - Обновить данные пользователя");
//        System.out.println("4. DELETE - Удалить пользователя");
//        System.out.println("5. LIST   - Показать всех пользователей");
//        System.out.println("6. EXIT   - Выход из программы");
//        System.out.println("=".repeat(50));
//    }

