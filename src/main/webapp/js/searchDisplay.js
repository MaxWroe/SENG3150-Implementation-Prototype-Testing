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

// Function to validate input from search flight form
function validateForm()
{
    // Check if selected airports valid
    var options = document.getElementById("locations").options;
    var result = false;

    for(var i = 0; i < options.length; i++) {
        if(document.getElementById("from").value === options[i].value) {
            result = true;
        }
    }
    if(!result)
    {
        document.getElementById("from").value = '';
        alert("Please select a valid departure airport from the list");
        return false;
    }

    result = false;
    for(var i = 0; i < options.length; i++) {
        if(document.getElementById("to").value === options[i].value) {
            result = true;
        }
    }
    if(!result)
    {
        document.getElementById("to").value = '';
        alert("Please select a valid destination airport from the list");
        return false;
    }

    // Check if selected airports are not the same
    var location = document.forms["searchFlight"]["from"].value;
    var destination = document.forms["searchFlight"]["to"].value;

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
    var check = document.getElementById("from").value;

    if(check === 'null')
    {
        document.getElementById("from").value = '';
        document.getElementById("to").value = '';
        document.getElementById("adults").value = 1;
        document.getElementById("children").value = 0;
    }
}