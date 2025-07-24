package com.pandu;

import com.entities.Worker;
import com.service.WorkerDao;

public class Demo_2 {

    public static void main(String[] args) {

        // Create a WorkerDao instance to perform operations
        WorkerDao workerDao = new WorkerDao();

        // Create a new Worker Object
        Worker worker = new Worker();
        worker.setWorkerId(103);
        worker.setWorkerName("Khaman Dhokla");
        worker.setWorkerRole("Chef");
        worker.setWorkerSalary(200_000);
        worker.setWorkerEmail("khaman.dhokla@pandu.com");
        worker.setWorkerPhone("+91-9876543210");
        worker.setWorkerAddress("Ahmedabad, Gujarat, India");
        worker.setWorkerTemporaryField("Temp data for Khaman Dhokla");

        System.out.println("SAVING WORKER OBJECT TO THE DATABASE");
        // Save the Worker object to the database
        workerDao.saveWorker(worker);

        System.out.println("*********************************************");

        System.out.println("FETCHING WORKER OBJECT FROM THE DATABASE BY ID");
        // Fetch the Worker object from the database by ID
        Worker fetchedWorker = workerDao.fetchWorker(101);
        if (fetchedWorker != null) {
            System.out.println("Fetched Worker: " + fetchedWorker.getWorkerName());
        } else {
            System.out.println("No worker found with the given ID. " +
                               "Please check the ID and try again.");
        }
        System.out.println("*********************************************");

        System.out.println("UPDATING WORKER OBJECT IN THE DATABASE");
        // Update the Worker object
        assert fetchedWorker != null;
        fetchedWorker.setWorkerSalary(80000); // Update the salary
        workerDao.updateWorker(fetchedWorker);
        System.out.println("**********************************************");

        System.out.println("FETCHING UPDATED WORKER OBJECT FROM THE DATABASE");
        // Fetch the updated Worker object
        Worker updatedWorker = workerDao.fetchWorker(101);
        if (updatedWorker != null) {
            System.out.println("Updated Worker Salary: " + updatedWorker.getWorkerSalary());
        } else {
            System.out.println("No worker found with the given ID after update.");
        }
        System.out.println("**********************************************");

        System.out.println("DELETING WORKER OBJECT FROM THE DATABASE");
        // Delete the Worker object from the database
        workerDao.deleteWorker(101);
        // Try to fetch the deleted Worker object
        Worker deletedWorker = workerDao.fetchWorker(101);
        if (deletedWorker == null) {
            System.out.println("Worker deleted successfully. No worker found with the given ID.");
        } else {
            System.out.println("Worker still exists: " + deletedWorker.getWorkerName());
        }
        System.out.println("**********************************************");

        // Print a message indicating the end of the operations
        System.out.println("All operations completed successfully.");

        // Close the session factory to release resources
        workerDao.close();
    }
}
