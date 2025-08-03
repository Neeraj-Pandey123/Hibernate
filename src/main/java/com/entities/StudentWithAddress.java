package com.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class StudentWithAddress {

    @Id
    private int rollNum;
    private String sName;
    private int sAge;
    private Address address;

    public int getRollNum() {
        return rollNum;
    }

    public void setRollNum(int rollNum) {
        this.rollNum = rollNum;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public int getsAge() {
        return sAge;
    }

    public void setsAge(int sAge) {
        this.sAge = sAge;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNum=" + rollNum +
                ", sName='" + sName + '\'' +
                ", sAge=" + sAge +
                ", address=" + address +
                '}';
    }
}
