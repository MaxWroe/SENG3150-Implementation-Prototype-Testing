<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
  Created by IntelliJ IDEA.
  User: jfpr2
  Date: 17/05/2020
  Time: 3:22 pm
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Home Page</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <div class="card-body">
        <h1>Search</h1>
        <h4>Search for Flight</h4>
        <!-- Flight search fields -->
        <form method="get" id="searchFlight" action="/search">
            <!-- Script for displaying return date on return flights -->
            <script>
                function showDiv(divId, changedId, element)
                {
                    document.getElementById(divId).style.display = element.value === "return" ? 'inline' : 'none';
                    document.getElementById(changedId).required = element.value === "return";
                }

                // Function to restrict return date to not minimum than depart
                function restrictDepart()
                {
                    document.getElementById("return").min = document.getElementById("depart").value;
                    // Disable return date until depart is selected
                    document.getElementById("return").disabled = false;
                    // Clear depart value
                    document.getElementById("return").value = "";
                }
            </script>

            <!-- Return or one-way trip -->
            <div id="home-form-group-trip">
                <label for="type">Trip:</label>
                <select id="type" name="type" onchange="showDiv('home-form-group-return-date', 'return', this)">
                    <option value="oneway">One-way</option>
                    <option value="return">Return</option>
                </select>
            </div>

            <!-- Adult passengers -->
            <label for="adults">Adults:</label>
            <input type="number" id="adults" name="adults" min="1" max="9" value="1" required>
            <br>

            <!-- Children passengers -->
            <label for="children">Children:</label>
            <input type="number" id="children" name="children" min="0" max="9" value="0" required>
            <br>

            <!-- Starting airport -->
            <div class="home-form-group">
                <label for="from">From:</label>
                <input list="locations" name="from" id="from" required>
            </div>

            <!-- Destination airport -->
            <div class="home-form-group">
                <label for="to">To:</label>
                <input list="locations" name="to" id="to" required>
            </div>
            <br>

            <!-- Parse all available airport destinations -->
            <datalist id="destinations">
                <c:forEach items="${destinations}" var="airport">
                    <option value="<c:out value="${airport.code}"/>"><c:out value="${airport.name}"/></option>
                </c:forEach>
            </datalist>

            <!-- temp destinations -->
            <datalist id="locations">
                <option value="SYD">Sydney - SYD</option>
                <option value="GIG">Rio De Janeiro - GIG</option>
                <option value="LAX">Los Angeles - LAX</option>
            </datalist>

            <!-- Depart date -->
            <div class="home-form-group">
                <label for="depart">Depart:</label>
                <jsp:useBean id="now" class="java.util.Date"/>
                <input type="date" id="depart" name="depart" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
                       onchange="restrictDepart()" required>
            </div>

            <!-- Return date -->
            <div id="home-form-group-return-date">
                <label for="return">Return:</label>
                <input type="date" id="return" name="return" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" disabled>
            </div>
            <br>

            <!-- Search button -->
            <button class="btn btn-lg btn-outline-success text-uppercase" type="submit">Search</button>
        </form>
    </div>
</main>
</body>
</html>
