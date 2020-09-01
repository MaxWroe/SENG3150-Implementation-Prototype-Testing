<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  SENG3150 Group 3
  Date: 14/08/2020
  Time: 11:05 am
--%>
<script src="${pageContext.request.contextPath}/js/searchFormAssistor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Flight search fields -->
<form name="searchFlight" method="get" id="searchFlight" action="${pageContext.request.contextPath}/search" onsubmit="return validateForm()">
    <div class="search-form">
        <div class="main-search-fields">
            <!-- Return or one-way trip -->
            <div class="form-group-trip">
                <label for="type">Trip:</label>
                <select id="type" name="type" onchange="showDiv('form-group-return-date', 'returnDate', this)">
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
                <input list="destinations" name="departureLocation" id="departureLocation" required>
            </div>

            <!-- Destination airport -->
            <div class="form-group-arrival-local">
                <label for="arrivalLocation">To:</label>
                <input list="destinations" name="arrivalLocation" id="arrivalLocation" required>
            </div>

            <!-- Depart date -->
            <div class="form-group-depart-date">
                <label for="departureDate">Depart:</label>
                <jsp:useBean id="now" class="java.util.Date"/>
                <input type="date" id="departureDate" name="departureDate" min="<fmt:formatDate pattern="yyyy-MM-dd" value="${now}" />"
                       onchange="restrictDepart()" required>
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
                <input type="number" id="departureDateRange" name="departureDateRange" min="1" max="7" disabled>
                <p style="display: inline">days ahead</p>
            </div>

            <input type="checkbox" id="return-range" name="return-range" disabled>
            <label for="return-range">Search within date range for return</label>
            <div id="return-range-div" style="display:none">
                <p style="display: inline">Get departing flights </p>
                <input type="number" id="returnDateRange" name="returnDateRange" min="1" max="7" disabled>
                <p style="display: inline">days ahead</p>
            </div>
        </div>
        <script>
            // departure date range ids: departureDate (date input), departureDateRange (number input), departureRange (checkbox input),
            // departure-range-div (div), start-departure-date-range (p)
            // return date ids: returnDate (date input), returnDateRange (number input), returnRange (checkbox input),
            // return-range-div (div), start-return-date-range (p)

            function restrict_departure_range()
            {
                var departureDate = new Date($("#departureDate").val());
                var returnDate =  new Date($("#returnDate").val());

                var Difference_In_Time = returnDate.getTime() - departureDate.getTime();
                var Difference_In_Days = Difference_In_Time / (1000 * 3600 * 24);

                if(Difference_In_Days < 7)
                {
                    if (Difference_In_Days === 1)
                    {
                        $("#departureDateRange").attr("min", 0);
                        $("#departureDateRange").attr("max", 0);
                    }
                    else {
                        $("#departureDateRange").attr("max", Difference_In_Days);
                    }
                }
                else {
                    $("#departureDateRange").attr("max", 7);
                }
            }

            $('#departure-range').click(function() {
                $dateRangeInput = $("#departureDateRange");
                // restrict selectable dates for range, make date range input required
                restrict_departure_range();
                // only if initial departure date selected enable range date
                if ($("#departureDate").val() !== "")
                {
                    $dateRangeInput.prop( "disabled", false );
                }
                // change visibility of departure date range div
                $("#departure-range-div").toggle(this.checked);
                // make departure date range input required if option selected
                if ($('#departure-range').is(':checked')) {
                    $dateRangeInput.prop( "required", true );
                }
                else {
                    $dateRangeInput.prop( "required", false );
                }
            });

            // if departure date is selected or changed
            $('#departureDate').change(function () {
                // check if departure range option is selected
                if($('#departure-range').prop("checked"))
                {
                    $dateRangeInput = $("#departureDateRange");
                    // if so reset departure date range
                    $dateRangeInput.val("");
                    $dateRangeInput.prop( "disabled", false );
                    restrict_departure_range();
                }
            });

            $('#return-range').click(function() {
                $dateRangeInput = $("#returnDateRange");
                // only if initial departure date selected enable range date
                if ($("#returnDate").val() !== "")
                {
                    $dateRangeInput.prop( "disabled", false );
                }
                // change visibility of departure date range div
                $("#return-range-div").toggle(this.checked);
                // make departure date range input required if option selected
                if ($('#return-range').is(':checked')) {
                    $dateRangeInput.prop( "required", true );
                }
                else {
                    $dateRangeInput.prop( "required", false );
                }
            });

            // if return date is selected or changed
            $('#returnDate').change(function () {
                if($('#departure-range').prop("checked")) {
                    $dateRangeInput = $("#departureDateRange");
                    // if so reset departure date range
                    $dateRangeInput.val("");
                    $dateRangeInput.prop( "disabled", false );
                    restrict_departure_range();
                }
                // check if departure range option is selected
                if($('#return-range').prop("checked"))
                {
                    $dateRangeInput = $("#returnDateRange");
                    // if so reset departure date range
                    $dateRangeInput.val("");
                    $dateRangeInput.prop( "disabled", false );
                    restrict_departure_range();
                }
            });

            $('#type').change(function () {
                if($(this).children("option:selected").val() === "return")
                {
                    $("#return-range").prop("disabled", false);
                }
                else {
                    $("#return-range").prop("disabled", true);
                }
            });
        </script>
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
</form>