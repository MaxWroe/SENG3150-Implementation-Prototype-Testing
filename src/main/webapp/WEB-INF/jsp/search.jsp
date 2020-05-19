<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jfpr2
  Date: 19/05/2020
  Time: 2:53 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Page</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <!-- The current search criteria -->
    <div id="search-field">

    </div>

    <!-- Result template -->
    <div class="flight-result">
        <div class="flight-result-time">
            <p>Depart time</p>
            <h4>00:00AM</h4>
            <p>Arrival time</p>
            <h4>00:00AM</h4>
        </div>
        <div class="flight-result-details">
            <p>Company</p>
            <p>Class Type</p>
            <p>Flight ID</p>
        </div>
        <div class="flight-result-cost">
            <h3>$000</h3>
        </div>
        <div class="flight-result-book">
            <button type="button">Book</button>
        </div>
    </div>

    <!-- Parse all returned flights -->
    <c:forEach items="${flights}" var="flight">
        <div class="flight-result">
            <div class="flight-result-time">
                <p>Depart time</p>
                <h4>${flight.departTime}</h4>
                <p>Arrival time</p>
                <h4>${flight.arrivalTime}</h4>
            </div>
            <div class="flight-result-details">
                <p>${flight.company}</p>
                <p>${flight.classType}</p>
                <p>${flight.id}</p>
            </div>
            <div class="flight-result-cost">
                <h3>${flight.cost}</h3>
            </div>
            <div class="flight-result-book">
                <button type="button">Book</button>
            </div>
        </div>
    </c:forEach>
</main>
</body>
</html>
