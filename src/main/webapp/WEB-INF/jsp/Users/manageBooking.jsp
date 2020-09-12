<%--
  JSP for viewing bookings and cancelling it
  SENG3150 Group 3
  Date: 26/05/2020
  Time: 11:12 am
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Booking</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">

   <%-- <script src="/js/dynamicLink.js"></script> --%>
</head>
<body>

<!-- session checker -->
<%-- <jsp:include page="sessionHandlerUser.jsp"/> --%>

<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">

        <h1>Manage Booking</h1>
        <h4>Booked flights</h4>


        <!-- user's booking/s-->
        <div class="wrap-bookings">


            <!--check if no booking -->
            <c:if test="${empty booking}">
                <h4>${message}</h4>
            </c:if>


            <!-- parse all booked flights -->
            <c:forEach items="${booking}" var ="booking">
            <div class="my-bookings">


                <!-- flight details -->
                <div class="manage-bookings">
                    <!-- checks if one-way or return -->
                    <c:if test="${booking.returnTrip == 0}">
                    <div class="register-row">
                        <div class="booking-input-center">ONE-WAY</div>
                    </div>
                    </c:if>
                    <c:if test="${booking.returnTrip == 1}">
                    <div class="register-row">
                        <div class="booking-input-center">RETURN</div>
                    </div>
                    </c:if>

                    <!-- checks group size -->
                    <c:if test="${booking.groupSize > 1}">
                    <div class="register-row">
                        <div class="booking-input-center">GROUP-BOOKING</div>
                    </div>
                    </c:if>

                <div class="register-row">
                    <div class="booking-input-center">Leg 1</div>
                </div>

                    <!-- departure and destination -->
                    <div class="register-group">
                        <div class="register-row1">
                            <div class="booking-info-left" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"> <c:out value= " ${booking.departure}"></c:out></div> <br>
                            <div class="booking-input-left" style="margin-top: 10px">Departure</div>
                        </div>
                        <div class="register-row1">
                            <div class="booking-info-right" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"><c:out value="${booking.destination}"></c:out></div> <br>
                            <div class="booking-input-right" style="margin-top: 10px">Destination</div>

                        </div>

                    </div>



                    <br>
                    <!-- departure and arrival date and time -->
                    <div class="register-group">
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.departureTime}" var="parsedDate" />
                            <div class="booking-input-left">Date and Time</div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "date" value = "${parsedDate}" /></div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate}" /></div>
                        </div>
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.arrivalTime}" var="parsedDate1" />
                            <div class="booking-input-right">Date and Time</div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "date" value = "${parsedDate2}" /></div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate1}" /></div>

                        </div>
                    </div>

                        <br>
                    <hr>

                    <!-- Passenger and class type -->
                <div class="register-group">
                    <div class="register-row1">

                            <div class="booking-input-left">Passenger</div><br>
                            <div class="booking-info-left"><c:out value="${booking.firstName}"> </c:out><c:out value="${booking.lastName}"></c:out></div>

                    </div>
                    <div class="register-row1">

                            <div class="booking-input-right">Class type</div></br>
                            <div class="booking-info-right"> <c:out value="${booking.classCode}"></c:out> </div>

                    </div>
                </div>

            <br>


                <!-- Airline and Flight Number -->
            <div class="register-group">
                <div class="register-row1">
                    <div class="booking-input-left">Airline</div></br>
                    <div class="booking-info-left"> <c:out value= " ${booking.airlineCode}"></c:out> </div>
                </div>
                <div class="register-row1">
                    <div class="booking-input-right">Flight Number</div></br>
                    <div class="booking-info-right"><c:out value= " ${booking.flightNumber}"></c:out> </div>
                </div>
            </div>



            <hr>



                <!-- Price -->

                    <div class="register-row">
                        <div class="booking-input-center">Price: <c:out value="${booking.price}"></c:out></div>
                    </div>

                </div>


                <!-- check if leg 2 -->
                <c:if test="${not empty booking.flightNumber2}">
                    <!-- flight details -->
                    <div class="manage-bookings">
                        <!-- checks if one-way or return -->
                        <c:if test="${booking.returnTrip == 0}">
                            <div class="register-row">
                                <div class="booking-input-center">ONE-WAY</div>
                            </div>
                        </c:if>
                        <c:if test="${booking.returnTrip == 1}">
                            <div class="register-row">
                                <div class="booking-input-center">RETURN</div>
                            </div>
                        </c:if>

                        <!-- checks group size -->
                        <c:if test="${booking.groupSize > 1}">
                            <div class="register-row">
                                <div class="booking-input-center">GROUP-BOOKING</div>
                            </div>
                        </c:if>

                        <div class="register-row">
                            <div class="booking-input-center">Leg 2</div>
                        </div>

                        <!-- departure and destination -->
                        <div class="register-group">
                            <div class="register-row1">
                                <div class="booking-info-left" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"> <c:out value= " ${booking.departure2}"></c:out></div> <br>
                                <div class="booking-input-left" style="margin-top: 10px">Departure</div>
                            </div>
                            <div class="register-row1">
                                <div class="booking-info-right" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"><c:out value="${booking.destination2}"></c:out></div> <br>
                                <div class="booking-input-right" style="margin-top: 10px">Destination</div>

                            </div>

                        </div>



                        <br>
                        <!-- departure and arrival date and time -->
                        <div class="register-group">
                            <div class="register-row1">
                                <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.departureTime2}" var="parsedDate2" />
                                <div class="booking-input-left">Date and Time</div> <br>
                                <div class="booking-info-left"><fmt:formatDate type = "date" value = "${parsedDate2}" /></div> <br>
                                <div class="booking-info-left"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate2}" /></div>
                            </div>
                            <div class="register-row1">
                                <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.arrivalTime2}" var="parsedDate22" />
                                <div class="booking-input-right">Date and Time</div> <br>
                                <div class="booking-info-right"><fmt:formatDate type = "date" value = "${parsedDate22}" /></div> <br>
                                <div class="booking-info-right"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate22}" /></div>

                            </div>
                        </div>

                        <br>
                        <hr>

                        <!-- Passenger and class type -->
                        <div class="register-group">
                            <div class="register-row1">

                                <div class="booking-input-left">Passenger</div><br>
                                <div class="booking-info-left"><c:out value="${booking.firstName}"> </c:out><c:out value="${booking.lastName}"></c:out></div>

                            </div>
                            <div class="register-row1">

                                <div class="booking-input-right">Class type</div></br>
                                <div class="booking-info-right"> <c:out value="${booking.classCode2}"></c:out> </div>

                            </div>
                        </div>

                        <br>


                        <!-- Airline and Flight Number -->
                        <div class="register-group">
                            <div class="register-row1">
                                <div class="booking-input-left">Airline</div></br>
                                <div class="booking-info-left"> <c:out value= " ${booking.airlineCode2}"></c:out> </div>
                            </div>
                            <div class="register-row1">
                                <div class="booking-input-right">Flight Number</div></br>
                                <div class="booking-info-right"><c:out value= " ${booking.flightNumber2}"></c:out> </div>
                            </div>
                        </div>



                        <hr>



                        <!-- Price -->

                        <div class="register-row">
                            <div class="booking-input-center">Price: <c:out value="${booking.price2}"></c:out></div>
                        </div>

                    </div>


                </c:if>

                <!-- check if leg 3 exist-->
                <c:if test="${not empty booking.flightNumber3}">
                    <!-- flight details -->
                    <div class="manage-bookings">
                        <!-- checks if one-way or return -->
                        <c:if test="${booking.returnTrip == 0}">
                            <div class="register-row">
                                <div class="booking-input-center">ONE-WAY</div>
                            </div>
                        </c:if>
                        <c:if test="${booking.returnTrip == 1}">
                            <div class="register-row">
                                <div class="booking-input-center">RETURN</div>
                            </div>
                        </c:if>

                        <!-- checks group size -->
                        <c:if test="${booking.groupSize > 1}">
                            <div class="register-row">
                                <div class="booking-input-center">GROUP-BOOKING</div>
                            </div>
                        </c:if>

                        <div class="register-row">
                            <div class="booking-input-center">Leg 3</div>
                        </div>

                        <!-- departure and destination -->
                        <div class="register-group">
                            <div class="register-row1">
                                <div class="booking-info-left" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"> <c:out value= " ${booking.departure3}"></c:out></div> <br>
                                <div class="booking-input-left" style="margin-top: 10px">Departure</div>
                            </div>
                            <div class="register-row1">
                                <div class="booking-info-right" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"><c:out value="${booking.destination3}"></c:out></div> <br>
                                <div class="booking-input-right" style="margin-top: 10px">Destination</div>

                            </div>

                        </div>



                        <br>
                        <!-- departure and arrival date and time -->
                        <div class="register-group">
                            <div class="register-row1">
                                <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.departureTime3}" var="parsedDate3" />
                                <div class="booking-input-left">Date and Time</div> <br>
                                <div class="booking-info-left"><fmt:formatDate type = "date" value = "${parsedDate3}" /></div> <br>
                                <div class="booking-info-left"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate3}" /></div>
                            </div>
                            <div class="register-row1">
                                <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.arrivalTime3}" var="parsedDate33" />
                                <div class="booking-input-right">Date and Time</div> <br>
                                <div class="booking-info-right"><fmt:formatDate type = "date" value = "${parsedDate33}" /></div> <br>
                                <div class="booking-info-right"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate33}" /></div>

                            </div>
                        </div>

                        <br>
                        <hr>

                        <!-- Passenger and class type -->
                        <div class="register-group">
                            <div class="register-row1">

                                <div class="booking-input-left">Passenger</div><br>
                                <div class="booking-info-left"><c:out value="${booking.firstName}"> </c:out><c:out value="${booking.lastName}"></c:out></div>

                            </div>
                            <div class="register-row1">

                                <div class="booking-input-right">Class type</div></br>
                                <div class="booking-info-right"> <c:out value="${booking.classCode3}"></c:out> </div>

                            </div>
                        </div>

                        <br>


                        <!-- Airline and Flight Number -->
                        <div class="register-group">
                            <div class="register-row1">
                                <div class="booking-input-left">Airline</div></br>
                                <div class="booking-info-left"> <c:out value= " ${booking.airlineCode3}"></c:out> </div>
                            </div>
                            <div class="register-row1">
                                <div class="booking-input-right">Flight Number</div></br>
                                <div class="booking-info-right"><c:out value= " ${booking.flightNumber3}"></c:out> </div>
                            </div>
                        </div>



                        <hr>



                        <!-- Price -->

                        <div class="register-row">
                            <div class="booking-input-center">Price: <c:out value="${booking.price3}"></c:out></div>
                        </div>

                    </div>
                </c:if>

                <!-- check if leg 4 exist -->
                <c:if test="${not empty booking.flightNumber4}">
                    <!-- flight details -->
                    <div class="manage-bookings">
                        <!-- checks if one-way or return -->
                        <c:if test="${booking.returnTrip == 0}">
                            <div class="register-row">
                                <div class="booking-input-center">ONE-WAY</div>
                            </div>
                        </c:if>
                        <c:if test="${booking.returnTrip == 1}">
                            <div class="register-row">
                                <div class="booking-input-center">RETURN</div>
                            </div>
                        </c:if>

                        <!-- checks group size -->
                        <c:if test="${booking.groupSize > 1}">
                            <div class="register-row">
                                <div class="booking-input-center">GROUP-BOOKING</div>
                            </div>
                        </c:if>

                        <div class="register-row">
                            <div class="booking-input-center">Leg 4</div>
                        </div>

                        <!-- departure and destination -->
                        <div class="register-group">
                            <div class="register-row1">
                                <div class="booking-info-left" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"> <c:out value= " ${booking.departure4}"></c:out></div> <br>
                                <div class="booking-input-left" style="margin-top: 10px">Departure</div>
                            </div>
                            <div class="register-row1">
                                <div class="booking-info-right" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"><c:out value="${booking.destination4}"></c:out></div> <br>
                                <div class="booking-input-right" style="margin-top: 10px">Destination</div>

                            </div>

                        </div>



                        <br>
                        <!-- departure and arrival date and time -->
                        <div class="register-group">
                            <div class="register-row1">
                                <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.departureTime4}" var="parsedDate4" />
                                <div class="booking-input-left">Date and Time</div> <br>
                                <div class="booking-info-left"><fmt:formatDate type = "date" value = "${parsedDate4}" /></div> <br>
                                <div class="booking-info-left"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate4}" /></div>
                            </div>
                            <div class="register-row1">
                                <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.arrivalTime4}" var="parsedDate44" />
                                <div class="booking-input-right">Date and Time</div> <br>
                                <div class="booking-info-right"><fmt:formatDate type = "date" value = "${parsedDate44}" /></div> <br>
                                <div class="booking-info-right"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDate44}" /></div>

                            </div>
                        </div>

                        <br>
                        <hr>

                        <!-- Passenger and class type -->
                        <div class="register-group">
                            <div class="register-row1">

                                <div class="booking-input-left">Passenger</div><br>
                                <div class="booking-info-left"><c:out value="${booking.firstName}"> </c:out><c:out value="${booking.lastName}"></c:out></div>

                            </div>
                            <div class="register-row1">

                                <div class="booking-input-right">Class type</div></br>
                                <div class="booking-info-right"> <c:out value="${booking.classCode4}"></c:out> </div>

                            </div>
                        </div>

                        <br>


                        <!-- Airline and Flight Number -->
                        <div class="register-group">
                            <div class="register-row1">
                                <div class="booking-input-left">Airline</div></br>
                                <div class="booking-info-left"> <c:out value= " ${booking.airlineCode4}"></c:out> </div>
                            </div>
                            <div class="register-row1">
                                <div class="booking-input-right">Flight Number</div></br>
                                <div class="booking-info-right"><c:out value= " ${booking.flightNumber4}"></c:out> </div>
                            </div>
                        </div>



                        <hr>



                        <!-- Price -->

                        <div class="register-row">
                            <div class="booking-input-center">Price: <c:out value="${booking.price4}"></c:out></div>
                        </div>

                    </div>
                </c:if>

            <div class="manage-bookings">
                <div class="register-row">
                    <div class="booking-input-center">Flight</div>
                    <div class="booking-input-center">Summary</div>
                </div>

                <!-- departure and destination -->
                <c:if test="${not empty booking.flightNumber && empty booking.flightNumber2}">
                    <div class="register-group">

                        <div class="register-row1">

                            <div class="booking-info-left" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"> <c:out value= " ${booking.departure}"></c:out></div> <br>
                            <div class="booking-input-left" style="margin-top: 10px">Departure</div>
                        </div>
                        <div class="register-row1">
                            <div class="booking-info-right" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"><c:out value="${booking.destination}"></c:out></div> <br>
                            <div class="booking-input-right" style="margin-top: 10px">Destination</div>

                        </div>

                    </div>

                    <br>
                    <div class="register-group">
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.departureTime}" var="parsed1" />
                            <div class="booking-input-left">Date and Time</div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "date" value = "${parsed1}" /></div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsed1}" /></div>
                        </div>
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.arrivalTime}" var="parsed11" />
                            <div class="booking-input-right">Date and Time</div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "date" value = "${parsed11}" /></div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsed11}" /></div>

                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty booking.flightNumber2 && empty booking.flightNumber3}">
                <div class="register-group">

                    <div class="register-row1">

                        <div class="booking-info-left" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"> <c:out value= " ${booking.departure}"></c:out></div> <br>
                        <div class="booking-input-left" style="margin-top: 10px">Departure</div>
                    </div>
                    <div class="register-row1">
                        <div class="booking-info-right" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"><c:out value="${booking.destination2}"></c:out></div> <br>
                        <div class="booking-input-right" style="margin-top: 10px">Destination</div>

                    </div>

                </div>

                    <br>
                    <div class="register-group">
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.departureTime}" var="parsed2" />
                            <div class="booking-input-left">Date and Time</div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "date" value = "${parsed2}" /></div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsed2}" /></div>
                        </div>
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.arrivalTime2}" var="parsed22" />
                            <div class="booking-input-right">Date and Time</div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "date" value = "${parsed22}" /></div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsed22}" /></div>

                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty booking.flightNumber3 && empty booking.flightNumber4}">
                    <div class="register-group">

                        <div class="register-row1">

                            <div class="booking-info-left" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"> <c:out value= " ${booking.departure}"></c:out></div> <br>
                            <div class="booking-input-left" style="margin-top: 10px">Departure</div>
                        </div>
                        <div class="register-row1">
                            <div class="booking-info-right" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"><c:out value="${booking.destination3}"></c:out></div> <br>
                            <div class="booking-input-right" style="margin-top: 10px">Destination</div>

                        </div>

                    </div>

                    <br>
                    <div class="register-group">
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.departureTime}" var="parsed3" />
                            <div class="booking-input-left">Date and Time</div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "date" value = "${parsed3}" /></div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsed3}" /></div>
                        </div>
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.arrivalTime3}" var="parsed33" />
                            <div class="booking-input-right">Date and Time</div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "date" value = "${parsed33}" /></div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsed33}" /></div>

                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty booking.flightNumber4}">
                    <div class="register-group">

                        <div class="register-row1">

                            <div class="booking-info-left" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"> <c:out value= " ${booking.departure}"></c:out></div> <br>
                            <div class="booking-input-left" style="margin-top: 10px">Departure</div>
                        </div>
                        <div class="register-row1">
                            <div class="booking-info-right" style="font-size: 25px; font-weight: bold; margin-bottom: -15px"><c:out value="${booking.destination4}"></c:out></div> <br>
                            <div class="booking-input-right" style="margin-top: 10px">Destination</div>

                        </div>

                    </div>
                    <br>
                    <div class="register-group">
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.departureTime}" var="parsed4" />
                            <div class="booking-input-left">Date and Time</div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "date" value = "${parsed4}" /></div> <br>
                            <div class="booking-info-left"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsed4}" /></div>
                        </div>
                        <div class="register-row1">
                            <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.arrivalTime4}" var="parsed44" />
                            <div class="booking-input-right">Date and Time</div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "date" value = "${parsed44}" /></div> <br>
                            <div class="booking-info-right"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsed44}" /></div>

                        </div>
                    </div>
                </c:if>
                <br>
                <hr>


                    <div class="register-row">
                        <fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${booking.bookingDate}" var="parsedDateStart" />
                        <div class="booking-input-right">Booking Date and Time</div> <br>
                        <div class="booking-info-right"><fmt:formatDate type = "date" value = "${parsedDateStart}" /></div> <br>
                        <div class="booking-info-right"><fmt:formatDate type = "time" dateStyle = "short" timeStyle = "short" value = "${parsedDateStart}" /></div>

                    </div>

                    <div class="register-row">
                        <div class="booking-input-left">Booking ID</div><br>
                        <div class="booking-info-left"><c:out value="${booking.bookingID}"></c:out></div>
                    </div>


                <br>
                <hr>

                <div class="register-row">
                    <div class="booking-input-center">Total Price: <c:out value="${booking.overallPrice}"></c:out></div>
                </div>

                <!-- cancel booking -->
                <div class="register-row">
                <form id="cancelForm" method="post" action="${pageContext.request.contextPath}/manageBooking/cancel">

                    <!-- userID -->
                    <input type ="hidden" id="userID" name="userID" value="<%=session.getAttribute("userId")%>"/>
                    <!-- booking ID -->
                    <input type ="hidden" id="bookingID" name="bookingID" value="<c:out value= "${booking.bookingID}"></c:out>" />
                    <button type="submit">Cancel</button>

                </form>
                </div>

            </div>

                <br>
            </div>
          </c:forEach>

        </div>
</main>

</body>
</html>
