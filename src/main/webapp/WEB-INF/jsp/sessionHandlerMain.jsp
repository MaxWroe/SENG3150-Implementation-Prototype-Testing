<%--
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
    String userId = request.getParameter("userID");
    String email = request.getParameter("email");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");
    String userType = request.getParameter("userType");
    String dateOfBirth = request.getParameter("dateOfBirth");
    String gender = request.getParameter("gender");
    String citizenship = request.getParameter("citizenship");
    String phone = request.getParameter("phone");
    String familyMembers = request.getParameter("FamilyMember");
    String emergencyContacts = request.getParameter("emergencyContacts");
    String address = request.getParameter("address");

    //checks if session is empty and not logged in
    if(userID == null && email == null){
        %><body><%

    //checks if logged in
    }else if(email != null && userID == null){

        session.setAttribute("userId", userId);
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

        %> <body onload=userPage('/logout','Logout');> <%

    }else{

        %> <body onload=userPage('/logout','Logout');> <%

    }
%>