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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="/js/dynamicLink.js"></script>
</head>
<body>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">
    <div class="card-body">
    <h1>Travel Agent</h1>

        <div class="hot-locations">
        <h4>Hot Locations</h4>
            <div class="wrap-stats">
            <c:forEach items="${countryStat}" var ="countryStat">
                <c:if test="${empty countryStat}">
                    <h4>No Hot Locations</h4>
                </c:if>


                <div class="manage-stats">
                <div class="register-row">
                    <div class="booking-info-right" style="font-weight: bold"><c:out value= "${countryStat.countryCode3}"></c:out></div><br>
                    <div class="booking-input-right">Country</div>
                    <hr>

                </div>
                    <div class="register-row">
                        <div class="booking-info-left" style="font-weight: bold"><c:out value= "${countryStat.popularity}"></c:out></div><br>
                        <div class="booking-input-left">Popularity</div>
                    </div>
                </div>

            </c:forEach>
            </div>
        </div>

        <h4>Create Holiday Packages </h4>
        <div class="manage-holiday-packages">
            <form id="holidayPackages" method="post" action="${pageContext.request.contextPath}/travelAgentPage/create">

                <!-- Destination -->
                <div class="register-group">
                    <div class="register-row1">
                        <input class="input" placeholder="Destination" list="airports" id="destination" name ="destination" onchange="setHolidayPackage()" required/>
                        <span class="symbol-input">
                            <i class="fa fa-plane" aria-hidden="true"> </i>
                        </span>
                    </div>

                <!-- Country code -->
                    <div class="register-row1">
                        <input class="input" placeholder="Country Code" list="countryCode3" id="countryCode" name ="countryCode" value="" required/>
                        <span class="symbol-input">
                            <i class="fa fa-flag" aria-hidden="true"> </i>
                        </span>
                    </div>
                </div>

                <!-- Description -->
                <div class="register-row">
                    <textarea class="input" placeholder="Package Description" form ="comment" id="comment" name="comment" rows="4" cols="50" required></textarea>
                    <span class="symbol-input">
                            <i class="fa fa-info" aria-hidden="true"> </i>
                    </span>
                </div>

                <br>




            <div class="register-group">

                <!-- User type -->

                <div class="register-row1">
                    <div class="register-gender">
                    <input type="radio" id="sponsored" name="userType" value="Sponsored" style="margin-left: 20px; margin-top: 10px" checked/>
                    <label for="sponsored">Sponsored</label>
                    </div>
                </div>
                <div class="register-row1">
                    <div class="register-gender">
                    <input type="radio" id="Hot location" name="userType" value="Hot location" style="margin-left: 20px; margin-top: 10px"/>
                    <label for="Hot location">Hot location</label>
                    </div>
                </div>
                <div class="register-row1">
                    <div class="register-gender">
                    <input type="radio" id="business" name="userType" value="Business" style="margin-left: 20px; margin-top: 10px"/>
                    <label for="business">Business</label>
                    </div>
                </div>
                <div class="register-row1">
                    <div class="register-gender">
                    <input type="radio" id="Other" name="userType" value="Other" style="margin-left: 20px; margin-top: 10px"/>
                    <label for="Other">Other</label> <br>
                    </div>
                </div>
            </div>

                <div class="register-group">
                    <div class="register-row1">
                        <input class="input-submit" type="submit" value="Create"/>
                    </div>
                    <div class="register-row1">
                        <input class="input-submit" type="reset" value="Clear"/>
                    </div>
                </div>

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
