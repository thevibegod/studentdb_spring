<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Section</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp" />
</div>
<div class="container">
    <h2>Create Section</h2>
    <form method="post">
        <label for="name">Name</label>
        <input name="name" id="name"/>
        <label for="year">Year</label>
        <input type="number" name="year" id="year"/>
        <label for="classAdvisor">Class Advisor</label>
        <select name="class_advisor_id" id="classAdvisor">
            <c:forEach items="${faculties}" var="faculty">
                <option value="${faculty.id}">${faculty.getName()}</option>
            </c:forEach>
        </select>
        <label for="department">Department</label>
        <select name="dept_id" id="department">
            <c:forEach items="${departments}" var="dept">
                <option value="${dept.id}">${dept.getName()}</option>
            </c:forEach>
        </select>
        <input class="btn btn-danger" type="submit" value="Create">
    </form>
</div>
</body>
</html>