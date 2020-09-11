<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 28/08/2020
  Time: 4:06 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Travel Agent</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <script src="/js/dynamicLink.js"></script>
</head>
<body>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">
    <div class="card-body">
    <h1>Travel Agent</h1>
    <h4>Holiday Packages</h4>

        <div class="hot-locations">
        <h4>Hot Locations</h4>
            <c:forEach items="${countryStat}" var ="countryStat">
                <c:if test="${empty countryStat}">
                    <h4>No Hot Locations</h4>
                </c:if>

                <c:out value= "${countryStat.countryCode3}"></c:out>
                <c:out value= "${countryStat.popularity}"></c:out></br>

            </c:forEach>
        </div>

        <h4>Create Holiday Packages </h4>
        <div class="manage-holiday-packages">
            <form id="holidayPackages" method="post" action="${pageContext.request.contextPath}/travelAgentPage/create">

                <!-- Description -->
                <label for="comment">Description: </label><br>
                <textarea form ="comment" id="comment" name="comment" rows="4" cols="50" required> </textarea> <br>

                <!-- Destination -->
                <label for="destination">Destination: </label>
                <input list="airports" id="destination" name ="destination" onchange="setHolidayPackage()" required/> <br>

                <!-- Country code -->
                <label for="countryCode">Country Code: </label>
                <input list="countryCode3" id="countryCode" name ="countryCode" value="" required/> <br>

                <!-- User type -->
                <label>Package Type: </label>

                <input type="radio" id="sponsored" name="userType" value="Sponsored" checked/>
                <label for="sponsored">Sponsored</label>

                <input type="radio" id="Hot location" name="userType" value="Hot location" checked/>
                <label for="Hot location">Hot location</label>

                <input type="radio" id="business" name="userType" value="Business"/>
                <label for="business">Business</label>

                <input type="radio" id="Other" name="userType" value="Other"/>
                <label for="Other">Other</label> <br>

                <button type="submit">Create</button>

                <!-- Airport destinations -->
                <datalist id="airports">
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

                <!-- country code List -->
                <datalist id ="countryCode3">
                    <c:forEach items="${airline}" var ="airline">
                        <option value="${airline.countryCode3}" ></option>
                    </c:forEach>

                </datalist>
            </form>
        </div>




    </div>
</main>



</body>
</html>
