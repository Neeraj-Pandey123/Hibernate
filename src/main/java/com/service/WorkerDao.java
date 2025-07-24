package com.service;

import com.entities.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class WorkerDao {

    private static final SessionFactory sessionFactory;
    // Static block to initialize the SessionFactory
    // This block is executed once when the class is loaded
    // It sets up the Hibernate configuration and builds the session factory.
    static {
        try {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(Worker.class)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * This method saves a Worker object to the database.
     * It opens a session, begins a transaction, and persists the Worker object.
     * If an error occurs, it rolls back the transaction and logs the error message.
     *
     * @param worker The Worker object to be saved.
     */
    public void saveWorker(Worker worker) {
        Transaction transaction = null;
        // Open a session from the session factory
        try (Session session = sessionFactory.openSession()) {  // Auto-closeable session
            // Begin a transaction
            transaction = session.beginTransaction();

            // Save the worker object to the database
            session.persist(worker);

            // Commit the transaction
            transaction.commit();

            // If the transaction is successful, print a success message
            System.out.println("Worker saved successfully.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction in case of an error
            }
            System.err.println("Error saving worker: " + e.getMessage());
        }
    }

    /**
     * This method fetches a Worker object from the database by its ID.
     * It opens a session, retrieves the Worker object, and returns it.
     * If no worker is found with the given ID, it returns null.
     *
     * @param workerId The ID of the worker to be fetched.
     * @return The Worker object if found, otherwise null.
     */
    public Worker fetchWorker(int workerId) {
        Worker worker = null;
        // Open a session from the session factory
        try (Session session = sessionFactory.openSession()) {
            // Fetch the worker object by its ID
            worker = session.find(Worker.class, workerId);
        } catch (Exception e) {
            System.err.println("Error fetching worker: " + e.getMessage());
        }
        return worker;
    }

    /**
     * This method updates an existing Worker object in the database.
     * It opens a session, begins a transaction, and merges the updated Worker object.
     * If an error occurs, it rolls back the transaction and logs the error message.
     * If the worker does not exist, it will create a new entry.
     * * Note: If the data to be updated is the same as the existing data, it will not update.
     *
     * @param worker The Worker object with updated information.
     */
    public void updateWorker(Worker worker) {
        Transaction transaction = null;
        // Open a session from the session factory
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Update the worker object in the database
            session.merge(worker);

            // Commit the transaction
            transaction.commit();

            System.out.println("Worker updated successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction in case of an error
            }
            System.err.println("Error updating worker: " + e.getMessage());
        }
    }


    /**
     * This method deletes a Worker object from the database by its ID.
     * It opens a session, begins a transaction, and removes the Worker object.
     * If an error occurs, it rolls back the transaction and logs the error message.
     *
     * @param workerId The ID of the worker to be deleted.
     */
    public void deleteWorker(int workerId) {
        Transaction transaction = null;
        // Open a session from the session factory
        try (Session session = sessionFactory.openSession()) {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Fetch the worker object by its ID
            Worker worker = session.find(Worker.class, workerId);
            if (worker != null) {
                // Remove the worker object from the database
                session.remove(worker);
                System.out.println("Worker deleted successfully.");
            } else {
                System.out.println("No worker found with ID: " + workerId);
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction in case of an error
            }
            System.err.println("Error deleting worker: " + e.getMessage());
        }
    }


    /**
     * This method closes the session factory.
     * It should be called when the application is shutting down to release resources.
     */
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
