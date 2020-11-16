//validates different forms in the system

//validates registration form
function validateRegister(){
    var password = document.getElementById("password");
    var confirmPassword = document.getElementById("confirmPassword");
    var phone = document.getElementById("phone");
    var dateOfBirth = document.getElementById("dateOfBirth");

    var phoneNo = /^\d{10}$/;
    var message = "Error: ";
    var boolean = true;

    //checks if password entered === confirmed password
    if(password.value !== confirmPassword.value){
        message += "-Please re-confirm password.\n";
        boolean = false;
    }

    //checks if valid phone number
    // if(phone.value.match(phoneNo)){
    //
    // }else{
    //     message += "-Phone number entered is invalid. \n";
    //     boolean = false;
    // }

    //checks age
    if (getAge(dateOfBirth.value) < 18){
        message += "-Only 18+ can register. \n";
        boolean = false;
    }

    //if false show message
    if(boolean !== true){
        alert(message);
    }



    return boolean;


}

//calculates age from dateOfBirth
function getAge(dateOfBirth){
    var today = new Date();
    var birthDate = new Date(dateOfBirth);
    var age = today.getFullYear() - birthDate.getFullYear();
    var m = today.getMonth() - birthDate.getMonth();


    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age = age - 1;
    }

    return age;

}