
<%--
  JSP for airline management, sponsor and unsponsor
  SENG3150 Group 3
  Date: 5/09/2020
  Time: 7:49 am
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Airline Management</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

    <main class="main-content">
            <h1>Airline Management</h1>
            <h4>List of Airlines</h4>

        <div class="my-airlines">
            <%-- preferred parsing all airlines --%>
             <div class="wrap-airlines">

                <c:forEach items="${airlines}" var ="airlines">




                    <%-- if sponsored --%>


                        <c:if test="${airlines.sponsored == 1}">

                            <div class="manage-airlines">
                            <div class="register-row">
                                <div class="booking-info-left" style="font-weight: bold"><c:out value= "${airlines.airlineName}"></c:out></div> <br>
                                <div class="booking-input-left">Airline</div>
                            </div>
                            <div class="register-row">
                                <div class="booking-info-right" style="font-weight: bold"><c:out value= "${airlines.countryCode3}"></c:out></div> <br>
                                <div class="booking-input-right">Country</div>
                            </div>

                            <hr>
                            <br>
                            <div class="register-row">
                                <div class="booking-input-center" style="font-style: italic"><c:out value= "Sponsored"></c:out></div>
                            </div>
                            <br>
                            <div class="unsponsor-airline">
                                <form id="unsponsorAirline" method="post" action="${pageContext.request.contextPath}/manageAirline/unsponsor">
                                    <input type ="hidden" id="airlineName1" name="airlineName1" value="${airlines.airlineName}"/>
                                    <input type ="hidden" id="airlineSponsored1" name="airlineSponsored1" value="${airlines.sponsored}"/>
                                    <button id="unsponsor" type="submit" value="Unsponsor">Unsponsor</button>

                                </form>
                            </div>
                            </div>

                        </c:if>

                </c:forEach>
             </div>

            <div class="wrap-airlines2">

                    <c:forEach items="${airlines}" var ="airlines">
                        <c:if test="${airlines.sponsored == 0}">
                            <%-- if not sponsored --%>

                                <div class="sponsor-airline">
                                    <div class="manage-airlines">
                                        <div class="register-row">
                                            <div class="booking-info-left" style="font-weight: bold"><c:out value= "${airlines.airlineName}"></c:out></div> <br>
                                            <div class="booking-input-left">Airline</div>
                                        </div>
                                        <div class="register-row">
                                            <div class="booking-info-right" style="font-weight: bold"><c:out value= "${airlines.countryCode3}"></c:out></div> <br>
                                            <div class="booking-input-right">Country</div>
                                        </div>
                                        <hr>
                                        <br>
                                        <div class="register-row">
                                            <div class="booking-input-center" style="font-style: italic"> <c:out value= "Unsponsored"></c:out></div>
                                        </div>
                                        <br>
                                        <form id="sponsorAirline" method="post" action="${pageContext.request.contextPath}/manageAirline/sponsor">
                                            <input type ="hidden" id="airlineName" name="airlineName" value="${airlines.airlineName}"/>
                                            <input type ="hidden" id="airlineSponsored" name="airlineSponsored" value="${airlines.sponsored}"/>
                                            <button id="sponsor" type="submit" value="Sponsor">Sponsor</button>

                                        </form>
                                    </div>
                                </div>

                        </c:if>
                    </c:forEach>
            </div>

        </div>
    </main>
</body>
</html>
