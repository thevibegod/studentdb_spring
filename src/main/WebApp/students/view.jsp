<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View All Students</title>
    <link rel="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"/>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp"/>
</div>
<div class="container">
    <h2>View All Students</h2>
    <div class="row">
        <div class="col">
            <a href="${pageContext.request.contextPath}/students/create" class="btn btn-primary">Create</a>
        </div>
    </div>
    <table id="datatable1" class="table table-striped table-bordered" style="width: 100%">
        <thead>
        <th>S.No</th>
        <th>ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Roll Number</th>
        <th>Section</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:set scope="page" var="count" value="1"/>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>
                        ${count}
                </td>
                <td>
                        ${student.id}
                </td>
                <td>
                        ${student.name}
                </td>
                <td>
                        ${student.email}
                </td>
                <td>
                        ${student.rollno}
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/sections?id=${student.section.id}">${student.section.name} ${student.section.year}</a>
                </td>
                <td>
                    <button class="btn btn-success" onclick="update(${student.id});">Edit</button>
                    <button class="btn btn-danger" onclick="del(${student.id});">Delete</button>
                </td>
            </tr>
            <c:set scope="page" value="${count+1}" var="count"/>
        </c:forEach>
        </tbody>
    </table>
</div>
<form method="post" id="deleteForm">
</form>
</body>
<script>
    $(function () {
        $("#datatable1").DataTable();
    });

    const update = (id) => {
        window.location.href = "/students/edit/" + id;
    }

    const del = (id) => {
        deleteForm.action = "/students/delete/" + id;
        deleteForm.submit();
    }
</script>
</html>