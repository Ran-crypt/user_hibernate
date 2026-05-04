//package org.example;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class HibernateConfiguration {
//
//    private static final Logger logger = LoggerFactory.getLogger(HibernateConfiguration.class);
//    private static SessionFactory sessionFactory;
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                logger.info("Initializing Hibernate SessionFactory");
//
//                // Загружаем конфигурацию из hibernate.cfg.xml
//                Configuration configuration = new Configuration();
//                configuration.configure("hibernate.cfg.xml");
//
//                sessionFactory = configuration.buildSessionFactory();
//                logger.info("Hibernate SessionFactory created successfully");
//
//            } catch (Exception e) {
//                logger.error("Failed to create SessionFactory: {}", e.getMessage(), e);
//                throw new ExceptionInInitializerError("Failed to create SessionFactory: " + e.getMessage());
//            }
//        }
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        if (sessionFactory != null && !sessionFactory.isClosed()) {
//            logger.info("Shutting down Hibernate SessionFactory");
//            sessionFactory.close();
//        }
//    }
//}