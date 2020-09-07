<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  SENG3150 Group 3
  Date: 14/08/2020
  Time: 11:05 am
--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Flight search fields -->
<form name="searchFlight" method="post" id="searchFlight" action="${pageContext.request.contextPath}/search" onsubmit="return validateForm()">
    <div class="search-form">
        <div class="main-search-fields">
            <!-- Return or one-way trip -->
            <div class="form-group-trip">
                <label for="type">Trip:</label>
                <select id="type" name="type">
                    <option value="oneway">One-way</option>
                    <option value="return">Return</option>
                </select>
            </div>

            <div class="form-group">
                <!-- Ticket class -->
                <label for="classCode">Class:</label>
                <select id="classCode" name="classCode">
                    <option value="ECO">Economy</option>
                    <option value="PME">Premium Economy</option>
                    <option value="BUS">Business Class</option>
                    <option value="FIR">First Class</option>
                </select>
            </div>

            <div class="form-group">
                <!-- Adult passengers -->
                <label for="adults">Adults:</label>
                <input type="number" id="adults" name="adults" min="1" max="9" required>
                <!-- Children passengers -->
                <label for="children">Children:</label>
                <input type="number" id="children" name="children" min="0" max="9" required>
            </div>

            <!-- Starting airport -->
            <div class="form-group-departure-local">
                <label for="departureLocation">From:</label>
                <input list="destinations" id="departureLocation" name="departureLocation" required>
            </div>

            <!-- Destination airport -->
            <div class="form-group-arrival-local">
                <label for="arrivalLocation">To:</label>
                <input list="destinations" id="arrivalLocation" name="arrivalLocation" required>
            </div>

            <!-- Depart date -->
            <div class="form-group-depart-date">
                <label for="departureDate">Depart:</label>
                <jsp:useBean id="now" class="java.util.Date"/>
                <input type="date" id="departureDate" name="departureDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" required>
            </div>

            <!-- Return date -->
            <div id="form-group-return-date">
                <label for="returnDate">Return:</label>
                <input type="date" id="returnDate" name="returnDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />" disabled>
            </div>

            <div style="padding-bottom: 5px">
                <p style="display: inline">Extra Options</p>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
                <div class="search-arrow-container">
                    <div class="fa fa-arrow-down rotate"></div>
                </div>
                <script>
                    $(".rotate").click(function(){
                        $(this).toggleClass("up");
                        $('.extra-search-fields').toggle("slow");
                    })
                </script>
            </div>
        </div>
        <div class="extra-search-fields" style="display:none">
            <input type="checkbox" id="departure-range" name="departure-range">
            <label for="departure-range">Search within date range for departure</label>
            <div id="departure-range-div" style="display:none">
                <p style="display: inline">Get departing flights from </p>
                <input type="number" id="departureDateRange" name="departureDateRange" min="1" max="7">
                <p style="display: inline">days ahead</p>
            </div>

            <input type="checkbox" id="return-range" name="return-range" disabled>
            <label for="return-range">Search within date range for return</label>
            <div id="return-range-div" style="display:none">
                <p style="display: inline">Get departing flights </p>
                <input type="number" id="returnDateRange" name="returnDateRange" min="1" max="7">
                <p style="display: inline">days ahead</p>
            </div>
        </div>

        <!-- Search button -->
        <div class="search-button">
            <button class="btn btn-lg btn-outline-success text-uppercase" type="submit">Search</button>
        </div>
    </div>
    <!-- Airport destinations -->
    <datalist id="destinations">
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
    <!-- jQuery for search form -->
    <script src="${pageContext.request.contextPath}/js/searchFormAssistor.js"></script>
</form>