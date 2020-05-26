<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <script src="/js/searchFormAssistor.js"></script>
</head>
<body>
<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <!-- The current search criteria -->
    <div id="search-criteria">
        <h4>Search Criteria</h4>
        <!-- Flight search fields -->
        <form name="searchFlight" method="get" id="searchFlight" action="/search" onsubmit="return validateForm()">
            <!-- Starting airport -->
            <div class="search-form-group">
                <input list="locations" name="departureLocation" id="departureLocation" value="<%=request.getParameter("departureLocation")%>" required>
                <p>to</p>
                <input list="locations" name="arrivalLocation" id="arrivalLocation" value="<%=request.getParameter("arrivalLocation")%>" required>
            </div>

            <!-- temp destinations -->
            <datalist id="locations">
                <option value="SYD">Sydney - SYD</option>
                <option value="GIG">Rio De Janeiro - GIG</option>
                <option value="LAX">Los Angeles - LAX</option>
            </datalist>

            <div class="search-form-group">
                <select id="type" name="type" onchange="showDiv('search-form-return-date', 'returnDate', this)">
                    <option value="oneway">One-way</option>
                    <option value="return">Return</option>
                </select>
            </div>

            <div class="search-form-group">
                <input type="number" id="adults" name="adults" min="1" max="9" value="<%=request.getParameter("adults")%>" required>
                <p>Adult/s</p>
                <input type="number" id="children" name="children" min="0" max="9" value="<%=request.getParameter("children")%>" required>
                <p>Children</p>
            </div>
            <br>

            <div class="search-form-group">
                <div class="home-form-group">
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <input type="date" id="departureDate" name="departureDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
                           onchange="restrictDepart()" value="<%=request.getParameter("departureDate")%>" required>
                </div>
            </div>

            <div id="search-form-return-date">
                <p>to</p>
                <input type="date" id="returnDate" name="returnDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
                       value="<%=request.getParameter("returnDate")%>" disabled>
            </div>

            <div id="sort-criteria">
                <h4>Sort Criteria</h4>
                <p>Sort by:</p>
                <select id="sortby" name="sortby">
                    <option value="price">Price</option>
                    <option value="time">Time</option>
                    <option value="class">Seat Class</option>
                    <option value="capacity">Capacity</option>
                    <option value="stopovers">Stop Overs</option>
                </select>
                <select id="sortMethod" name="sortMethod">
                    <option value="descending">Descending</option>
                    <option value="ascending">Ascending</option>
                </select>
            </div>

            <button class="btn btn-lg btn-outline-success text-uppercase" type="submit">Go</button>
        </form>
        <script>
            // Check if incoming search trip type, set type select field and return date div
            var trip = "<%=request.getParameter("type")%>";

            if (trip === "oneway")
            {
                document.getElementById("type").value = "oneway";
            }
            else if (trip === "return")
            {
                document.getElementById("type").value = "return";
                document.getElementById("search-form-return-date").style.display = 'inline';
                document.getElementById("returnDate").required = true;
                document.getElementById("returnDate").disabled = false;
            }
        </script>
    </div>

    <!-- Sort criteria
    <div id="sort-criteria">
        <h4>Sort Criteria</h4>
        <p>Sort By:</p>

    </div> -->

    <!-- Result template -->
    <div class="flight-result">
        <div class="flight-result-time">
            <p>Depart time</p>
            <h4>00:00AM</h4>
            <p>Arrival time</p>
            <h4>00:00AM</h4>
        </div>
        <div class="flight-result-details">
            <h4>Company</h4>
            <h4>Class Type</h4>
            <h4>Flight ID</h4>
        </div>
        <div class="flight-result-cost">
            <h3>$000</h3>
        </div>
        <div class="flight-result-book">
            <form action="/bookingtemp">
                <button type="submit">Book</button>
            </form>
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
                <form action="/bookingtemp">
                    <button type="submit">Book</button>
                </form>
            </div>
        </div>
    </c:forEach>
</main>
</body>
</html>
