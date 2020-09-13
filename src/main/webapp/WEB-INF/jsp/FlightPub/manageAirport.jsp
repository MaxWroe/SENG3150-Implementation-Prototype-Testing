<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 5/09/2020
  Time: 9:23 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Airport Management</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="/js/dynamicLink.js"></script>
    <script src="${pageContext.request.contextPath}/js/searchFormAssistor.js"></script>
</head>
<body>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">

        <h1>Airport Management</h1>
        <h4>Restricted Airports</h4>




    <%-- preferred parsing --%>
        <div class="wrap-airports">


            <c:forEach items="${airports}" var ="airports">
                <c:if test="${airports.shutdownStartDate != null}">

             <div class="manage-airports">
                <div class="register-group">
                    <div class="register-row1">
                        <div class="booking-info-left" style="font-weight: bold"><c:out value="${airports.destinationCode}"> </c:out></div><br>
                        <div class="booking-input-left">City</div>
                    </div>
                    <div class="register-row1">
                         <div class="booking-info-right" style="font-weight: bold"><c:out value="${airports.country}"> </c:out></div><br>
                        <div class="booking-input-right">Country</div>
                    </div>




                </div>

                 <br>
                 <hr>
                    <div class="register-group">
                        <div class="register-row1">
                            <div class="booking-input-left">Start Date</div><br>
                           <div class="booking-info-left"> <c:out value="${airports.shutdownStartDate}"> </c:out></div>
                        </div>
                        <div class="register-row1">
                            <div class="booking-input-right">End Date</div><br>
                           <div class="booking-info-right"> <c:out value="${airports.shutdownEndDate}"> </c:out></div>
                        </div>
                    </div>
             </div>
                </c:if>



            </c:forEach>
        </div>





                <div class="restrict-airline">
                    <div class="submit-airport">
                        <button type="submit" onclick="displayForm('restrictAirline')"> Restrict </button>
                    </div>


                    <form id="restrictAirline" method="post" action="/manageAirport/restrict" style="display:none;">

                        <div class="register-row">
                             <input class="input" placeholder="City to restrict" list="listOfAirports" id="airportCode" name="airlineName2" required>
                            <span class="symbol-input">
                                <i class="fa fa-flag" aria-hidden="true"> </i>
                             </span>
                        </div>

                        <div class="register-group">
                            <div class="register-row1">
                                <label for="departureDate" style="color: white">Restrict Start Date</label>
                                <jsp:useBean id="now" class="java.util.Date"/>
                                <input class="input" type="date" id="departureDate" name="shutdownStartDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"  required>
                            </div>

                            <div class="register-row1">
                                <label for="returnDate" style="color: white">Restrict End Date</label>
                                <input class="input" type="date" id="returnDate" name="shutdownEndDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" required>
                            </div>
                        </div>

                        <div class="register-group">
                            <div class="register-row1">
                                <input class="input-submit-invert" type="submit" value="Submit"/>
                            </div>
                            <div class="register-row1">
                                <input class="input-submit-invert" type="reset" value="Clear"/>
                            </div>
                        </div>
                    </form>

                    <datalist id ="listOfAirports">
                        <c:forEach items="${airports}" var ="airports">
                            <c:if test="${airports.shutdownStartDate == null}">
                                <option value="${airports.destinationCode}"> ${airports.country}</option>
                            </c:if>

                        </c:forEach>
                    </datalist>
                </div>








</main>

</body>
</html>
