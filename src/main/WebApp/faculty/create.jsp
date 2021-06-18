<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Faculty</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp" />
</div>
<div class="container">
    <h2>Create Faculty</h2>
    <form method="post">
        <label for="name">Name</label>
        <input name="name" id="name"/>
        <label for="employeeId">Employee ID</label>
        <input name="employeeId" id="employeeId"/>
        <label for="email">Email</label>
        <input name="email" id="email"/>
        <label for="designation">Designation</label>
        <select name="designation" id="designation">
            <c:forEach items="${choices}" var="choice">
                <option value="${choice}">${choice}</option>
            </c:forEach>
        </select>
        <input class="btn btn-danger" type="submit" value="Create">
    </form>
</div>
</body>
</html>