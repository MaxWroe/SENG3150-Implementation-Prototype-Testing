//dynamic links


//guest
function dynamicLink(href, html){

    document.getElementById("dynamicLink").href=href;
    document.getElementById("dynamicLink").innerHTML=html;


}

//registered user
function userHome(href, html, username){

    dynamicLink(href,html);
    displayMenu();
    document.getElementById("myAccount").innerHTML=username;
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
