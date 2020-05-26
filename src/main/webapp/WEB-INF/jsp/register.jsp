<%--
  Created by IntelliJ IDEA.
  User: acero
  Date: 19/05/2020
  Time: 5:52 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <script src="/js/formValidations.js"></script>
</head>
<body>
<!-- Site header -->
<jsp:include page="header.jsp"/>

    <main class="main-content">
        <div class="card-body">
            <h1>Register</h1>
            <h4>Fill in your information</h4>

                <!-- registration form-->
                <form id="registerForm" method="post" action="/register" onsubmit="return validateRegister()">

                    <!-- First Name -->
                    <label for="firstName">First Name: </label>
                    <input id="firstName" name ="firstName" required/> <br>

                    <!-- Last Name -->
                    <label for="lastName">Last Name: </label>
                    <input id="lastName" name ="lastName" required/> <br>

                    <!-- password -->
                    <label for="password">Password: </label>
                    <input type ="password" id="password" name ="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                           title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required/> <br>

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

                    <!-- confirm password -->
                    <label for="confirmPassword">Confirm Password: </label>
                    <input type ="password" id="confirmPassword" name ="confirmPassword" required/> <br>

                    <!-- email -->
                    <label for="email">Email: </label>
                    <input type ="email" id="email" name ="email" required/> <br>

                    <!-- phone no -->
                    <label for="phone">Phone No:</label>
                    <input type ="tel" id="phone" name ="phone" required/> <br>

                    <!-- date of birth -->
                    <label for="dateOfBirth">Date of birth: </label>
                    <input type="date" id="dateOfBirth" name ="dateOfBirth" required/> <br>

                    <!-- user types -->
                    <label>User type: </label>

                    <input type="radio" id="personal" name="userType" value="Personal" checked/>
                    <label for="personal">Personal</label>

                    <input type="radio" id="business" name="userType" value="Business"/>
                    <label for="business">Business</label>

                    <input type="radio" id="family" name="userType" value="Family"/>
                    <label for="family">Family</label> <br>



                    <input type="submit" value="Register"/><input type="reset" value="Reset"/>

                </form>
        </div>
    </main>

</body>
</html>
