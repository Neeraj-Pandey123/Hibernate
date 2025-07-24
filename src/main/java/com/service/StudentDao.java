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

    /**
     * This method saves a Student object to the database.
     *
     * @param student The Student object to be saved.
     */
    public static void saveStudent(Student student) {
        // Create a Configuration object
        Configuration cfg = new Configuration()             // Load the configuration from hibernate.cfg.xml
                .addAnnotatedClass(Student.class) // Specify the annotated class
                .configure();                               // Load the configuration file

        // Build a SessionFactory
        SessionFactory factory = cfg.buildSessionFactory();

        // Open a session
        Session session = factory.openSession();

        // Begin a transaction
        Transaction tx = session.beginTransaction();

        // Save the student object to the database
        session.persist(student);

        // Commit the transaction
        tx.commit();

        // Close the session
        session.close();

        // Close the factory
        factory.close();
    }

    /**
     * This method fetches a Student object from the database using its roll number.
     *
     * @param rollNum The roll number of the student to be fetched.
     */
    public static Student fetchStudent(int rollNum) {
        // Create a Configuration object
        Configuration cfg = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure();

        // Build a SessionFactory
        SessionFactory factory = cfg.buildSessionFactory();

        // Open a session
        Session session = factory.openSession();

        // Fetch the student object from the database
        Student student = session.find(Student.class, rollNum);

        // Print the fetched student object
        System.out.println(student);

        // Close the session
        session.close();

        // Close the factory
        factory.close();

        return student;
    }


    /**
     * This method updates a Student object in the database.
     * If the student object does not exist, it will create a new entry.
     * If the data to be updated is same as the existing data, it will not update.
     *
     * @param student The Student object to be updated.
     */
    public static void updateStudent(Student student) {

        // Create a configuration object
        Configuration cfg = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure();

        // Build a session factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Open a sessoin
        Session session = sessionFactory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        // Updated the student object in the database
        session.merge(student);

        // Commit the transaction
        transaction.commit();

        // Close the session
        session.close();

        // Close the session factory
        sessionFactory.close();
    }


    /**
     * This method deletes a Student object from the database using its roll number.
     * If the student does not exist, it will print a message.
     *
     * @param rollNum The roll number of the student to be deleted.
     */
    public static void deleteStudent(int rollNum) {
        // Delete a student from the database
        Student student =fetchStudent(rollNum);
        if (student == null) {
            System.out.println("Student with roll number " + rollNum + " does not exist.");
            return;
        }
        // Create a configuration object
        Configuration cfg = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure();

        // Build a session factory
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();

        // Delete the student object from the database
        session.remove(student);

        // Commit the transaction
        transaction.commit();

        // Close the session
        session.close();

        // close the session factory
        sessionFactory.close();
    }

}
