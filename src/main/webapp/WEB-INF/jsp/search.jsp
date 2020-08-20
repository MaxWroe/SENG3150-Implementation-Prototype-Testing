<%--
  JSP for searching, sorting and displaying flight plans.
  SENG3150 Group 3
  Date: 19/05/2020
  Time: 2:53 pm
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Page</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">

    <script src="${pageContext.request.contextPath}/js/flightSelectAssistor.js"></script>
    <script src="${pageContext.request.contextPath}/js/dynamicLink.js"></script>
</head>
<body>
<!-- Session handler -->
<jsp:include page="sessionHandlerGuest.jsp"/>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<!-- Page content -->
<main class="main-content">
    <!-- The current search criteria -->
    <div id="search-grid">
        <div id="search-overlay">
            <jsp:include page="searchForm.jsp"/>
        </div>
        <script>
            function on() {
                document.getElementById("search-overlay").style.display = "block";
            }

            function off() {
                document.getElementById("search-overlay").style.display = "none";
            }
        </script>
        <div id="results-search">
            <table>
                <tr>
                    <td>Search Criteria: <button onclick="on()">Change Search</button></td>
                    <td>${param.type}, ${param.classCode}</td>
                    <td>${param.adults} Adult/s, ${param.children} Children</td>
                    <td>${param.departureLocation} to ${param.arrivalLocation}</td>
                    <td>Depart ${param.departureDate}</td>
                </tr>
            </table>

            <script>
                // Check if incoming search trip type, set type select field and return date div
                var trip = "${param.type}";

                // Set fields to incoming search parameters
                document.getElementById("classCode").value = "${param.classCode}";
                document.getElementById("adults").value = "${param.adults}";
                document.getElementById("children").value = "${param.children}";
                document.getElementById("departureLocation").value = "${param.departureLocation}";
                document.getElementById("arrivalLocation").value = "${param.arrivalLocation}";
                document.getElementById("departureDate").value = "${param.departureDate}";
                document.getElementById("returnDate").value = "${param.returnDate}";

                if (trip === "oneway")
                {
                    document.getElementById("type").value = "oneway";
                    document.getElementById("form-group-return-date").style.display = 'none';
                }
                else if (trip === "return")
                {
                    document.getElementById("type").value = "return";
                    document.getElementById("form-group-return-date").style.display = 'inline';
                    document.getElementById("returnDate").required = true;
                    document.getElementById("returnDate").disabled = false;
                }

                // Set class type to incoming type
                document.getElementById("classCode").value = "${param.classCode}";
            </script>
        </div>
        <div id="search-criteria">
            <!-- Apply sorting -->
            <div id="sort-criteria">
                <h4 style="display: inline;padding-right: 50px;">Sort Criteria</h4>
                <p>Sort by:</p>
                <select class="sortby">
                    <option value="price">Price</option>
                    <option value="time">Time</option>
                    <option value="capacity">Capacity</option>
                    <option value="stopovers">Stop Overs</option>
                </select>
                <select class="sortMethod">
                    <option value="descending">Descending</option>
                    <option value="ascending">Ascending</option>
                </select>
                <button id="sort">Sort</button>
            </div>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
            <script>
                let method;
                function Price_sort(a, b) {
                    if(method === "descending")
                    {
                        return ($(b).data('price')) > ($(a).data('price')) ? 1 : -1;
                    }
                    if(method === "ascending")
                    {
                        return ($(b).data('price')) < ($(a).data('price')) ? 1 : -1;
                    }
                }
                $("#sort").on('click', function(){
                    method = $('select.sortMethod').children("option:selected").val();
                    $(".fro").children('li').sort(Price_sort).appendTo(".fro");
                });
            </script>
        </div>
        <div id="search-sidebar">
            <p>Side filters</p>
        </div>

        <!-- If searched trip is one-way -->
        <c:if test = "${param.type eq 'oneway'}">
            <!-- Parse all returned flights -->

            <!-- Check if any flights returned -->
            <c:if test="${empty flights.flightPlansDeparting}">
                <h4>No flights can be found that match the criteria!</h4>
            </c:if>

            <!-- Set flights returned (FlightHolder) to session scope -->
            <c:set var = "departureFlights" scope = "session" value = "${flights.flightPlansDeparting}"/>

            <!-- For each flight returned display -->
            <ul class="fro">
            <c:forEach items="${flights.flightPlansDeparting}" var="flightPlan">
                <li data-price="${flightPlan.price}">

                <div class="flight-result-oneway">
                    <div class="flight-result-time">
                        <p>Depart time</p>
                        <h4>${flightPlan.departureDate}</h4>
                        <p>Arrival time</p>
                        <h4>${flightPlan.arrivalDate}</h4>
                    </div>
                    <div class="flight-result-details">
                        <%--
                        <p>${flightPlan.airlines}</p>
                        --%>
                        <br>
                        <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                        <br>
                            <c:forEach items="${flightPlan.flights}" var="flightPlanFlights">
                                <p>Stop over airports:</p>
                                <h4>${flightPlanFlights.stopOverCode}</h4>
                            </c:forEach>
                        <br>
                        <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                    </div>
                    <div class="flight-result-cost">
                        <h3>$${flightPlan.price}</h3>
                    </div>
                    <div class="flight-result-book">
                        <!-- Information to send to booking controller -->
                        <form action="${pageContext.request.contextPath}/flightBookingOneway" method="post">
                            <input type="hidden" id="onewayBooking" name="trip" value="oneway">
                            <input type="hidden" id="onewayAdultsBooking" name="onewayAdultsBooking" value="${param.adults}">
                            <input type="hidden" id="onewayChildrenBooking" name="onewayChildrenBooking" value="${param.children}">
                            <input type="hidden" id="onewayClassBooking" name="onewayClassBooking" value="${param.classCode}">
                            <!-- Position of the specific flight plan within the FlightHolder FlightPlans list -->
                            <input type="hidden" id="onewayFlightPlan${flightPlan.position}" name="flightPlan" value="${flightPlan.position}">
                            <button type="submit">Book</button>
                        </form>
                    </div>
                </div>

                </li>
            </c:forEach>
            </ul>
        </c:if>

        <!-- If searched trip is return -->
        <c:if test = "${param.type eq 'return'}">
            <div class="flight-result-return">
                <div class="flight-result-return-departure" style="background: none">
                    <h4 style="margin: 0;">Select a departure flight</h4>
                </div>
                <div class="flight-result-return-return" style="background: none">
                    <h4 style="margin: 0;">Select a return flight</h4>
                </div>
            </div>

            <!-- Set flights returned (FlightHolders) to session scope -->
            <c:set var = "departureFlights" scope = "session" value = "${flights.flightPlansDeparting}"/>
            <c:set var = "returnFlights" scope = "session" value = "${flights.flightPlansReturning}"/>

            <!-- Information to send to booking controller -->
            <form method="post" action="${pageContext.request.contextPath}/flightBookingReturn" onsubmit="return validateFlightSelection()">
                <input type="hidden" id="returnBooking" name="trip" value="return">
                <input type="hidden" id="returnAdultsBooking" name="returnAdultsBooking" value="${param.adults}">
                <input type="hidden" id="returnChildrenBooking" name="returnChildrenBooking" value="${param.children}">
                <input type="hidden" id="returnClassBooking" name="returnClassBooking" value="${param.classCode}">

                <div class="flight-result-return">
                    <!-- Parse all returned flights -->

                    <!-- Check if any departure flights returned -->
                    <c:if test="${empty flights.flightPlansDeparting}">
                        <div class="flight-result-return-windows">
                            <h4>No departure flights can be found that match the criteria!</h4>
                        </div>
                    </c:if>

                    <!-- For each departure flight returned display -->
                    <div id="flight-departure-results">
                        <c:forEach items="${flights.flightPlansDeparting}" var="flightPlan">
                            <div class="flight-result-return-windows">
                                <div class="flight-result-time">
                                    <p>Depart time</p>
                                    <h4>${flightPlan.departureDate}</h4>
                                    <p>Arrival time</p>
                                    <h4>${flightPlan.arrivalDate}</h4>
                                </div>
                                <div class="flight-result-details">
                                    <%--
                                    <p>${flightPlan.airlines}</p>
                                    --%>
                                    <br>
                                    <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                                    <br>
                                    <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                                </div>
                                <div class="flight-result-cost">
                                    <h3>$${flightPlan.price}</h3>
                                </div>
                                <div class="flight-result-book">
                                    <!-- Position of the specific flight plan within the FlightHolder FlightPlans list -->
                                    <label for="returnDepartureFlightPlan${flightPlan.position}">Select: </label>
                                    <input type="radio" id="returnDepartureFlightPlan${flightPlan.position}" name="departure" value="${flightPlan.position}" onchange="updateCost('departure', '${flightPlan.price}')">
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Check if any return flights returned -->
                    <c:if test="${empty flights.flightPlansReturning}">
                        <div class="flight-result-return-windows">
                            <h4>No return flights can be found that match the criteria!</h4>
                        </div>
                    </c:if>

                    <!-- For each return flight returned display -->
                    <div id="flight-return-results">
                        <c:forEach items="${flights.flightPlansReturning}" var="flightPlan">
                            <div class="flight-result-return-windows">
                                <div class="flight-result-time">
                                    <p>Depart time</p>
                                    <h4>${flightPlan.departureDate}</h4>
                                    <p>Arrival time</p>
                                    <h4>${flightPlan.arrivalDate}</h4>
                                </div>
                                <div class="flight-result-details">
                                    <%--
                                    <p>${flightPlan.airlines}</p>
                                    --%>
                                    <br>
                                    <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                                    <br>
                                    <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                                </div>
                                <div class="flight-result-cost">
                                    <h3>$${flightPlan.price}</h3>
                                </div>
                                <div class="flight-result-book">
                                    <!-- Position of the specific flight plan within the FlightHolder FlightPlans list -->
                                    <label for="returnReturnFlightPlan${flightPlan.position}">Select: </label>
                                    <input type="radio" id="returnReturnFlightPlan${flightPlan.position}" name="return" value="${flightPlan.position}" onchange="updateCost('return', '${flightPlan.price}')"></div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <br>
                <div id="flight-result-return-book">
                    <h4 id="booking-cost">Total cost: </h4>
                    <button type="submit" id="return-flight-book">Book</button>
                </div>
            </form>
        </c:if>
    </div>
</main>
</body>
</html>
