<%@ page import="org.example.hw4.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Select page</title>
</head>

<body>

    <h1>Get one Student from DB by ID</h1>

    <br/>

    <%
        Student student = (Student) request.getSession().getAttribute("student");
    %>

    <h3>
        Id: <%=student.getStudentId()%>
    </h3>
    <h3>
        Name: <%=student.getName()%>
    </h3>
    <h3>
        Surname: <%=student.getSurname()%>
    </h3>
    <h3>
        Birthdate: <%=student.getBirthDate()%>
    </h3>
    <h3>
        Group number: <%=student.getUniversityGroup().getNumber()%>
    </h3>



</body>

</html>