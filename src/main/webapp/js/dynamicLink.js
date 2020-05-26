//dynamic links


//guest
function dynamicLink(href, html){

    document.getElementById("dynamicLink").href=href;
    document.getElementById("dynamicLink").innerHTML=html;

}

//registered user
function userHome(href, html, username){

    dynamicLink(href,html);
    document.getElementById("myAccount").innerHTML=username;
}
