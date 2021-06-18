<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>View All Sections</title>
    <link rel="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"/>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp" />
</div>
<div class="container">
    <h2>View All Sections</h2>
    <div class="row">
        <div class="col">
            <a href="${pageContext.request.contextPath}/sections/create" class="btn btn-primary">Create</a>
        </div>
    </div>
    <table id="datatable1" class="table table-striped table-bordered" style="width: 100%">
        <thead>
        <th>S.No</th>
        <th>ID</th>
        <th>Name</th>
        <th>Year</th>
        <th>Department</th>
        <th>Class Advisor</th>
        <th>Actions</th>
        </thead>
        <tbody>
        <c:set scope="page" var="count" value="1"/>
        <c:forEach items="${sections}" var="section">
            <tr>
                <td>
                        ${count}
                </td>
                <td>
                        ${section.id}
                </td>
                <td>
                        ${section.name}
                </td>
                <td>
                        ${section.year}
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/depts?id=${section.department.id}">${section.department.shortCode}</a>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/faculty?id=${section.classAdvisor.id}">${section.classAdvisor.name}</a>
                </td>
                <td>
                    <button class="btn btn-success" onclick="update(${section.id});">Edit</button>
                    <button class="btn btn-danger" onclick="del(${section.id});">Delete</button>
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
        window.location.href = "/sections/edit/" + id;
    }

    const del = (id) => {
        deleteForm.action = "/sections/delete/" + id;
        deleteForm.submit();
    }
</script>
</html>