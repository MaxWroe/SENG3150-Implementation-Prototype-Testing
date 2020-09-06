<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 28/08/2020
  Time: 4:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>List of Users</h1>

        <div class="manage-users">
        <c:forEach items="${users}" var ="users">
            <c:if test="${users.ROLEID == 'CUSTOMER'}">
                <c:out value= "${users.lastName}"></c:out>
                <c:out value= "${users.firstName}"></c:out>
                <c:out value= "${users.email}"></c:out>

            </c:if>

        </c:forEach>

        </div>






    </div>
</main>

</body>
</html>
