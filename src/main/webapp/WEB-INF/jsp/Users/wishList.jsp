<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 5/09/2020
  Time: 11:19 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wish List</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>My Wish List</h1>
        <h4></h4>

        <%-- if wish list is empty --%>
        <c:if test="${empty wishList}">
            <h4>Empty</h4>
        </c:if>

        <%-- parse all wish list --%>
        <c:forEach items="${wishList}" var ="wishList">
            <p><c:out value= "${wishList.countryCode3}"></c:out></p>
            <p><c:out value= "${booking.countryName}"></c:out></p>

        </c:forEach>

    </div>
</main>

</body>
</html>
