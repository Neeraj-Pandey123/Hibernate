package com.pandu;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {


    }

    /**
     * This method saves a Student object to the database.
     *
     * @param student The Student object to be saved.
     */
    public static void saveStudent(Student student) {
        // Create a Configuration object
        Configuration cfg = new Configuration()             // Load the configuration from hibernate.cfg.xml
                .addAnnotatedClass(com.pandu.Student.class) // Specify the annotated class
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
    public static void fetchStudent(int rollNum) {
        // Create a Configuration object
        Configuration cfg = new Configuration()
                .addAnnotatedClass(com.pandu.Student.class)
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
    }


}