package com.atuluttam.springBootDBMS.repository;


import com.atuluttam.springBootDBMS.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class studentRepo {
    @Autowired
    JdbcTemplate jt;

    public  int save(Student s)
    {
        String sql = "insert into student(roll, name, course)values(?, ?, ?)";





     int a =    jt.update(sql, s.getRoll(), s.getName(), s.getCourse());
     return a;
    }


}
