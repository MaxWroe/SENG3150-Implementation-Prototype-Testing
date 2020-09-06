//dynamic links


//guest
function dynamicLink(href, html){
    document.getElementById("dynamicLink").href=href;
    document.getElementById("dynamicLink").innerHTML=html;

}


//logged in user
function userPage(href, html){
    dynamicLink(href,html);
    displayMenu();
    //get name?
}


//displays hidden menu
function displayMenu(){
    document.getElementById("dropdown").style.display="block";
}


//display hidden form
function displayForm(id){
    var form = document.getElementById(id);

    if(form.style.display === "none"){
        form.style.display="block";
    }else{
        form.style.display="none";
    }
}

function setHolidayPackage(){
    var destination;
    destination = document.getElementById("destination").value;

    if(destination === "ADL"){
        document.getElementById("countryCode").value = "USA";
        document.getElementById("countryName").Value = "United States of America";

    }
}
