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




    <%-- preferred parsing --%>

        <div class="manage-airports">

            <c:forEach items="${airports}" var ="airports">
                <c:if test="${not empty airports.shutdownStartDate}">
                    <p><c:out value="${airports.destinationCode}"> </c:out></p>
                    <p><c:out value="${airports.country}"> </c:out></p>
                    <p><c:out value="${airports.shutdownStartDate}"> </c:out></p>
                    <p><c:out value="${airports.shutdownEndDate}"> </c:out></p>
                </c:if>


            </c:forEach>







                <div class="submit-airport">
                    <button type="submit" onclick="displayForm('restrictAirline')"> Restrict </button>
                </div>

                <div class="restrict-airline">
                    <form id="restrictAirline" method="post" action="/manageAirport/restrict" style="display:none;">
                        <label for="airportCode">City to Restrict</label>
                        <input list="listOfAirports" id="airportCode" name="airlineName2" required>

                        <label for="departureDate">Restrict Start Date</label>
                        <jsp:useBean id="now" class="java.util.Date"/>
                        <input type="date" id="departureDate" name="shutdownStartDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"  required>

                        <label for="returnDate">Restrict End Date</label>
                        <input type="date" id="returnDate" name="shutdownEndDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" required>

                        <button type="submit">Submit </button>
                    </form>

                    <datalist id ="listOfAirports">
                        <c:forEach items="${airports}" var ="airports">
                            <c:if test="${empty airports.shutdownStartDate}">
                                <option value="${airports.destinationCode}" > ${airports.country}</option>
                            </c:if>

                        </c:forEach>
                    </datalist>
                </div>


        </div>




    </div>
</main>

</body>
</html>
