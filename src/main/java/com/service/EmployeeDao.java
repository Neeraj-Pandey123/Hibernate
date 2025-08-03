package com.service;

import com.entities.Employee;
import com.entities.Laptop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDao {

    public static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Laptop.class)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * This method saves an Employee object to the database.
     * It opens a session, begins a transaction, and persists the Employee object.
     * If an error occurs, it rolls back the transaction and logs the error message.
     *
     * @param employee The Employee object to be saved.
     */
    public void saveEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            session.persist(employee);
            // save the employee object to the database
            for(Laptop laptop: employee.getLaptop()) session.persist(laptop);

            // commit the transaction
            transaction.commit();

            // If the transaction is successful, print a success message
            System.out.println("Employee saved successfully.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction in case of an error
            }
            System.err.println("Error saving employee: " + e.getMessage());
        }
    }
}
