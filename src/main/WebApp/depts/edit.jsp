<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Department</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp" />
</div>
<div class="container">
    <h2>Edit Department</h2>
    <form method="post">
        <label for="short_code">Short Code</label>
        <input name="shortCode" value="${department.shortCode}" id="short_code"/>
        <label for="name">Name</label>
        <input name="name" value="${department.name}" id="name"/>
        <input class="btn btn-danger" type="submit" value="Edit">
    </form>
</div>
</body>
</html>