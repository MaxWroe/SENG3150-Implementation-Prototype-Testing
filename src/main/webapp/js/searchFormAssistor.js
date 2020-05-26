// Manipulate search form fields

// Function to display return date input field if return flight
function showDiv(divId, changedId, element)
{
    document.getElementById(divId).style.display = element.value === "return" ? 'inline' : 'none';
    document.getElementById(changedId).required = element.value === "return";
}

// Function to restrict return date to not minimum than depart
function restrictDepart()
{
    document.getElementById("returnDate").min = document.getElementById("departureDate").value;
    // Disable return date until depart is selected
    document.getElementById("returnDate").disabled = false;
    // Clear depart value
    document.getElementById("returnDate").value = "";
}

// Function to validate input from search flight form
function validateForm()
{
    // Check if selected airports valid
    var options = document.getElementById("locations").options;
    var result = false;

    for(var i = 0; i < options.length; i++) {
        if(document.getElementById("departureLocation").value === options[i].value) {
            result = true;
        }
    }
    if(!result)
    {
        document.getElementById("departureLocation").value = '';
        alert("Please select a valid departure airport from the list");
        return false;
    }

    result = false;
    for(var i = 0; i < options.length; i++) {
        if(document.getElementById("arrivalLocation").value === options[i].value) {
            result = true;
        }
    }
    if(!result)
    {
        document.getElementById("arrivalLocation").value = '';
        alert("Please select a valid destination airport from the list");
        return false;
    }

    // Check if selected airports are not the same
    var location = document.forms["searchFlight"]["departureLocation"].value;
    var destination = document.forms["searchFlight"]["arrivalLocation"].value;

    if(location === destination)
    {
        alert("Destination airport cannot be the same as departure airport.");
        return false;
    }
}

window.onload = function() {
    // Work around for 'null' airports showing when selecting search page
    clearFields();
};

function clearFields()
{
    var check = document.getElementById("departureLocation").value;

    if(check === 'null')
    {
        document.getElementById("departureLocation").value = '';
        document.getElementById("arrivalLocation").value = '';
        document.getElementById("adults").value = 1;
        document.getElementById("children").value = 0;
    }
}