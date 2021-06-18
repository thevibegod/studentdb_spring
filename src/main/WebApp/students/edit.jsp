<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Student ${student.name}</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp"/>
</div>
<div class="container">
    <h2>Edit Student ${student.name}</h2>
    <form method="post">
        <label for="name">Name</label>
        <input name="name" id="name" value="${student.name}"/>
        <label for="email">Email</label>
        <input type="email" name="email" id="email" value="${student.email}"/>
        <label for="rollno">Roll Number</label>
        <input name="rollno" id="rollno" value="${student.rollno}"/>
        <label for="section">Section</label>
        <select name="section_id" id="section">
            <c:forEach items="${sections}" var="section">
                <c:choose>
                    <c:when test="${section.id==student.section.id}">
                        <option value="${section.id}" selected>${section.getName()} ${section.getYear()}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${section.id}">${section.getName()} ${section.getYear()}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <input class="btn btn-danger" type="submit" value="Edit">
    </form>
</div>
</body>
</html>