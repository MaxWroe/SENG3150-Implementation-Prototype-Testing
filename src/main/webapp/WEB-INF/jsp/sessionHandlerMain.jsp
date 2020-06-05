<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 29/05/2020
  Time: 8:30 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- session handler -->
<%
    //gets session
    String userID = (String)session.getAttribute("userId");

    //user details

    String userID1 = (String)request.getAttribute("userID");
    String email = (String)request.getAttribute("email");


    String firstName = (String)request.getAttribute("firstName");
    String lastName = (String)request.getAttribute("lastName");
    String userType = (String)request.getAttribute("userType");
    Date dateOfBirth = (Date)request.getAttribute("dateOfBirth");
    String gender = (String)request.getAttribute("gender");
    String citizenship = (String)request.getAttribute("citizenship");
    String phone = (String)request.getAttribute("phone");
    String familyMembers = (String)request.getAttribute("FamilyMember");
    String emergencyContacts = (String)request.getAttribute("emergencyContacts");
    String address = (String)request.getAttribute("address");

    request.setAttribute("userID", userID1);

    //checks if session is empty and not logged in
    if(userID == null && email == null){
        %><body><%

    //checks if logged in
    }else if(email != null && userID == null){

        //sets user session
        session.setAttribute("userId", userID1);
        session.setAttribute("email", email);
        session.setAttribute("firstName", firstName);
        session.setAttribute("lastName", lastName);
        session.setAttribute("userType", userType);
        session.setAttribute("dateOfBirth", dateOfBirth);
        session.setAttribute("gender", gender);
        session.setAttribute("citizenship", citizenship);
        session.setAttribute("phone", phone);
        session.setAttribute("familyMembers", familyMembers);
        session.setAttribute("emergencyContacts", emergencyContacts);
        session.setAttribute("address", address);

        %> <body onload=userPage('/logout','Logout');><%

    }else{

        %> <body onload=userPage('/logout','Logout');><%

    }
%>