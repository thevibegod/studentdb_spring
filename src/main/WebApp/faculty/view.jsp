<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View All Faculty</title>
    <link rel="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"/>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp" />
</div>
<div class="container">
    <h2>View All Faculty</h2>
    <div class="row">
        <div class="col">
            <a href="${pageContext.request.contextPath}/faculty/create" class="btn btn-primary">Create</a>
        </div>
    </div>
    <table id="datatable1" class="table table-striped table-bordered" style="width: 100%">
        <thead>
        <th>S.No</th>
        <th>ID</th>
        <th>Name</th>
        <th>Emp Code</th>
        <th>Email</th>
        <th>Designation</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:set scope="page" var="count" value="1"/>
        <c:forEach items="${faculties}" var="faculty">
            <tr>
                <td>
                        ${count}
                </td>
                <td>
                        ${faculty.id}
                </td>
                <td>
                        ${faculty.name}
                </td>
                <td>
                        ${faculty.employeeId}
                </td>
                <td>
                        ${faculty.email}
                </td>
                <td>
                        ${faculty.designation}
                </td>
                <td>
                    <button class="btn btn-success" onclick="update(${faculty.id});">Edit</button>
                    <button class="btn btn-danger" onclick="del(${faculty.id});">Delete</button>
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
        window.location.href = "/faculty/edit/" + id;
    }

    const del = (id) => {
        deleteForm.action = "/faculty/delete/" + id;
        deleteForm.submit();
    }
</script>
</html>