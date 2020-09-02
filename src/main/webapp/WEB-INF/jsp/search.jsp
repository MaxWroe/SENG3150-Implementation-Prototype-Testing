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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>
<!-- Session handler -->
<%--<jsp:include page="sessionHandlerGuest.jsp"/>--%>

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

                // on load if search criteria being passed
                $(document).ready(function() {
                    // Set fields to incoming search parameters
                    $("#classCode").val("${param.classCode}");
                    $("#adults").val("${param.adults}");
                    $("#children").val("${param.children}");
                    $("#departureLocation").val("${param.departureLocation}");
                    $("#arrivalLocation").val("${param.arrivalLocation}");
                    $("#departureDate").val("${param.departureDate}");
                    $("#returnDate").val("${param.returnDate}");

                    $("#departureDateRange").val("${param.departureDateRange}");

                    // check if departure range option is selected
                    if($('#departureDateRange').val() !== "")
                    {
                        $("#depart-range").prop( "checked", true );
                        $("#departureDateRange").prop( "disabled", false );

                        $(this).toggleClass("up");
                        $('.extra-search-fields').toggle();

                        restrict_departure_range();

                        // change visibility of departure date range div
                        $("#depart-range-div").toggle(this.checked);
                        // make departure date range input required if option selected
                        $('#departureDateRange').prop( "required", true );
                    }

                    if (trip === "oneway")
                    {
                        $("#type").val("oneway");
                        $("#form-group-return-date").hide();
                    }
                    else if (trip === "return")
                    {
                        $("#type").val("return");
                        $("#form-group-return-date").style.display = 'inline';
                        $("#returnDate").prop("required", true);
                        $("#returnDate").prop( "disabled", false );
                    }
                });
            </script>
        </div>
        <div id="search-criteria">
            <!-- Apply sorting -->
            <div id="sort-criteria">
                <h4 style="display: inline;padding-right: 50px;">Sort Criteria</h4>
                <p style="display: inline">Sort by:</p>
                <select class="sortType">
                    <option value="price">Price</option>
                    <option value="duration">Total Duration</option>
                    <option value="capacity">Capacity</option>
                    <option value="stopovers">Stop Overs</option>
                </select>
                <select class="sortMethod">
                    <option value="descending">Descending</option>
                    <option value="ascending">Ascending</option>
                </select>
                <button id="sort">Sort</button>
            </div>
            <script>
                let method;
                let type;
                function sort_li(a, b) {
                    if(method === "descending")
                    {
                        return ($(b).data(type)) > ($(a).data(type)) ? 1 : -1;
                    }
                    if(method === "ascending")
                    {
                        return ($(b).data(type)) < ($(a).data(type)) ? 1 : -1;
                    }
                }
                $("#sort").on('click', function(){
                    method = $('select.sortMethod').children("option:selected").val();
                    type = $('select.sortType').children("option:selected").val();

                    $(".flight-list").children('li').sort(sort_li).appendTo(".flight-list");
                });
            </script>
        </div>
        <div id="search-sidebar">
            <p>Side filters</p>
            <p>Stop Overs</p>
            <label for="stop1">1</label>
            <input type="checkbox" id="stop1" name="stopsFilter" value="1">
            <label for="stop2">2</label>
            <input type="checkbox" id="stop2" name="stopsFilter" value="2">
            <p>Airlines</p>
            <div id="airlineFilterDiv">
            </div>
        </div>
        <script>
            // array of airlines and their id
            let airlines = [
                ['AA','American Airlines'],
                ['AC','Air Canada'],
                ['AF','Air France'],
                ['AI','Air India'],
                ['AM','Air Mexico'],
                ['AR','Aerolineas Argentinas'],
                ['AY','Finnair'],
                ['BA','British Airways'],
                ['CA','Air China'],
                ['CI','China Airlines'],
                ['CO','Continental Airlines'],
                ['CX','Cathay Pacific Airways'],
                ['DJ','Virgin Blue'],
                ['DL','Delta Air Lines'],
                ['EI','Aer Lingus'],
                ['EK','Qatar Airways'],
                ['IB','Iberia'],
                ['JL','Japan Airlines'],
                ['JQ','Jetstar Airlines'],
                ['KE','Korean Airlines'],
                ['KL','KLM-Royal Dutch Airlines'],
                ['LH','Lufthansa'],
                ['LY','El Al Israel Airlines'],
                ['MH','Malaysia Airlines'],
                ['MS','Egyptair'],
                ['MX','Mexicana de Aviacion'],
                ['NA','North American Airlines'],
                ['NW','Northwest Airlines'],
                ['NZ','Air New Zealand'],
                ['OS','Austrian Airlines'],
                ['PR','Philippine Airlines'],
                ['QF','Qantas Airways'],
                ['QR','Emirates Airlines'],
                ['RJ','Royal Jordanian'],
                ['SA','South African'],
                ['SK','SAS-Scandinavian Airlines'],
                ['SQ','Singapore Airlines'],
                ['SU','Aeroflot'],
                ['TG','Thai Airways'],
                ['TK','Turkish Airlines'],
                ['TW','Trans World Airlines'],
                ['UA','United Airlines'],
                ['VH','Aeropostal Alas de Venezuela'],
                ['VS','Virgin Atlantic Airways']
            ];
            // array of all airlines returned from search
            let returnedAirlines = $(".flight-list li").map(function() {
                return $(this).data("airline");
            }).get();
            // don't display airlines not in search in side filters
            for (let i = airlines.length - 1; i >= 0; i--) {
                if(!returnedAirlines.includes(airlines[i][0]))
                {
                    airlines.splice(i, 1);
                    console.log(airlines);
                }
            }
            // create airline filter inputs
            for (let i = 0; i < airlines.length; i++) {
                let label = "<label for=\""+airlines[i][0]+"\">"+airlines[i][1]+"</label>";
                let input = "<input type=\"checkbox\" id=\""+airlines[i][0]+"\" value=\""+airlines[i][0]+"\" name=\"airlineFilter\">";
                $("#airlineFilterDiv").append(label);
                $("#airlineFilterDiv").append(input);
            }
        </script>
        <script>
            var $select = $("#search-sidebar input:checkbox");
            $select.change(function () {
                var include = "";

                $select.each(function () {
                    if ($(this).prop("checked"))
                    {
                        var val = $(this).val();

                        switch ($(this).attr("name")) {
                            case "airlineFilter":
                                include += "[data-airline='" + val + "']";
                                break;
                            case "stopsFilter":
                                include += "[data-stops='" + val + "']";
                                break;
                        }
                    }
                });
                if (include === "")
                {
                    $(".flight-list li").show();
                }
                else {
                    $(".flight-list li").hide();
                    $(".flight-list li").filter(include).show();
                }
            });
        </script>

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
            <ul class="flight-list">
                <c:forEach items="${flights.flightPlansDeparting}" var="flightPlan">
                    <li data-price="${flightPlan.price}" data-duration="${flightPlan.durationTotal}" data-stopovers="${flightPlan.numberStopOvers}"
                        data-airline="test" data-stops="${flightPlan.numberStopOvers}">

                        <div class="flight-result-oneway">
                            <div class="flight-result-depart-time">
                                <fmt:parseDate pattern="yyyy-MM-dd hh:mm:ss" value="${flightPlan.departureDate}" var="parsedDate" />

                                <h4><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate}" /></h4>
                                <p><fmt:formatDate type = "date" value = "${parsedDate}" /></p>
                            </div>
                            <div class="flight-result-stopovers">
                                <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                                <table>
                                    <tr>
                                        <td>${param.departureLocation} --></td>
                                        <c:forEach items="${flightPlan.flights}" var="flightPlanFlights">
                                            <td>${flightPlanFlights.stopOverCode}</td>
                                        </c:forEach>
                                        <td>--> ${param.arrivalLocation}</td>
                                    </tr>
                                </table>
                                <p>Total duration: ${flightPlan.durationTotal} hours</p>
                            </div>
                            <div class="flight-result-arrival-time">
                                <fmt:parseDate pattern="yyyy-MM-dd hh:mm:ss" value="${flightPlan.arrivalDate}" var="parsedDate" />

                                <h4><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate}" /></h4>
                                <p><fmt:formatDate type = "date" value = "${parsedDate}" /></p>
                            </div>
                            <div class="flight-result-cost">
                                <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                                <!-- Information to send to booking controller -->
                                <form action="${pageContext.request.contextPath}/flightBookingOneway" method="post">
                                    <input type="hidden" id="onewayBooking" name="trip" value="oneway">
                                    <input type="hidden" id="onewayAdultsBooking" name="onewayAdultsBooking" value="${param.adults}">
                                    <input type="hidden" id="onewayChildrenBooking" name="onewayChildrenBooking" value="${param.children}">
                                    <input type="hidden" id="onewayClassBooking" name="onewayClassBooking" value="${param.classCode}">
                                    <!-- Position of the specific flight plan within the FlightHolder FlightPlans list -->
                                    <input type="hidden" id="onewayFlightPlan${flightPlan.position}" name="flightPlan" value="${flightPlan.position}">
                                    <button type="submit">$${flightPlan.price}</button>
                                </form>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:if>

        <!-- If searched trip is return -->
        <c:if test = "${param.type eq 'return'}">
            <div id="flight-result-return-trip">
                <div id="flight-result-departure-header">
                    <h4 style="margin: 0;">Select a departure flight</h4>
                </div>
                <div id="flight-result-return-header">
                    <h4 style="margin: 0;">Select a return flight</h4>
                </div>


                <!-- Set flights returned (FlightHolders) to session scope -->
                <c:set var = "departureFlights" scope = "session" value = "${flights.flightPlansDeparting}"/>
                <c:set var = "returnFlights" scope = "session" value = "${flights.flightPlansReturning}"/>

                <!-- Information to send to booking controller -->
                <form method="post" action="${pageContext.request.contextPath}/flightBookingReturn" onsubmit="return validateFlightSelection()"
                      style="display:contents">
                    <input type="hidden" id="returnBooking" name="trip" value="return">
                    <input type="hidden" id="returnAdultsBooking" name="returnAdultsBooking" value="${param.adults}">
                    <input type="hidden" id="returnChildrenBooking" name="returnChildrenBooking" value="${param.children}">
                    <input type="hidden" id="returnClassBooking" name="returnClassBooking" value="${param.classCode}">

                    <!-- Parse all returned flights -->

                    <!-- Check if any departure flights returned -->
                    <c:if test="${empty flights.flightPlansDeparting}">
                        <div class="flight-result-departure-window">
                            <h4>No departure flights can be found that match the criteria!</h4>
                        </div>
                    </c:if>

                    <!-- For each departure flight returned display -->
                    <div class="flight-result-departure-window">
                        <ul class="flight-list">
                        <c:forEach items="${flights.flightPlansDeparting}" var="flightPlan">
                            <li data-price="${flightPlan.price}" data-duration="${flightPlan.durationTotal}" data-stopovers="${flightPlan.numberStopOvers}"
                                data-airline="test" data-stops="${flightPlan.numberStopOvers}">
                                <div class="flight-result-return-trip-flight">
                                    <div class="flight-result-depart-time">
                                        <fmt:parseDate pattern="yyyy-MM-dd hh:mm:ss" value="${flightPlan.departureDate}" var="parsedDate" />

                                        <h4><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate}" /></h4>
                                        <p><fmt:formatDate type = "date" value = "${parsedDate}" /></p>
                                    </div>
                                    <div class="flight-result-stopovers">
                                        <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                                        <table>
                                            <tr>
                                                <td>${param.departureLocation} --></td>
                                                <c:forEach items="${flightPlan.flights}" var="flightPlanFlights">
                                                    <td>${flightPlanFlights.stopOverCode}</td>
                                                </c:forEach>
                                                <td>--> ${param.arrivalLocation}</td>
                                            </tr>
                                        </table>
                                        <p>Total duration: ${flightPlan.durationTotal} hours</p>
                                    </div>
                                    <div class="flight-result-arrival-time">
                                        <fmt:parseDate pattern="yyyy-MM-dd hh:mm:ss" value="${flightPlan.arrivalDate}" var="parsedDate" />

                                        <h4><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate}" /></h4>
                                        <p><fmt:formatDate type = "date" value = "${parsedDate}" /></p>
                                    </div>
                                    <div class="flight-result-cost">
                                        <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                                        <!-- Information to send to booking controller -->
                                        <label for="returnDepartureFlightPlan${flightPlan.position}">Select: </label>
                                        <input type="radio" id="returnDepartureFlightPlan${flightPlan.position}"
                                               name="departure" value="${flightPlan.position}" onchange="updateCost('departure', '${flightPlan.price}')">
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>

                    <!-- Check if any return flights returned -->
                    <c:if test="${empty flights.flightPlansReturning}">
                        <div class="flight-result-return-window">
                            <h4>No return flights can be found that match the criteria!</h4>
                        </div>
                    </c:if>

                    <!-- For each return flight returned display -->
                    <div class="flight-result-return-window">
                        <ul class="flight-list">
                        <c:forEach items="${flights.flightPlansReturning}" var="flightPlan">
                            <li data-price="${flightPlan.price}" data-duration="${flightPlan.durationTotal}" data-stopovers="${flightPlan.numberStopOvers}"
                                data-airline="test" data-stops="${flightPlan.numberStopOvers}">
                                <div class="flight-result-return-trip-flight">
                                    <div class="flight-result-depart-time">
                                        <fmt:parseDate pattern="yyyy-MM-dd hh:mm:ss" value="${flightPlan.departureDate}" var="parsedDate" />

                                        <h4><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate}" /></h4>
                                        <p><fmt:formatDate type = "date" value = "${parsedDate}" /></p>
                                    </div>
                                    <div class="flight-result-stopovers">
                                        <p>Stop overs: ${flightPlan.numberStopOvers}</p>
                                        <table>
                                            <tr>
                                                <td>${param.departureLocation} --></td>
                                                <c:forEach items="${flightPlan.flights}" var="flightPlanFlights">
                                                    <td>${flightPlanFlights.stopOverCode}</td>
                                                </c:forEach>
                                                <td>--> ${param.arrivalLocation}</td>
                                            </tr>
                                        </table>
                                        <p>Total duration: ${flightPlan.durationTotal} hours</p>
                                    </div>
                                    <div class="flight-result-arrival-time">
                                        <fmt:parseDate pattern="yyyy-MM-dd hh:mm:ss" value="${flightPlan.arrivalDate}" var="parsedDate" />

                                        <h4><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate}" /></h4>
                                        <p><fmt:formatDate type = "date" value = "${parsedDate}" /></p>
                                    </div>
                                    <div class="flight-result-cost">
                                        <p>Available seats: ${flightPlan.numberAvailableSeats}</p>
                                        <!-- Position of the specific flight plan within the FlightHolder FlightPlans list -->
                                        <label for="returnReturnFlightPlan${flightPlan.position}">Select: </label>
                                        <input type="radio" id="returnReturnFlightPlan${flightPlan.position}" name="return" value="${flightPlan.position}" onchange="updateCost('return', '${flightPlan.price}')">
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>

                    <br>
                    <div id="flight-result-return-book">
                        <h4 id="booking-cost">Total cost: </h4>
                        <button type="submit" id="return-flight-book">Book</button>
                    </div>
                </form>
            </div>
        </c:if>
    </div>
</main>
</body>
</html>
