function setVarsFormValidation(password, conPassword, phone, dob){
    var pass = password;
    var conPass = conPassword;
    var pnum = phone;
    var birth = dob;
    document.getElementById("password").innerText = pass;
    document.getElementById("confirmPassword").innerText = conPass;
    document.getElementById("phone").innerText = pnum;
    document.getElementById("dateOfBirth").innerText = birth;
}

function setVarsFormValidationFlights(departure, destination){
    var depart = departure;
    var destin = destination;
    document.getElementById("departureLocation").value = depart;
    document.getElementById("arrivalLocation").value = destin;
}

