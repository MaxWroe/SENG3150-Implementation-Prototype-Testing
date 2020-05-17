<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jfpr2
  Date: 17/05/2020
  Time: 1:45 pm
  To change this template use File | Settings | File Templates.
--%>

<html>
  <head>
    <title>Welcome</title>
  </head>
  <body>
    <h1>Hello World!</h1>
    <h2>Here are the first 10 available flights in the database</h2>
  <c:forEach begin="0" end="10" items="${requestScope.availabilities}" var="availabilities">
    <jsp:useBean id="availabilities" type="java.lang.Object" />
    <h3>flight: <c:out value = "${availabilities.flightNumber}"/></h3>
    <c:out value = "${availabilities.airlineCode}"/>
    <c:out value = "${availabilities.departureTime}"/>
    <c:out value = "${availabilities.classCode}"/>
    <c:out value = "${availabilities.ticketCode}"/>
    <c:out value = "${availabilities.numberAvailableSeatsLeg1}"/>
    <c:out value = "${availabilities.numberAvailableSeatsLeg2}"/>
  </c:forEach>
  </body>
</html>
