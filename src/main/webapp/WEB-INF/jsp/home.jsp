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

    <script src="${pageContext.request.contextPath}/js/dynamicLink.js"></script>
</head>
<body>
<!-- Session handler -->
<%--<jsp:include page="sessionHandlerMain.jsp"/>--%>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <div id="home-card-body">
        <div id="home-search">
            <h1>Search for a flight</h1>
            <!-- Flight search form jsp -->
            <jsp:include page="searchForm.jsp"/>
            <!-- Javascript to set fields to default values -->
            <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>-->
            <script>
                document.getElementById("departureLocation").value = '';
                document.getElementById("arrivalLocation").value = '';
                document.getElementById("adults").value = 1;
                document.getElementById("children").value = 0;
                document.getElementById("form-group-return-date").style.display = 'none';
            </script>
        </div>
    </div>
</main>
</body>
</html>
