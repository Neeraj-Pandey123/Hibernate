package com.pandu;

import com.entities.Student;
import com.service.StudentDao;

public class Demo_1 {

    public static void main(String[] args) {
        // Create a new Student object
        Student student = new Student();
        student.setRollNum(110);
        student.setsName("John Doe");
        student.setsAge(20);

        // Save the student object to the database
        StudentDao.saveStudent(student);

        // Fetch the student object from the database
        Student fetchedStudent = StudentDao.fetchStudent(110);
        System.out.println("Fetched Student: " + fetchedStudent);

        // Update the student object in the database
        fetchedStudent.setsName("Jane Doe Junior");
        StudentDao.updateStudent(fetchedStudent);

        // Fetch the updated student object from the database
        Student updatedStudent = StudentDao.fetchStudent(110);
        System.out.println("Updated Student: " + updatedStudent);

        // Delete the student object from the database
        StudentDao.deleteStudent(110);

    }



}