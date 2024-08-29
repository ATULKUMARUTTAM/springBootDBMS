package com.atuluttam.springBootDBMS.service;

import com.atuluttam.springBootDBMS.Model.Student;
import com.atuluttam.springBootDBMS.repository.studentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentService {

    @Autowired
    studentRepo sr;

    public void storeInDatabase(Student s) {
        System.out.println(sr.save(s) + " record(s) stored.");
    }

    public Student findStudentByRoll(int roll) {
        return sr.findByRoll(roll);
    }

    public List<Student> findAllStudents() {
        return sr.findAll();
    }

    public void deleteStudentByRoll(int roll) {
        System.out.println(sr.deleteByRoll(roll) + " record(s) deleted.");
    }

    public void updateStudentInDatabase(Student s) {
        System.out.println(sr.updateStudent(s) + " record(s) updated.");
    }
}
