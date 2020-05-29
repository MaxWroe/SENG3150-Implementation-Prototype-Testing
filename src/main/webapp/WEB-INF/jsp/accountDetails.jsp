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
<body onload=userPage("/home","Logout");>

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
            <p>Date of Birth: ${dateOfBirth}</p><br>
            <p>Sex: ${sex}</p><br>
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

                <input type="submit" value="Edit Account"/><input type="reset" value="Reset"/>

            </form>
        </div>

    </div>
</main>

</body>
</html>
