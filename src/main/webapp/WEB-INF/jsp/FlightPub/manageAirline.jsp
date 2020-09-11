<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 5/09/2020
  Time: 7:49 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Airline Management</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

    <main class="main-content">
        <div class="card-body">
            <h1>Airline Management</h1>
            <h4>List of Airlines</h4>

            <%-- test --%>
            <div class="manage-airlines">
            <c:forEach items="${airline}" var ="airline">
                <p><c:out value= "${airline}"></c:out></p>


                <div class="unsponsor-airline-test">
                    <form id="unsponsorAirline2" method="post" action="${pageContext.request.contextPath}/manageAirline/unsponsor">
                        <input type ="hidden" id="airlineName2" name="airlineName2" value="${airlines.airlineName}"/>
                        <input type ="hidden" id="airlineSponsored2" name="airlineSponsored2" value="${airlines.sponsored}"/>
                        <button type="submit" value="Unsponsor"> Unsponsor </button>

                    </form>
                </div>


            </c:forEach>
            </div>

            <%-- preferred parsing all airlines --%>
            <div class="manage-airlines">
            <c:forEach items="${airlines}" var ="airlines">
                <p><c:out value= "${airlines.airlineName}"></c:out></p>
                <p><c:out value= "${airlines.countryCode3}"></c:out></p>
                <p><c:out value= "${airlines.sponsored}"></c:out></p>

                <%-- if sponsored --%>
                <c:choose>
                    <c:when test="${airlines.sponsored == 1}">
                        <div class="unsponsor-airline">
                            <form id="unsponsorAirline" method="post" action="${pageContext.request.contextPath}/manageAirline/unsponsor">
                                <input type ="hidden" id="airlineName1" name="airlineName1" value="${airlines.airlineName}"/>
                                <input type ="hidden" id="airlineSponsored1" name="airlineSponsored1" value="${airlines.sponsored}"/>
                                <button id="unsponsor" type="submit" value="Unsponsor">Unsponsor</button>

                            </form>
                        </div>
                        <br />
                    </c:when>

                    <%-- if not sponsored --%>
                    <c:otherwise>
                        <div class="sponsor-airline">
                            <form id="sponsorAirline" method="post" action="${pageContext.request.contextPath}/manageAirline/sponsor">
                                <input type ="hidden" id="airlineName" name="airlineName" value="${airlines.airlineName}"/>
                                <input type ="hidden" id="airlineSponsored" name="airlineSponsored" value="${airlines.sponsored}"/>
                                <button id="sponsor" type="submit" value="Sponsor">Sponsor</button>

                            </form>
                        </div>

                        <br />
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </div>


        </div>
    </main>
</body>
</html>
