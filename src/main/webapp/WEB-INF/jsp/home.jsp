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

    <script src="/js/searchFormAssistor.js"></script>
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
        <form name="searchFlight" method="get" id="searchFlight" action="/search" onsubmit="return validateForm()">
            <!-- Return or one-way trip -->
            <div id="home-form-group-trip">
                <label for="type">Trip:</label>
                <select id="type" name="type" onchange="showDiv('home-form-group-return-date', 'returnDate', this)">
                    <option value="oneway">One-way</option>
                    <option value="return">Return</option>
                </select>
            </div>

            <!-- Ticket class -->
            <label for="classCode">Class:</label>
            <select id="classCode" name="classCode">
                <option value="ECO">Economy</option>
                <option value="PME">Premium Economy</option>
                <option value="BUS">Business Class</option>
                <option value="FIR">First Class</option>
            </select>
            <br>

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
                <label for="departureLocation">From:</label>
                <input list="destinations" name="departureLocation" id="departureLocation" required>
            </div>

            <!-- Destination airport -->
            <div class="home-form-group">
                <label for="arrivalLocation">To:</label>
                <input list="destinations" name="arrivalLocation" id="arrivalLocation" required>
            </div>
            <br>

            <!-- Depart date -->
            <div class="home-form-group">
                <label for="departureDate">Depart:</label>
                <jsp:useBean id="now" class="java.util.Date"/>
                <input type="date" id="departureDate" name="departureDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
                       onchange="restrictDepart()" required>
            </div>

            <!-- Return date -->
            <div id="home-form-group-return-date">
                <label for="returnDate">Return:</label>
                <input type="date" id="returnDate" name="returnDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" disabled>
            </div>
            <br>

            <!-- Search button -->
            <button class="btn btn-lg btn-outline-success text-uppercase" type="submit">Search</button>

            <!-- Airport destinations -->
            <datalist id="destinations">
                <option value="ADL">Adelaide - ADL</option>
                <option value="AMS">Amsterdam - AMS</option>
                <option value="ATL">Atlanta - ATL</option>
                <option value="BKK">Bangkok - BKK</option>
                <option value="BNE">Brisbane - BNE</option>
                <option value="CBR">Canberra - CBR</option>
                <option value="CDG">Paris - Charles De Gaulle - CDG</option>
                <option value="CNS">Cairns - CNS</option>
                <option value="DOH">Doha - DOH</option>
                <option value="DRW">Darwin - DRW</option>
                <option value="DXB">Dubai - DXB</option>
                <option value="FCO">Rome-Fiumicino - FCO</option>
                <option value="GIG">Rio De Janeiro - GIG</option>
                <option value="HBA">Hobart - HBA</option>
                <option value="HEL">Helsinki - HEL</option>
                <option value="HKG">Hong Kong - HKG</option>
                <option value="HNL">Honolulu - HNL</option>
                <option value="JFK">New York - JFK - JFK</option>
                <option value="JNB">Johannesburg - JNB</option>
                <option value="KUL">Kuala Lumpur - KUL</option>
                <option value="LAX">Los Angeles - LAX</option>
                <option value="LGA">New York - Laguardia - LGA</option>
                <option value="LGW">London-Gatwick - LGW</option>
                <option value="LHR">London-Heathrow - LHR</option>
                <option value="MAD">Madrid - MAD</option>
                <option value="MEL">Melbourne - MEL</option>
                <option value="MIA">Miami - MIA</option>
                <option value="MUC">Munich - MUC</option>
                <option value="NRT">Tokyo - Narita - NRT</option>
                <option value="OOL">Gold Coast - OOL</option>
                <option value="ORD">Chicago - OHare Intl. - ORD</option>
                <option value="ORY">Paris - Orly - ORY</option>
                <option value="PER">Perth - PER</option>
                <option value="SFO">San Francisco - SFO</option>
                <option value="SIN">Singapore - SIN</option>
                <option value="SYD">Sydney - SYD</option>
                <option value="VIE">Vienna - VIE</option>
                <option value="YYZ">Toronto - YYZ</option>
            </datalist>
        </form>
    </div>
</main>
</body>
</html>
