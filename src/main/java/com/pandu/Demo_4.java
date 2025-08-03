package com.pandu;

import com.entities.Address;
import com.entities.StudentWithAddress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Demo_4 {

    public static void main(String[] args) {
        // This is a placeholder for the main method.
        // You can add your code here to test the functionality of your application.
        System.out.println("Demo_4 is running!");

        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("Springfield");
        address.setState("IL");
        address.setZipCode("62701");
        address.setCountry("USA");

        StudentWithAddress student = new StudentWithAddress();
        student.setRollNum(1);
        student.setsName("John Doe");
        student.setsAge(20);
        student.setAddress(address);

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(StudentWithAddress.class)
                .addAnnotatedClass(Address.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.persist(student);

        transaction.commit();

        StudentWithAddress fetchedStudent = session.find(StudentWithAddress.class, 1);
        System.out.println("Fetched Student: " + fetchedStudent.getAddress());

        session.close();
        sessionFactory.close();

    }
}
