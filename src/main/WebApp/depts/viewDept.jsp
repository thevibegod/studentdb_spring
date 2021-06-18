<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Department No. ${dept.id}</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp" />
</div>
<div class="container">
    <h2>View Department No. ${dept.id}</h2>
    <div>
        <p>Department Name: ${dept.name}</p>
        <p>Department Code: ${dept.shortCode}</p>
        <button class="btn btn-success" onclick="update(${dept.id});">Edit</button>
        <button class="btn btn-danger" onclick="del(${dept.id});">Delete</button>
    </div>
</div>
<form method="post" id="deleteForm">
</form>
</body>
<script>
    const update = (id) => {
        window.location.href = "/depts/edit/" + id;
    }

    const del = (id) => {
        deleteForm.action = "/depts/delete/" + id;
        deleteForm.submit();
    }
</script>
</html>