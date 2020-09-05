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
    <script src="/js/dynamicLink.js"></script>
    <script src="${pageContext.request.contextPath}/js/searchFormAssistor.js"></script>
</head>
<body>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>Airport Management</h1>
        <h4>List of Airports</h4>


        <%-- test --%>
        <div class="manage-airports-test">
            <c:forEach items="${airport}" var ="airport">

                <p><c:out value= "${airport}"></c:out></p>
                <button type="submit" onclick="displayForm('restrictAirlineTest')"> Restrict </button>

            </c:forEach>

            <%-- preferred parsing --%>
            <c:set var = "i" value = "${0}"/>
            <c:set var = "j" value = "${i}"/>
                <div class="manage-airports">
                    <c:forEach items="${airports}" var ="airports">
                        <c:set var = "j" value = "${j+1}"/>

                        <p><c:out value= "${airports.destinationCode}"></c:out></p>
                        <p><c:out value= "${airports.country}"></c:out></p>
                        <p><c:out value= "${airports.shutdownStartDate}"></c:out></p>
                        <p><c:out value= "${airports.shutdownEndDate}"></c:out></p>


                        <div class="submit-airport">
                            <button type="submit" onclick="displayForm('restrictAirlineTest')"> Restrict </button>
                        </div>


                    </c:forEach>
                </div>

            <div class="restrict-airline-test">
                <form id="restrictAirlineTest" method="post" action="${pageContext.request.contextPath}/manageAirport/restrict" style="display:none;">
                    <input type ="hidden" id="airportCode" name="airlineName2" value="${airport}"/>

                    <label for="departureDate">Restrict Start Date</label>
                    <jsp:useBean id="now" class="java.util.Date"/>
                    <input type="date" id="departureDate" name="shutdownStartDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" onchange="restrictDepart()" required>

                    <label for="returnDate">Restrict End Date</label>
                    <input type="date" id="returnDate" name="shutdownEndDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" disabled>

                    <button type="submit">Submit </button>
                </form>
            </div>

    </div>
</main>

</body>
</html>
