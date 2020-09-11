<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 23/08/2020
  Time: 12:50 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reviews</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <%--<script src="/js/dynamicLink.js"></script>--%>

</head>
<body>
<!-- session handler -->
<%--<jsp:include page="sessionHandlerGuest.jsp"/>--%>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>FlightPub Reviews</h1>

        <!-- checks if no user reviews yet -->
        <c:if test="${empty review}">
            <h4>${message}</h4>
        </c:if>

        <!-- gets all review information -->
        <c:forEach items="${review}" var ="review">
            <h4>Title: </h4><c:out value= " ${review.name}"></c:out>
            <h4>Review Date: </h4><c:out value= " ${review.reviewDate}"></c:out>
            <h4>Comment: </h4><c:out value= " ${review.comment}"></c:out>
            <h4>Rating: </h4><c:out value= " ${review.rating}"></c:out>
        </c:forEach>

    </div>
</main>

</body>
</html>
