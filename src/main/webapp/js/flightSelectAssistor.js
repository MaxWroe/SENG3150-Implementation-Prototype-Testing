var departureCost = 0;
var returnCost = 0;

function updateCost(travel, amount)
{
    if (travel === "departure")
    {
        departureCost = amount;
    }
    if (travel === "return")
    {
        returnCost = amount;
    }

    document.getElementById("booking-cost").innerText = "Total cost: " + (Number(departureCost) + Number(returnCost));
}

function validateFlightSelection()
{
    var checkDepart = document.querySelector('input[name="departure"]:checked');
    var checkReturn = document.querySelector('input[name="return"]:checked');

    if (checkDepart == null || checkReturn == null)
    {
        alert("Please select a departure flight AND a return flight.");
        return false;
    }
}