<%@ page import="org.example.hw3.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Start page</title>
</head>

<body>

<h1>Get one Student from DB by ID</h1>

<br/>

<p>
    <%
        Student student = (Student) request.getSession().getAttribute("student");
    %>
    Id: <%=student.getStudentId()%>
    Name: <%=student.getName()%>
    Surname: <%=student.getSurname()%>
    Birth date: <%=student.getBirthDate()%>
    Group number: <%=student.getUniversityGroup().getNumber()%>
</p>

</body>

</html>