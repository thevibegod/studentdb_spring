<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Section ${section.name}</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp"/>
</div>
<div class="container">
    <h2>Edit Section ${section.name}</h2>
    <form method="post">
        <label for="name">Name</label>
        <input name="name" value="${section.name}" id="name"/>
        <label for="year">Year</label>
        <input type="number" value="${section.year}" name="year" id="year"/>
        <label for="classAdvisor">Class Advisor</label>
        <select name="class_advisor_id" id="classAdvisor">
            <c:forEach items="${faculties}" var="faculty">
                <c:choose>
                    <c:when test="${faculty.id==section.classAdvisor.id}">
                        <option value="${faculty.id}" selected>${faculty.getName()}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${faculty.id}">${faculty.getName()}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <label for="department">Department</label>
        <select name="dept_id" id="department">
            <c:forEach items="${departments}" var="dept">
                <c:choose>
                    <c:when test="${dept.id==section.department.id}">
                        <option value="${dept.id}" selected>${dept.getName()}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${dept.id}">${dept.getName()}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        <input class="btn btn-danger" type="submit" value="Edit">
    </form>
</div>
</body>
</html>