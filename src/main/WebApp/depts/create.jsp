<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create Department</title>
</head>
<body>
<div class="header">
    <c:import url="../header.jsp" />
</div>
<div class="container">
    <h2>Create Department</h2>
    <form method="post">
        <label for="short_code">Short Code</label>
        <input name="shortCode" id="short_code"/>
        <label for="name">Name</label>
        <input name="name" id="name"/>
        <input class="btn btn-danger" type="submit" value="Create">
    </form>
</div>
</body>
</html>