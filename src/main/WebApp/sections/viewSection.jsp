<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View Section ${section.name} ${section.year}</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp"/>
</div>
<div class="container">
    <h2>View Section ${section.name} ${section.year}</h2>
    <div>
        <p>Name: ${section.name}</p>
        <p>Year: ${section.year}</p>
        <p>Department: <a
                href="${pageContext.request.contextPath}/depts?id=${section.department.id}">${section.department.shortCode}</a>
        </p>
        <p>Class Advisor: <a
                href="${pageContext.request.contextPath}/faculty?id=${section.classAdvisor.id}">${section.classAdvisor.name}</a>
        </p>
        <button class="btn btn-success" onclick="update(${section.id});">Edit</button>
        <button class="btn btn-danger" onclick="del(${section.id});">Delete</button>
    </div>
</div>
<form method="post" id="deleteForm">
</form>
</body>
<script>
    const update = (id) => {
        window.location.href = "/sections/edit/" + id;
    }

    const del = (id) => {
        deleteForm.action = "/sections/delete/" + id;
        deleteForm.submit();
    }
</script>
</html>