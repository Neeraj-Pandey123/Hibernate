package com.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Pandu_Worker") // This annotation specifies the table name in the database
public class Worker {

    @Id
    @Column(name = "worker_id") // This annotation specifies the column name in the database
    private int workerId;   // Unique identifier for the worker
    @Column(name = "worker_name")
    private String workerName;
    @Column(name = "worker_role")
    private String workerRole;
    @Column(name = "worker_salary")
    private int workerSalary;
    @Column(name = "worker_email")
    private String workerEmail;
    @Column(name = "worker_phone")
    private String workerPhone;
    @Column(name = "worker_address")
    private String workerAddress;
    @Transient
    private String workerTemporaryField; // Example of a transient field
    // This field will not be stored in the database
    // and is only used for temporary calculations or data that does not need to be saved.

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerRole() {
        return workerRole;
    }

    public void setWorkerRole(String workerRole) {
        this.workerRole = workerRole;
    }

    public int getWorkerSalary() {
        return workerSalary;
    }

    public void setWorkerSalary(int workerSalary) {
        this.workerSalary = workerSalary;
    }

    public String getWorkerEmail() {
        return workerEmail;
    }

    public void setWorkerEmail(String workerEmail) {
        this.workerEmail = workerEmail;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getWorkerAddress() {
        return workerAddress;
    }

    public void setWorkerAddress(String workerAddress) {
        this.workerAddress = workerAddress;
    }

    public String getWorkerTemporaryField() {
        return workerTemporaryField;
    }

    public void setWorkerTemporaryField(String workerTemporaryField) {
        this.workerTemporaryField = workerTemporaryField;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "workerId=" + workerId +
                ", workerName='" + workerName + '\'' +
                ", workerRole='" + workerRole + '\'' +
                ", workerSalary=" + workerSalary +
                ", workerEmail='" + workerEmail + '\'' +
                ", workerPhone='" + workerPhone + '\'' +
                ", workerAddress='" + workerAddress + '\'' +
                ", workerTemporaryField='" + workerTemporaryField + '\'' +
                '}';
    }
}
