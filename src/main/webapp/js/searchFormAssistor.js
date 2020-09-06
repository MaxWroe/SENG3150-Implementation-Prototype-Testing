// Manipulate and validate search form fields

// Function to display return date input field if return flight
function showDiv(divId, changedId, element)
{
    document.getElementById(divId).style.display = element.value === "return" ? 'inline' : 'none';
    document.getElementById(changedId).required = element.value === "return";
    // Check if date already selected for departure
    if ($("#departureDate").val() !== "")
    {
        document.getElementById("returnDate").min = document.getElementById("departureDate").value;
        $("#returnDate").prop("disabled", false);
    }
}

// Function to restrict return date to not minimum than depart
function restrictDepart()
{
    document.getElementById("returnDate").min = document.getElementById("departureDate").value;
    // Disable return date until depart is selected
    $("#returnDate").prop("disabled", false);
    // Clear depart value
    $("#returnDate").val("");
}

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

// deprecated function
function clearFields()
{
    var check = $("#departureLocation").val();

    if(check === 'null')
    {
        $("#departureLocation").val('');
        $("#arrivalLocation").val('');
        $("#adults").val('1');
        $("#children").val('0');
    }
}

// restrict departure range input so user cannot search for a range that goes into return date if return trip type selected
function restrict_departure_range()
{
    var departureDate = new Date($("#departureDate").val());
    var returnDate =  new Date($("#returnDate").val());

    var timeDifference = returnDate.getTime() - departureDate.getTime();
    var dayDifference = timeDifference / (1000 * 3600 * 24);

    $dateRangeInput = $("#departureDateRange");
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
        $dateRangeInput.prop( "required", true );
    }
    else {
        $dateRangeInput.prop( "required", false );
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
        $("#return-range").prop("disabled", false);
    }
    else {
        $("#return-range").prop("disabled", true);
    }
});