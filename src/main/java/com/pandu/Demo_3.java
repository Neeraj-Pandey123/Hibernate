package com.pandu;

import com.entities.Employee;
import com.entities.Laptop;
import com.service.EmployeeDao;

import java.util.Arrays;

public class Demo_3 {

    public static void main(String[] args) {
        // Understanding relationships in JPA
        // 1. One-to-One
        // 2. One-to-Many
        // 3. Many-to-One
        // 4. Many-to-Many
        // Create a laptop object

        Employee employee = new Employee();
        employee.setEmpId(10001);
        employee.setEmpName("Karlos");
        employee.setTech("Java");

        Laptop laptop = new Laptop();
        laptop.setLaptopId(101);
        laptop.setLaptopName("MacBook Pro");
        laptop.setLaptopBrand("Apple");
        laptop.setLaptopModel("M1 Chip");
        laptop.setEmployee(employee); // Setting the employee for the laptop

        Laptop laptop2 = new Laptop();
        laptop2.setLaptopId(102);
        laptop2.setLaptopName("ThinkPad X1");
        laptop2.setLaptopBrand("Lenovo");
        laptop2.setLaptopModel("Intel i7");
        laptop2.setEmployee(employee); // Setting the employee for the second laptop

        employee.setLaptop(Arrays.asList(laptop, laptop2)); // Assigning multiple laptops to the employee

        // Save the employee object which will also save the laptop object due to cascading
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.saveEmployee(employee);
    }
}
