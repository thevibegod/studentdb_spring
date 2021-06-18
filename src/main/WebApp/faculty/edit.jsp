<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Faculty ${faculty.name}</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp" />
</div>
<div class="container">
    <h2>Edit Faculty ${faculty.name}</h2>
    <form method="post">
        <label for="name">Name</label>
        <input name="name" value="${faculty.name}" id="name"/>
        <label for="employeeId">Employee ID</label>
        <input name="employeeId" value="${faculty.employeeId}" id="employeeId"/>
        <label for="email">Email</label>
        <input name="email" value="${faculty.email}" id="email"/>
        <label for="designation">Designation</label>
        <select name="designation" id="designation">
            <c:forEach items="${choices}" var="choice">
                <c:choose>
                    <c:when test="${choice == faculty.designation}">
                        <option value="${choice}" selected>${choice}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${choice}">${choice}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <input class="btn btn-danger" type="submit" value="Edit">
    </form>
</div>
</body>
</html>