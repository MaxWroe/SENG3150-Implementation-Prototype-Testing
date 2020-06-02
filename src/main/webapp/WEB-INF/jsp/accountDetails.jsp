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

<!-- session handler -->
<jsp:include page="sessionHandlerUser.jsp"/>

<!-- Site header -->
<jsp:include page="header.jsp"/>

<main class="main-content">
    <div class="card-body">
        <h1>Account Details</h1>

        <!-- user's details -->
        <div class ="my-account">
            <div class="user-details">
                <p>First Name: <%= session.getAttribute("firstName")%></p> <br>
                <p>Last Name: <%= session.getAttribute("lastName")%></p><br>
                <p>Email: <%= session.getAttribute("email")%></p><br>
                <p>User type: <%= session.getAttribute("userType")%></p> <br>
                <p>Date of Birth: <%= session.getAttribute("dateOfBirth")%></p><br>
                <p>Gender: <%= session.getAttribute("gender")%></p><br>
                <p>Citizenship: <%= session.getAttribute("citizenship")%></p><br>
                <p>Phone Number: <%= session.getAttribute("phone")%></p><br>
                <p>Family Members: <%= session.getAttribute("familyMembers")%></p><br>
                <p>Emergency Contacts: <%= session.getAttribute("emergencyContacts")%></p><br>
                <p>Address: <%= session.getAttribute("address")%></p><br>
            </div>

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

                <!-- emergency contacts,  -->
                <label for="emergencyContacts">Emergency Contacts: </label>
                <input id="emergencyContacts" name ="emergencyContacts" required/> <br>

                <!-- address -->
                <label for="address">Address: </label>
                <input id="address" name ="address" required/> <br>

                <!-- userID -->
                <input type ="hidden" id="userID" name="userID" value="<%= session.getAttribute("userId")%>"/>
                <input type="submit" value="Edit Account"/><input type="reset" value="Reset"/>

            </form>
        </div>

    </div>
</main>

</body>
</html>
