<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View All Departments</title>
    <link rel="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"/>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp"/>
</div>
<div class="container">
    <h2>View All Departments</h2>
    <div class="row">
        <div class="col">
            <a href="${pageContext.request.contextPath}/depts/create" class="btn btn-primary">Create</a>
        </div>
    </div>
    <table id="datatable1" class="table table-striped table-bordered" style="width: 100%">
        <thead>
        <th>S.No</th>
        <th>Department ID</th>
        <th>Department ShortCode</th>
        <th>Department Name</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:set scope="page" var="count" value="1"/>
        <c:forEach items="${depts}" var="dept">
            <tr>
                <td>
                        ${count}
                </td>
                <td>
                        ${dept.id}
                </td>
                <td>
                        ${dept.shortCode}
                </td>
                <td>
                        ${dept.name}
                </td>
                <td>
                    <button class="btn btn-success" onclick="update(${dept.id});">Edit</button>
                    <button class="btn btn-danger" onclick="del(${dept.id});">Delete</button>
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
        window.location.href = "/depts/edit/" + id;
    }

    const del = (id) => {
        deleteForm.action = "/depts/delete/" + id;
        deleteForm.submit();
    }
</script>
</html>