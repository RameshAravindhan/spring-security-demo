<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: rames
  Date: 02-11-2021
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Spring Security</title>
</head>
<body>

Welcome <security:authentication property="principal.username"></security:authentication> to Manager User Page of Spring Security Classes

Role : <security:authentication property="principal.authorities"></security:authentication>

<form:form action="${pageContext.request.contextPath}/logout" method="post">

    <input type="submit" value="Logout">

</form:form>


</body>
</html>