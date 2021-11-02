<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="false" %>
<html>
<head>
    <title>Custom Login Page</title>

    <style>
        .failed {
            color: red;
        }

    </style>
</head>
<body>

<h3> My login Page</h3>


<c:if test="${param.error != null}">

    <i class="failed"> Sorry! You entered a invalid Username/Password </i>


</c:if>


<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">

    <p>
        User Name : <input type="text" name="username">

    </p>


    <p>
        Password : <input type="text" name="password">

    </p>

    <input type="submit" value="Login">

</form:form>

</body>
</html>