

<%--
  JSP for viewing account details and editing.
  SENG3150 Group 3
  Date: 26/05/2020
  Time: 11:12 am
--%>

<%@ page import="org.springframework.web.servlet.ModelAndView" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Details</title>


    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <script src="/js/formValidations.js"></script>
    <script src="/js/dynamicLink.js"></script>


</head>
<body>
<!-- session handler -->
    <%--<jsp:include page="sessionHandlerUser.jsp"/> --%>

<!-- Site header -->
<jsp:include page="../header.jsp"/>


<main class="main-content">

        <h1>Account Details</h1>

        <!-- user's details -->
        <div class ="account-wrap">
            <div class="user-details">

                <!-- First and last name -->
                <div class="register-group">
                    <div class="register-row1">
                        <input class="input" name ="firstName" placeholder="<%= request.getAttribute("firstName")%>" disabled/>
                        <span class="symbol-input">
                            <i class="fa fa-user-circle-o" aria-hidden="true"> </i>
                        </span>
                    </div>
                    <div class="register-row1">
                        <input class="input" name ="lastName" placeholder="<%= request.getAttribute("lastName")%>" disabled/>
                        <span class="symbol-input">
                            <i class="fa fa-user-circle-o" aria-hidden="true"> </i>
                        </span>
                    </div>
                </div>

                <!-- gender and citizenship -->
                <div class="register-group">
                    <div class="register-row1">

                            <% if (request.getAttribute("gender")== "Male") { %>
                                <input class="input" id="gender" name ="gender" placeholder="<%= request.getAttribute("gender")%>" disabled/>
                                <span class="symbol-input">
                                    <i class="fa fa-male" aria-hidden="true"> </i>
                                </span>
                             <%}else{%>

                                <input class="input" id="gender" name ="gender" placeholder="<%= request.getAttribute("gender")%>" disabled/>
                                <span class="symbol-input">
                                    <i class="fa fa-female" aria-hidden="true"> </i>
                                </span>
                           <%}%>


                     </div>
                    <div class="register-row1">
                        <input class="input" id="citizen" name ="citizen" placeholder="<%= request.getAttribute("citizenship")%>" disabled/>
                        <span class="symbol-input">
                            <i class="fa fa-user" aria-hidden="true"> </i>
                        </span>
                    </div>
                </div>

                <!-- Home address -->
                <div class="register-row">
                    <% if (request.getAttribute("address") == null) { %>
                        <input class="input"  placeholder="Home Address" disabled/>
                        <span class="symbol-input">
                            <i class="fa fa-address-card" aria-hidden="true"> </i>
                        </span>
                    <%}else{%>
                        <input class="input"  placeholder="<%= request.getAttribute("address")%>" disabled/>
                        <span class="symbol-input">
                                <i class="fa fa-address-card" aria-hidden="true"> </i>
                        </span>
                    <%}%>
                </div>

                <!-- EMAIL and DOB -->
                <div class="register-group">
                    <div class="register-row1">
                         <input class="input" placeholder="<%= request.getAttribute("email")%> " type ="email" name ="email" disabled/>
                            <span class="symbol-input">
                                <i class="fa fa-envelope" aria-hidden="true"> </i>
                            </span>
                    </div>
                    <div class="register-row1">
                        <input class="input" placeholder="<%= request.getAttribute("dateOfBirth")%>"  name ="dateOfBirth" disabled/>
                        <span class="symbol-input">
                            <i class="fa fa-calendar-o" aria-hidden="true"> </i>
                        </span>
                    </div>
                </div>

                <!-- phone and user type -->
                <div class="register-group">
                    <div class="register-row1">
                        <input class="input" placeholder="<%= request.getAttribute("phone")%>" type ="tel" name ="phone" pattern="[0-9].{8,}" disabled/>
                        <span class="symbol-input">
                            <i class="fa fa-phone" aria-hidden="true"> </i>
                        </span>
                    </div>

                    <div class="register-row1">
                        <% if (request.getAttribute("userType").equals(0)) { %>
                            <input class="input" id="userType" name ="userType" placeholder="Personal" disabled/>
                            <span class="symbol-input">
                                <i class="fa fa-address-book" aria-hidden="true"> </i>
                            </span>
                        <%}else if (request.getAttribute("userType").equals(1)){%>
                            <input class="input" id="userType" name ="userType" placeholder="Family" disabled/>
                            <span class="symbol-input">
                                    <i class="fa fa-address-book" aria-hidden="true"> </i>
                            </span>
                        <%}else{%>
                            <input class="input" id="userType" name ="userType" placeholder="Business" disabled/>
                            <span class="symbol-input">
                                        <i class="fa fa-address-book" aria-hidden="true"> </i>
                            </span>
                        <%}%>
                    </div>
                </div>



                <!-- Family Members -->
                <div class="register-row">
                    <% if (request.getAttribute("familyMembers") == null) { %>
                        <input class="input" id="family" name ="family" placeholder="Family Members" disabled/>
                        <span class="symbol-input">
                                <i class="fa fa-users" aria-hidden="true"> </i>
                        </span>

                    <%} else { %>
                        <input class="input" id="family" name ="family" placeholder="<%= request.getAttribute("familyMembers")%>" disabled/>
                        <span class="symbol-input">
                            <i class="fa fa-users" aria-hidden="true"> </i>
                        </span>
                    <% } %>
                </div>

                <!-- Emergency Contact -->
                <div class="register-row">
                    <% if (request.getAttribute("emergencyContacts") == null) { %>
                        <input class="input" id="contacts" name ="contacts" placeholder="Emergency Contacts" disabled/>
                        <span class="symbol-input">
                                <i class="fa fa-warning" aria-hidden="true"> </i>
                        </span>
                    <%} else { %>
                        <input class="input" id="contacts" name ="contacts" placeholder="<%= request.getAttribute("emergencyContacts")%>" disabled/>
                        <span class="symbol-input">
                            <i class="fa fa-warning" aria-hidden="true"> </i>
                        </span>
                    <% } %>
                </div>


            </div>

            <button class="edit-account-button" id="editAccount" type="submit" onclick="displayForm('accountForm')"> Edit Account </button>


            <!-- edit account form-->
            <div class = "account-form">


                <form id="accountForm" method="post" action="/accountDetails/edit" onsubmit="return validateRegister()" style="display: none">

                    <jsp:include page="../form.jsp"/>

                    <!-- citizenship -->
                    <div class="register-row">
                        <select class="input" id ="citizenship" name="citizenship" required>
                           <option>Australian</option>
                           <option>Others</option>
                        </select>

                        <span class="symbol-input">
                            <i class="fa fa-user" aria-hidden="true"> </i>
                        </span>
                    </div>

                    <!-- family members -->
                    <div class="register-row">
                        <input class="input" placeholder="Family Members" id="familyMembers" name ="familyMembers" required/>
                        <span class="symbol-input">
                                <i class="fa fa-users" aria-hidden="true"> </i>
                        </span>
                    </div>

                    <!-- emergency contacts,  -->
                    <div class="register-row">
                        <input class="input" placeholder="Emergency Contact" id="emergencyContacts" name ="emergencyContacts" required/>
                        <span class="symbol-input">
                                <i class="fa fa-warning" aria-hidden="true"> </i>
                        </span>
                    </div>

                    <!-- address -->
                    <div class="register-row">
                        <input class="input" placeholder="Home Address" id="address" name ="address" required/>
                            <span class="symbol-input">
                                <i class="fa fa-address-card" aria-hidden="true"> </i>
                            </span>
                    </div>

                    <!-- userID -->
                    <input type ="hidden" id="userID" name="userID" value="<%= session.getAttribute("userId")%>"/>

                    <div class="register-group">
                        <div class="register-row1">
                            <input class="input-submit-invert" type="submit" value="Submit"/>
                        </div>
                        <div class="register-row1">
                            <input class="input-submit-invert" type="reset" value="Reset"/>
                        </div>
                    </div>

                </form>

            </div>
        </div>


</main>

</body>
</html>
