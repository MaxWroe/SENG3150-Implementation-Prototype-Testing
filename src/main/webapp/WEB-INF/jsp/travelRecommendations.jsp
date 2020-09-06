<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:forEach items="${recommendationPackages}" var="recommendationPackage">
            <c:set var="holidayPackage" value="${recommendationPackage.hp}" scope="page"/>
            <c:set var="flightPlan" value="${recommendationPackage.fp}" scope="page"/>
            <div class="recommendation-display-window">
                <h2>${holidayPackage.countryName}</h2>
                <p>${holidayPackage.description}</p>

                <c:if test="${empty flightPlan}">
                <p>Found flightplan</p>

                <form name="searchFlight" method="get" id="searchFlight" action="${pageContext.request.contextPath}/search" onsubmit="return validateForm()">
                    <input type="hidden" id="type" name="type" value="oneway">
                    <input type="hidden" id="classCode" name="classCode" value="${recommendationPackage.cc}">
                    <input type="hidden" id="adults" name="adults" value="${recommendationPackage.groupSize}">
                    <input type="hidden" id="departureLocation" name="departureLocation" value="">
                    <input type="hidden" id="arrivalLocation" name="arrivalLocation" value="${holidayPackage.destination}">
                    <input type="hidden" id="departureDate" name="departureDate" value="${flightPlan.departureDate}">
                </form>
                </c:if>
            </div>
        </c:forEach>
    </div>
</main>
</body>
</html>
