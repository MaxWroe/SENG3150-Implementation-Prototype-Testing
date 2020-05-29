<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 26/05/2020
  Time: 11:12 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Details</title>


    <link rel="stylesheet" type="text/css" href="/css/main.css">

    <script src="/js/dynamicLink.js"></script>
</head>

<!-- session checker -->
<%
   String userID = (String)session.getAttribute("userId");

    //checks if user is logged in
   if(userID == null) {
        %> <jsp:forward page="errorPage.jsp"></jsp:forward> <%
   }else{
        %><body onload=userPage('/logout','Logout');> <%
   }%>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>Account Details</h1>

        <!-- user's details -->
        <div class ="my-account">
            <p>First Name: ${firstName}</p> <br>
            <p>Last Name: ${lastName}</p><br>
            <p>Email: ${email}</p><br>
            <p>User type: ${userType}</p> <br>
            <p>Date of Birth: ${dateOfBirth}</p><br>
            <p>Gender: ${gender}</p><br>
            <p>Citizenship: ${citizenship}</p><br>
            <p>Phone Number: ${phone}</p><br>
            <p>Family Members: ${familyMembers}</p><br>
            <p>Emergency Contacts: ${emergencyContact}</p><br>
            <p>Address: ${firstName}</p><br>

          <button id="editAccount" type="submit" onclick="displayForm('accountForm')"> Edit Account </button>


        <!-- edit account form-->
        <div class = "account-form">

            <form id="accountForm" method="post" action="/accountDetails/edit" onsubmit="return validateRegister()" style="display: none">

                <jsp:include page="form.jsp"/>

                <!-- citizenship -->
                <label for="citizenship">Citizenship: </label>
                <input id="citizenship" name ="citizenship" required/> <br>

                <!-- family members -->
                <label for="familyMembers">Family Members: </label>
                <input id="familyMembers" name ="familyMembers" required/> <br>

                <!-- emergency contacts -->
                <label for="emergencyContacts">Emergency Contacts: </label>
                <input type="number" id="emergencyContacts" name ="emergencyContacts" required/> <br>

                <!-- address -->
                <label for="address">Address: </label>
                <input id="address" name ="address" required/> <br>

                <!-- userID -->
                <input type ="hidden" id="userID" name="userID" value="<%= userID%>"/>
                <input type="submit" value="Edit Account"/><input type="reset" value="Reset"/>

            </form>
        </div>

    </div>
</main>

</body>
</html>
