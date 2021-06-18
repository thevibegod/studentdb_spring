<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Details Portal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>


<div class="header">
    <c:import url="header.jsp"/>
</div>

<div class="container">
    <h3 class="text-center my-4">Index Page</h3>
    <div class='row d-flex justify-content-center'>
        <div class='row d-flex justify-content-center'>
            <div class='col-md'>
                <center><a class="btn btn-primary" href="${pageContext.request.contextPath}/depts/view">Dept.
                    Management</a>
                </center>
            </div>
            <div class='col-md'>
                <center><a class="btn btn-secondary" href="${pageContext.request.contextPath}/faculty/view">Faculty
                    Management</a></center>
            </div>
            <div class='col-md'>
                <center><a class="btn btn-danger" href="${pageContext.request.contextPath}/sections/view">Section
                    Management</a></center>
            </div>
            <div class='col-md'>
                <center><a class="btn btn-success" href="${pageContext.request.contextPath}/students/view">Student
                    Management</a></center>
            </div>
        </div>
    </div>
</div>
</body>
</html>

