// Manipulate and validate search form fields

// Function to validate input from search flight form
function validateForm()
{
    // Check if selected airports valid
    var options = document.getElementById("destinations").options;
    var result = false;

    for(var i = 0; i < options.length; i++) {
        if($("#departureLocation").val() === options[i].value) {
            result = true;
        }
    }
    if(!result)
    {
        $("#departureLocation").val('');
        alert("Please select a valid departure airport from the list.");
        return false;
    }

    result = false;
    for(var i = 0; i < options.length; i++) {
        if($("#arrivalLocation").val() === options[i].value) {
            result = true;
        }
    }
    if(!result)
    {
        $("#arrivalLocation").value = '';
        alert("Please select a valid destination airport from the list.");
        return false;
    }

    // Check if selected airports are not the same
    var location = document.forms["searchFlight"]["departureLocation"].value;
    var destination = document.forms["searchFlight"]["arrivalLocation"].value;

    if(location === destination)
    {
        $("#arrivalLocation").val('');
        alert("Destination airport cannot be the same as departure airport.");
        return false;
    }
}

// restrict departure range input so user cannot search for a range that goes into return date if return trip type selected
function restrict_departure_range()
{
    var departureDate = new Date($("#departureDate").val());
    var returnDate =  new Date($("#returnDate").val());

    var timeDifference = returnDate.getTime() - departureDate.getTime();
    var dayDifference = timeDifference / (1000 * 3600 * 24);

    var $dateRangeInput = $("#departureDateRange");
    if(dayDifference < 7)
    {
        // if so reset departure date range
        $dateRangeInput.val("");
        if (dayDifference === 1)
        {
            $dateRangeInput.prop("disabled", true);
        }
        else {
            $dateRangeInput.attr("max", (dayDifference-1));
            $dateRangeInput.prop("disabled", false);
        }
    }
    else {
        $dateRangeInput.attr("max", 7);
        $dateRangeInput.prop("disabled", false);
    }
}

$('#departure-range').click(function() {
    $dateRangeInput = $("#departureDateRange");
    // restrict selectable dates for range, make date range input required
    restrict_departure_range();
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
    // if return trip
    if($("#type").val() == "return")
    {
        // change min of return date to departure date
        $("#returnDate").attr("min", $('#departureDate').val());
        // enable return date until as depart is selected
        $("#returnDate").prop("disabled", false);
        // Clear depart value
        $("#returnDate").val("");
    }

    // check if departure range option is selected
    if($('#departure-range').prop("checked"))
    {
        $dateRangeInput = $("#departureDateRange");
        restrict_departure_range();
    }
});

$('#return-range').click(function() {
    $dateRangeInput = $("#returnDateRange");
    // change visibility of departure date range div
    $("#return-range-div").toggle(this.checked);
    // make departure date range input required if option selected
    if ($('#return-range').is(':checked')) {
        $dateRangeInput.prop( "required",true);
    }
    else {
        $dateRangeInput.prop( "required",false);
    }
});

// if return date is selected or changed
$('#returnDate').change(function () {
    if($('#departure-range').prop("checked")) {
        restrict_departure_range();
    }
});

$('#type').change(function () {
    if($(this).children("option:selected").val() === "return")
    {
        // if return trip selected show return date input and set as required
        $("#departureDate").css("display","inline");
        $("#departureDate").prop("required",true);
        // check if date already selected for departure
        if ($("#departureDate").val() !== "")
        {
            // if so set min of return date to departure and enable return input
            $("#returnDate").attr("min", $("#departureDate").val());
            $("#returnDate").prop("disabled", false);
        }
        // enable return range option
        $("#return-range").prop("disabled", false);
    }
    else {
        // if oneway trip selected hide return date input and set as not required
        $("#departureDate").hide();
        $("#departureDate").prop( "required",false);
        // disable return range option
        $("#return-range").prop("disabled", true);
    }
});