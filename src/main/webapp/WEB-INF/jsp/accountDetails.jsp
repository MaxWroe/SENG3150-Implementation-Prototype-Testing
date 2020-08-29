<%@ page import="org.springframework.web.servlet.ModelAndView" %><%--
  JSP for viewing account details and editing.
  SENG3150 Group 3
  Date: 26/05/2020
  Time: 11:12 am
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Details</title>


    <link rel="stylesheet" type="text/css" href="/css/main.css">

   <%-- <script src="/js/dynamicLink.js"></script> --%>
</head>
<body>
<!-- session handler -->
    <%--<jsp:include page="sessionHandlerUser.jsp"/> --%>

<!-- Site header -->
<jsp:include page="header.jsp"/>


<main class="main-content">
    <div class="card-body">
        <h1>Account Details</h1>

        <!-- user's details -->
        <div class ="my-account">
            <div class="user-details">
                <h4>First Name: </h4> <p><%= request.getAttribute("firstName")%> </p><br>
                <h4>Last Name:  </h4>  <p><%= request.getAttribute("lastName")%></p><br>
                <h4>Email: </h4>  <p> <%= request.getAttribute("email")%> </p><br>
                <h4>User type: </h4>  <p><%= request.getAttribute("userType")%> </p><br>
                <h4>Date of Birth: </h4>  <p><%= request.getAttribute("dateOfBirth")%></p><br>
                <h4>Gender: </h4>  <p> <%= request.getAttribute("gender")%></p><br>
                <h4>Citizenship: </h4>  <p> <%= request.getAttribute("citizenship")%></p><br>
                <h4>Phone Number: </h4> <p><%= request.getAttribute("phone")%></p><br>
                <h4>Family Members: </h4> <p><%= request.getAttribute("familyMembers")%></p><br>
                <h4>Emergency Contacts:</h4> <p><%= request.getAttribute("emergencyContacts")%></p><br>
                <h4>Address: </h4> <p><%= request.getAttribute("address")%></p><br>
            </div>

          <button id="editAccount" type="submit" onclick="displayForm('accountForm')"> Edit Account </button>


        <!-- edit account form-->
        <div class = "account-form">

            <form id="accountForm" method="post" action="/accountDetails/edit" onsubmit="return validateRegister()" style="display: none">

                <jsp:include page="form.jsp"/>

                <!-- citizenship -->
                <label for="citizenship">Citizenship: </label>
                <select id ="citizenship" name="citizenship" required>
                   <option>Australian</option>
                   <option>Others</option>
                </select> <br>

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

    </div>
</main>

</body>
</html>
