function checkSelection()
{
    var checkDepart = document.querySelector('input[name="departure"]:checked');
    var checkReturn = document.querySelector('input[name="return"]:checked');

    if (checkDepart && checkReturn != null)
    {
        document.getElementById("return-flight-book").disabled = false;
    }
}

function validateFlightSelection()
{

}