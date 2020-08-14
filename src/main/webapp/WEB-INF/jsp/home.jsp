<%--
  JSP for initial searching of flight plans.
  SENG3150 Group 3
  Date: 17/05/2020
  Time: 3:22 pm
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Home Page</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

    <script src="${pageContext.request.contextPath}/js/searchFormAssistor.js"></script>
    <script src="${pageContext.request.contextPath}/js/dynamicLink.js"></script>
</head>
<body>
<!-- Session handler -->
<jsp:include page="sessionHandlerMain.jsp"/>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <div class="card-body">
        <h1>Search</h1>
        <h4>Search for Flight</h4>
        <!-- Flight search form jsp -->
        <jsp:include page="searchForm.jsp"/>
    </div>
</main>
</body>
</html>
