package com.atuluttam.springBootDBMS.repository;

import com.atuluttam.springBootDBMS.Exception.NoSuchStudentExistsException;
import com.atuluttam.springBootDBMS.Exception.StudentAlreadyExistsException;
import com.atuluttam.springBootDBMS.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class studentRepo {

    @Autowired
    JdbcTemplate jt;

    // Save a student
    public int save(Student s) {
        String checkSql = "select count(*) from student where roll = ?";
        Integer count = jt.queryForObject(checkSql, new Object[]{s.getRoll()}, Integer.class);

        if (count != null && count > 0) {
            throw new StudentAlreadyExistsException("Student with roll number " + s.getRoll() + " already exists.");
        }

        String sql = "insert into student(roll, name, course) values(?, ?, ?)";
        return jt.update(sql, s.getRoll(), s.getName(), s.getCourse());
    }

    // Find a student by roll number
    public Student findByRoll(int roll) {
        String sql = "select * from student where roll = ?";
        List<Student> students = jt.query(sql, new Object[]{roll}, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student st = new Student();
                st.setRoll(rs.getInt("roll"));
                st.setName(rs.getString("name"));
                st.setCourse(rs.getString("course"));
                return st;
            }
        });

        if (students.isEmpty()) {
            throw new NoSuchStudentExistsException("No student found with roll number " + roll);
        }

        return students.get(0);
    }

    // Display all students
    public List<Student> findAll() {
        String sql = "select * from student";
        return jt.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student st = new Student();
                st.setRoll(rs.getInt("roll"));
                st.setName(rs.getString("name"));
                st.setCourse(rs.getString("course"));
                return st;
            }
        });
    }

    // Delete a student by roll number
    public int deleteByRoll(int roll) {
        String checkSql = "select count(*) from student where roll = ?";
        Integer count = jt.queryForObject(checkSql, new Object[]{roll}, Integer.class);

        if (count == null || count == 0) {
            throw new NoSuchStudentExistsException("No student found with roll number " + roll);
        }

        String deleteSql = "delete from student where roll = ?";
        return jt.update(deleteSql, roll);
    }

    // Update a student's name and course by roll number
    public int updateStudent(Student s) {
        String checkSql = "select count(*) from student where roll = ?";
        Integer count = jt.queryForObject(checkSql, new Object[]{s.getRoll()}, Integer.class);

        if (count == null || count == 0) {
            throw new NoSuchStudentExistsException("No student found with roll number " + s.getRoll());
        }

        String updateSql = "update student set name = ?, course = ? where roll = ?";
        return jt.update(updateSql, s.getName(), s.getCourse(), s.getRoll());
    }
}
