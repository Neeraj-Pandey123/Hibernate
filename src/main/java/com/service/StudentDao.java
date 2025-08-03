package com.service;

import com.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Dao - Data Access Object
 * This class provides methods to perform CRUD operations on Student entities using Hibernate.
 * It includes methods to save, fetch, update, and delete Student objects in the database.
 */
public class StudentDao {

    private static final SessionFactory sessionFactory;
    // Static block to initialize the SessionFactory
    // This block is executed once when the class is loaded
    // It sets up the Hibernate configuration and builds the session factory.
    static {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Student.class)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * This method saves a Student object to the database.
     *
     * @param student The Student object to be saved.
     */
    public void saveStudent(Student student) {
        Transaction transaction = null;
        // Open a session
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Save the student object to the database
            session.persist(student);

            // Commit the transaction
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction in case of an error
            }
            System.err.println("Error saving student: " + e.getMessage());
        }
    }

    /**
     * This method fetches a Student object from the database using its roll number.
     *
     * @param rollNum The roll number of the student to be fetched.
     */
    public Student fetchStudent(int rollNum) {
        Student student = null;
        try (Session session = sessionFactory.openSession()) {
            // Fetch the student object from the database
            student = session.find(Student.class, rollNum);
        }
        catch (Exception e) {
            System.err.println("Error fetching student: " + e.getMessage());
            return null; // Return null if an error occurs
        }
        return student;
    }


    /**
     * This method updates a Student object in the database.
     * If the student object does not exist, it will create a new entry.
     * If the data to be updated is same as the existing data, it will not update.
     *
     * @param student The Student object to be updated.
     */
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Updated the student object in the database
            session.merge(student);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction in case of an error
            }
            System.err.println("Error updating student: " + e.getMessage());
        }
    }


    /**
     * This method deletes a Student object from the database using its roll number.
     * If the student does not exist, it will print a message.
     *
     * @param rollNum The roll number of the student to be deleted.
     */
    public void deleteStudent(int rollNum) {
        // Delete a student from the database
        Student student =fetchStudent(rollNum);
        if (student == null) {
            System.out.println("Student with roll number " + rollNum + " does not exist.");
            return;
        }
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Delete the student object from the database
            session.remove(student);

            // Commit the transaction
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction in case of an error
            }
            System.err.println("Error deleting student: " + e.getMessage());
        }
    }

    /**
     * This method closes the session factory to release resources.
     */
    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
