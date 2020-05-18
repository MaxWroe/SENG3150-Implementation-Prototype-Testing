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
<!-- Navigation bar -->
<header class="navbar">
    <div class="nav-logo">
        <a href="/home">FlightPub</a>
    </div>
    <nav class="primary-nav">
        <ul>
            <li>
                <a class ="active" href="/home">Flight Search</a>
            </li>
            <li>
                <a href="/login">Login</a>
            </li>
        </ul>
    </nav>
</header>
<main class="main-content">
    <div class="card-body">
        <h1>Search</h1>
        <h4>Search for Flight</h4>
        <form method="post" id="" action="">
            <script>
                function showDiv(divId, element)
                {
                    document.getElementById(divId).style.display = element.value === "return" ? 'inline-flex' : 'none';
                }
            </script>
            <!-- Return or one-way trip -->
            <div id="form-group-trip">
                <label for="type">Trip:</label>
                <select id="type" name="type" onchange="showDiv('form-group-return-date', this)">
                    <option value="oneway">One-way</option>
                    <option value="return">Return</option>
                </select>
            </div>

            <!-- Starting airport -->
            <div class="form-group">
                <label for="from">From:</label>
                <input list="locations" name="from" id="from" required>
            </div>

            <!-- Destination airport -->
            <div class="form-group">
                <label for="to">To:</label>
                <input list="locations" name="to" id="to" required>
            </div>

            <!-- Parse all available airport destinations -->
            <datalist id="destinations">
                <c:forEach items="${destinations}" var="airport">
                    <option value="<c:out value="${airport.code}"/>"><c:out value="${airport.name}"/></option>
                </c:forEach>
            </datalist>

            <!-- temp -->
            <datalist id="locations">
                <option value="SYD">Sydney - SYD</option>
                <option value="GIG">Rio De Janeiro - GIG</option>
                <option value="LAX">Los Angeles - LAX</option>
            </datalist>

            <!-- Depart date -->
            <div class="form-group">
                <label for="depart">Depart:</label>
                <jsp:useBean id="now" class="java.util.Date"/>
                <input type="date" id="depart" name="depart" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" required>
            </div>

            <!-- Return date -->
            <div id="form-group-return-date">
                <label for="return">Return:</label>
                <input type="date" id="return" name="return" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" required>
            </div>
            <br>
            <!--Search button-->
            <button class="btn btn-lg btn-outline-success text-uppercase" type="submit">Search</button>
        </form>
    </div>
</main>
</body>
</html>
