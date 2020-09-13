

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


                    <!-- user form -->

                    <!-- First Name -->
                    <div class="register-group">
                        <!-- <div class="register-row"> -->
                        <div class="register-row1">
                            <input class="input" id="firstName" name ="firstName" placeholder="First Name" value="<%= request.getAttribute("firstName")%>" required/>
                            <span class="symbol-input">
                                <i class="fa fa-user-circle-o" aria-hidden="true"> </i>
                            </span>
                        </div>
                        <!-- </div> -->

                        <!-- Last Name -->
                        <!-- <div class="register-row"> -->
                        <div class="register-row1">
                            <input class="input" id="lastName" name ="lastName" placeholder="Last Name" value="<%= request.getAttribute("lastName")%>" required/> <br>
                            <span class="symbol-input">
                                <i class="fa fa-user-circle-o" aria-hidden="true"> </i>
                            </span>
                        </div>
                        <!-- </div> -->
                    </div>

                    <!-- preferred airport -->

                    <div class="register-row">
                        <input class="input" list="airports" name="preferredAirport" id="preferredAirport" placeholder="Nearest Airport" required>
                        <span class="symbol-input">
                            <i class="fa fa-plane" aria-hidden="true"> </i>
                        </span>
                    </div>

                    <div class="register-group">
                        <!-- gender -->




                        <!--<label class="gender">Gender: </label> -->
                        <div class="register-row1">
                            <div class="register-gender" >
                                <input type="radio" id="male" name="gender" value="Male" checked/>
                                <span class="symbol-input">
                                <i class=" fa fa-male" aria-hidden="true"> </i>
                       </span>
                                <label for="male">Male</label>
                            </div>
                        </div>

                        <div class="register-row1">
                            <div class="register-gender">
                                <input type="radio" id="female" name="gender" value="Female"/>
                                <span class="symbol-input">
                                <i class=" fa fa-female" aria-hidden="true"> </i>
                            </span>
                                <label  for="female">Female</label><br>
                            </div>
                        </div>


                    </div>

                    <div class="register-group">
                        <!-- password -->
                        <div class="register-row1">
                            <input class="input" placeholder="Password" type ="password" id="password" name ="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                   title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required/> <br>
                            <span class="symbol-input">
                                <i class="fa fa-lock" aria-hidden="true"> </i>
                            </span>
                        </div>

                        <!-- confirm password -->
                        <div class="register-row1">
                            <input class="input" placeholder="Confirm Password" type ="password" id="confirmPassword" name ="confirmPassword" required/> <br>
                            <span class="symbol-input">
                                <i class="fa fa-lock" aria-hidden="true"> </i>
                            </span>
                        </div>
                    </div>
                    <!-- message when making a password-->
                    <div id="message" style="display: none">
                        <h4>Password must contain the following:</h4>
                        <p id="letter" class="invalid">A <b>lowercase</b> letter</p> <br>
                        <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p><br>
                        <p id="number" class="invalid">A <b>number</b></p><br>
                        <p id="length" class="invalid">Minimum <b>8 characters</b></p><br>
                    </div>

                    <!-- password strength script-->
                    <script>
                        var myInput = document.getElementById("password");
                        var letter = document.getElementById("letter");
                        var capital = document.getElementById("capital");
                        var number = document.getElementById("number");
                        var length = document.getElementById("length");

                        // When the user clicks on the password field, show the message box
                        myInput.onfocus = function() {
                            document.getElementById("message").style.display = "block";
                        }

                        // When the user clicks outside of the password field, hide the message box
                        myInput.onblur = function() {
                            document.getElementById("message").style.display = "none";
                        }

                        // When the user starts to type something inside the password field
                        myInput.onkeyup = function() {
                            // Validate lowercase letters
                            var lowerCaseLetters = /[a-z]/g;
                            if(myInput.value.match(lowerCaseLetters)) {
                                letter.classList.remove("invalid");
                                letter.classList.add("valid");
                            } else {
                                letter.classList.remove("valid");
                                letter.classList.add("invalid");
                            }

                            // Validate capital letters
                            var upperCaseLetters = /[A-Z]/g;
                            if(myInput.value.match(upperCaseLetters)) {
                                capital.classList.remove("invalid");
                                capital.classList.add("valid");
                            } else {
                                capital.classList.remove("valid");
                                capital.classList.add("invalid");
                            }

                            // Validate numbers
                            var numbers = /[0-9]/g;
                            if(myInput.value.match(numbers)) {
                                number.classList.remove("invalid");
                                number.classList.add("valid");
                            } else {
                                number.classList.remove("valid");
                                number.classList.add("invalid");
                            }

                            // Validate length
                            if(myInput.value.length >= 8) {
                                length.classList.remove("invalid");
                                length.classList.add("valid");
                            } else {
                                length.classList.remove("valid");
                                length.classList.add("invalid");
                            }
                        }
                    </script>


                    <div class="register-row">
                        <!-- email -->
                        <input class="input" placeholder="Email" type ="email" id="email" name ="email" value="<%= request.getAttribute("email")%>" required/> <br>
                        <span class="symbol-input">
                            <i class="fa fa-envelope" aria-hidden="true"> </i>
                        </span>
                    </div>

                    <div class="register-row">
                        <!-- phone no -->
                        <input class="input" placeholder="Phone Number" type ="tel" id="phone" name ="phone" pattern="[0-9].{8,}" value="<%= request.getAttribute("phone")%>" required/> <br>
                        <span class="symbol-input">
                            <i class="fa fa-phone" aria-hidden="true"> </i>
                        </span>
                    </div>

                    <div class="register-row">
                        <!-- date of birth -->

                        <input class="input" placeholder="Date of birth" type="date" id="dateOfBirth" name ="dateOfBirth" value="<%= request.getAttribute("dateOfBirth")%>" required/> <br>
                    </div>


                    <!-- user types -->
                    <div class="register-group">



                        <div class="register-row1">
                            <div class="register-gender">
                                <input type="radio" id="personal" name="userType" value="Personal" checked/>
                                <label for="personal">Personal</label>
                            </div>
                        </div>

                        <div class="register-row1">
                            <div class="register-gender">
                                <input type="radio" id="business" name="userType" value="Business"/>
                                <label for="business">Business</label>
                            </div>
                        </div>

                        <div class="register-row1">
                            <div class="register-gender">
                                <input type="radio" id="family1" name="userType" value="Family"/>
                                <label for="family">Family</label> <br>
                            </div>
                        </div>


                    </div>


                    <!-- Airport destinations -->
                    <datalist id="airports">
                        <option value="ADL">Adelaide - ADL</option>
                        <option value="AMS">Amsterdam - AMS</option>
                        <option value="ATL">Atlanta - ATL</option>
                        <option value="BKK">Bangkok - BKK</option>
                        <option value="BNE">Brisbane - BNE</option>
                        <option value="CBR">Canberra - CBR</option>
                        <option value="CDG">Paris - Charles De Gaulle - CDG</option>
                        <option value="CNS">Cairns - CNS</option>
                        <option value="DOH">Doha - DOH</option>
                        <option value="DRW">Darwin - DRW</option>
                        <option value="DXB">Dubai - DXB</option>
                        <option value="FCO">Rome-Fiumicino - FCO</option>
                        <option value="GIG">Rio De Janeiro - GIG</option>
                        <option value="HBA">Hobart - HBA</option>
                        <option value="HEL">Helsinki - HEL</option>
                        <option value="HKG">Hong Kong - HKG</option>
                        <option value="HNL">Honolulu - HNL</option>
                        <option value="JFK">New York - JFK - JFK</option>
                        <option value="JNB">Johannesburg - JNB</option>
                        <option value="KUL">Kuala Lumpur - KUL</option>
                        <option value="LAX">Los Angeles - LAX</option>
                        <option value="LGA">New York - Laguardia - LGA</option>
                        <option value="LGW">London-Gatwick - LGW</option>
                        <option value="LHR">London-Heathrow - LHR</option>
                        <option value="MAD">Madrid - MAD</option>
                        <option value="MEL">Melbourne - MEL</option>
                        <option value="MIA">Miami - MIA</option>
                        <option value="MUC">Munich - MUC</option>
                        <option value="NRT">Tokyo - Narita - NRT</option>
                        <option value="OOL">Gold Coast - OOL</option>
                        <option value="ORD">Chicago - OHare Intl. - ORD</option>
                        <option value="ORY">Paris - Orly - ORY</option>
                        <option value="PER">Perth - PER</option>
                        <option value="SFO">San Francisco - SFO</option>
                        <option value="SIN">Singapore - SIN</option>
                        <option value="SYD">Sydney - SYD</option>
                        <option value="VIE">Vienna - VIE</option>
                        <option value="YYZ">Toronto - YYZ</option>
                    </datalist>

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
