<%--
  JSP for displaying travel recommendations.
  SENG3150 Group 3
  Date: 22/05/2020
  Time: 4:35 pm
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Travel Recommendations</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <script src="${pageContext.request.contextPath}/js/dynamicLink.js"></script>
</head>
<body>
<!-- Session checker -->
<jsp:include page="sessionHandlerGuest.jsp"/>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <h1>Travel Recommendations</h1>
    <h4>Deals</h4>
    <div class="recommendation">
        <div class="recommendation-display-window">
        </div>
    </div>
</main>
</body>
</html>
