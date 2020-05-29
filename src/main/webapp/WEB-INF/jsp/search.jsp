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
    <script src="/js/flightSearchAssistor.js"></script>
    <script src="/js/dynamicLink.js"></script>
</head>

<!-- session checker -->
<%
    String userID = (String)session.getAttribute("userId");

    //checks if user is logged in
    if(userID == null) {
        %> <body><%
    }else{
        %><body onload=userPage('/logout','Logout');> <%
    }%>


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
                <input list="destinations" name="departureLocation" id="departureLocation" value="<%=request.getParameter("departureLocation")%>" required>
                <p>to</p>
                <input list="destinations" name="arrivalLocation" id="arrivalLocation" value="<%=request.getParameter("arrivalLocation")%>" required>
            </div>

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

    <!-- Result template for return trips -->
    <div class="flight-result-return">
        <div class="flight-result-return-departure">
            <h4>Select a departure flight</h4>
        </div>
        <div class="flight-result-return-return">
            <h4>Select a return flight</h4>
        </div>
    </div>
    <form name="bookFlight" method="get" id="bookFlight" action="/bookingtemp" onsubmit="return validateBookingForm()">
        <div class="flight-result-return">
            <!-- Departure flight -->
            <div class="flight-result-return-departure">
                <div class="flight-result-time">
                    <p>Depart time</p>
                    <h4>00:00AM</h4>
                    <p>Arrival time</p>
                    <h4>00:00AM</h4>
                </div>
                <div class="flight-result-details">
                    <h4>Company</h4>
                    <h4>Stop Overs</h4>
                    <h4>Flight ID</h4>
                </div>
                <div class="flight-result-cost">
                    <h3>$000</h3>
                </div>
                <div class="flight-result-book">
                    <label for="flightid1">Select: </label>
                    <input type="radio" id="flightid1" name="departure" value="flightid1">
                </div>
            </div>

            <!-- Return flight -->
            <div class="flight-result-return-return">
                <div class="flight-result-time">
                    <p>Depart time</p>
                    <h4>00:00AM</h4>
                    <p>Arrival time</p>
                    <h4>00:00AM</h4>
                </div>
                <div class="flight-result-details">
                    <h4>Company</h4>
                    <h4>Stop Overs</h4>
                    <h4>Flight ID</h4>
                </div>
                <div class="flight-result-cost">
                    <h3>$000</h3>
                </div>
                <div class="flight-result-book">
                    <label for="flightid2">Select: </label>
                    <input type="radio" id="flightid2" name="return" value="flightid2">
                </div>
            </div>
        </div>

        <div id="return-book">
            <button type="submit">Book</button>
        </div>
    </form>

    <c:if test = "${param.type eq 'oneway'}">
        <p>One way</p>
        <!-- Parse all returned flights -->
        <c:forEach items="${departureFlights}" var="flight">
            <div class="flight-result-oneway">
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
    </c:if>
    <c:if test = "${param.type eq 'return'}">
        <p>Return</p>
    </c:if>
</main>
</body>
</html>
