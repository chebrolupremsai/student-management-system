<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.legacy.Student" %>
<html>
<head>
    <title>Student List - Legacy MVC</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        table { border-collapse: collapse; width: 60%; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        form { margin-top: 20px; padding: 15px; background: #f5f5f5; width: 300px; }
        input { margin: 5px 0; padding: 8px; width: 100%; }
        button { background: #4CAF50; color: white; padding: 10px; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h2>Student Management (JSP + Servlet + JDBC + MVC)</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Course</th>
        </tr>
        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
            if (students != null) {
                for (Student s : students) {
        %>
        <tr>
            <td><%= s.getId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getEmail() %></td>
            <td><%= s.getCourse() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <h3>Add New Student</h3>
    <form method="post" action="students">
        <input type="text" name="name" placeholder="Name" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="text" name="course" placeholder="Course" required>
        <button type="submit">Add Student</button>
    </form>
</body>
</html>