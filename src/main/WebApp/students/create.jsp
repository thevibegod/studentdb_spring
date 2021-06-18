<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Student</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp"/>
</div>
<div class="container">
    <h2>Create Student</h2>
    <form method="post">
        <label for="name">Name</label>
        <input name="name" id="name"/>
        <label for="email">Email</label>
        <input type="email" name="email" id="email"/>
        <label for="rollno">Roll Number</label>
        <input name="rollno" id="rollno"/>
        <label for="section">Section</label>
        <select name="section_id" id="section">
            <c:forEach items="${sections}" var="section">
                <option value="${section.id}">${section.getName()} ${section.getYear()}</option>
            </c:forEach>
        </select>
        <input class="btn btn-danger" type="submit" value="Create">
    </form>
</div>
</body>
</html>