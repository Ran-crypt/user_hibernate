//package org.example;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public class HibernateUtil {
//    private static final SessionFactory sessionFactory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            // Автоматически ищет hibernate.cfg.xml в classpath
//            return new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Ошибка создания SessionFactory: " + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        getSessionFactory().close();
//    }
//}