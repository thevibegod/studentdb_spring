<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Student ${student.name} ${student.rollno}</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp"/>
</div>
<div class="container">
    <h2>View Student ${student.name} ${student.rollno}</h2>
    <div>
        <p>Name: ${student.name}</p>
        <p>Email: ${student.email}</p>
        <p>Roll Number: ${student.rollno}</p>
        <p>Section: <a
                href="${pageContext.request.contextPath}/sections?id=${student.section.id}">${student.section.name} ${student.section.year}</a>
        </p>
        <button class="btn btn-success" onclick="update(${student.id});">Edit</button>
        <button class="btn btn-danger" onclick="del(${student.id});">Delete</button>
    </div>
</div>
<form method="post" id="deleteForm">
</form>
</body>
<script>
    const update = (id) => {
        window.location.href = "/students/edit/" + id;
    }

    const del = (id) => {
        deleteForm.action = "/students/delete/" + id;
        deleteForm.submit();
    }
</script>
</html>