package org.example.repository;

//import org.example.api.ConsoleApplication;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    private final SessionFactory sessionFactory;
//    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
//
//    public UserRepository(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    public User save(User user) {
//        Transaction transaction = null;
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//            session.save(user);
//            transaction.commit();
//            logger.info("User saved successfully: {}", user.getEmail());
//            return user;
//        } catch (Exception e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
//            logger.error("Error saving user: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to save user", e);
//        }
//    }
//
//    public Optional<User> findById(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            User user = session.get(User.class, id);
//            return Optional.ofNullable(user);
//        } catch (Exception e) {
//            logger.error("Error finding user by id {}: {}", id, e.getMessage(), e);
//            throw new RuntimeException("Failed to find user by id", e);
//        }
//    }
//
//    public List<User> findAll() {
//        try (Session session = sessionFactory.openSession()) {
//            Query<User> query = session.createQuery("FROM User ORDER BY id", User.class);
//            return query.list();
//        } catch (Exception e) {
//            logger.error("Error finding all users: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to find all users", e);
//        }
//    }
//
//    public User update(User user) {
//        Transaction transaction = null;
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//            session.update(user);
//            transaction.commit();
//            logger.info("User updated successfully: {}", user.getEmail());
//            return user;
//        } catch (Exception e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
//            logger.error("Error updating user: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to update user", e);
//        }
//    }
//
//    public void delete(Long id) {
//        Transaction transaction = null;
//        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//            User user = session.get(User.class, id);
//            if (user != null) {
//                session.delete(user);
//                logger.info("User deleted successfully: {}", id);
//            } else {
//                logger.warn("User not found for deletion: {}", id);
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null && transaction.isActive()) {
//                transaction.rollback();
//            }
//            logger.error("Error deleting user: {}", e.getMessage(), e);
//            throw new RuntimeException("Failed to delete user", e);
//        }
//    }
//
//    public Optional<User> findByEmail(String email) {
//        try (Session session = sessionFactory.openSession()) {
//            Query<User> query = session.createQuery(
//                    "FROM User WHERE email = :email", User.class);
//            query.setParameter("email", email);
//            return query.uniqueResultOptional();
//        } catch (Exception e) {
//            logger.error("Error finding user by email {}: {}", email, e.getMessage(), e);
//            throw new RuntimeException("Failed to find user by email", e);
//        }
//    }

}
