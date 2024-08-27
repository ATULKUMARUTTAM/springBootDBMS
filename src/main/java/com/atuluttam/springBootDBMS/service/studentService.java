package com.atuluttam.springBootDBMS.service;

import com.atuluttam.springBootDBMS.Model.Student;
import com.atuluttam.springBootDBMS.repository.studentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class studentService {
    @Autowired
    studentRepo sr;





    public void storeindatabase(Student s)
    {
        System.out.println(sr.save(s) + "Number of records stored");
    }



}
