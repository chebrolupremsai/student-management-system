package com.example.legacy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentDao {

    private Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:studentdb", "sa", "");
    }

    public List<Student> getAll() throws Exception {
        List<Student> list = new ArrayList<>();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM students");

        while (rs.next()) {
            list.add(new Student(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course")
            ));
        }

        rs.close();
        stmt.close();
        conn.close();
        return list;
    }

    public void add(Student student) throws Exception {
        Connection conn = getConnection();
        String sql = "INSERT INTO students (name, email, course) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setString(2, student.getEmail());
        ps.setString(3, student.getCourse());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}