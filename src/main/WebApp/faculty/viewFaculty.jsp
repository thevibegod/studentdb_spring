<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Faculty ${faculty.name}</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp" />
</div>
<div class="container">
    <h2>View Faculty ${faculty.name}</h2>
    <div>
        <p>Name: ${faculty.name}</p>
        <p>Employee ID: ${faculty.employeeId}</p>
        <p>Email: ${faculty.email}</p>
        <p>Designation: ${faculty.designation}</p>
        <button class="btn btn-success" onclick="update(${faculty.id});">Edit</button>
        <button class="btn btn-danger" onclick="del(${faculty.id});">Delete</button>
    </div>
</div>
<form method="post" id="deleteForm">
</form>
</body>
<script>
    const update = (id) => {
        window.location.href = "/faculty/edit/" + id;
    }

    const del = (id) => {
        deleteForm.action = "/faculty/delete/" + id;
        deleteForm.submit();
    }
</script>
</html>