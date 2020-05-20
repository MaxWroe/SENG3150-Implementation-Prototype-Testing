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
    document.getElementById("return").min = document.getElementById("depart").value;
    // Disable return date until depart is selected
    document.getElementById("return").disabled = false;
    // Clear depart value
    document.getElementById("return").value = "";
}

// Function to restrict user from selection same airport for location and destination
function restrictAirport()
{
    //$("#browsers").find("[value='Firefox']").prop("disabled", true);
}