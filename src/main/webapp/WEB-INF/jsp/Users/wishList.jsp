
<%--
  JSP for user wish list and add to wish list
  SENG3150 Group 3
  Date: 5/09/2020
  Time: 11:19 am
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wish List</title>

    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<!-- Site header -->
<jsp:include page="../header.jsp"/>

<main class="main-content">

        <h1>My Wish List</h1>
        <h4></h4>

        <%-- if wish list is empty --%>
        <div class="wrap-stats">
        <c:if test="${empty wishList}">
            <h4>Add a country to your wish list</h4>
        </c:if>

        <%-- parse all wish list --%>
        <c:forEach items="${wishList}" var ="wishList">
            <div class="manage-stats">
                <div class="register-group">
                    <div class="register-row1">
                        <div class="booking-info-left" style="font-weight: bold"><c:out value= "${wishList.countryCode3}"></c:out></div>
                        <div class="booking-input-left">Country code</div>
                    </div>
                    <div class="register-row1">
                     <div class="booking-info-right"><c:out value= "${wishList.countryName}"></c:out></div>
                        <div class="booking-input-right">Country name</div>
                    </div>
                </div>

                <div class="register-row">
                    <form id="removeWishList" method="post" action="${pageContext.request.contextPath}/wishList/remove">
                        <br>
                        <hr>
                        <!-- country code -->
                        <input type ="hidden" id="country" name="country" value="<c:out value= "${wishList.countryCode3}"></c:out>" />
                        <button type="submit">Remove</button>

                    </form>
                </div>
            </div>

        </c:forEach>
        </div>


        <div class="wish-list">
            <div id="wishlist-add">
                <h4>Add a country to travel wishlist</h4>
                <form action="${pageContext.request.contextPath}/wishList" method="post">
                    <div class="register-row">
                        <input class= "input" placeholder="Country" list="countries" id="countryCode" name="countryCode" required>
                        <span class="symbol-input">
                            <i class="fa fa-flag" aria-hidden="true"> </i>
                        </span>
                    </div>
                    <button type="submit">Add</button>

                    <!-- Airport destinations -->
                    <datalist id="countries">
                        <option value='A'>Anla</option>
                        <option value='ABW'>Aruba</option>
                        <option value='AFG'>Afghanistan</option>
                        <option value='AIA'>Anguilla</option>
                        <option value='ALA'>Åland Islands</option>
                        <option value='ALB'>Albania</option>
                        <option value='AND'>Andorra</option>
                        <option value='ANT'>Netherlands Antilles</option>
                        <option value='ARE'>United Arab Emirates</option>
                        <option value='ARG'>Argentina</option>
                        <option value='ARM'>Armenia</option>
                        <option value='ASM'>American</option>
                        <option value='ATA'>Antarctica</option>
                        <option value='ATF'>French Southern Territories</option>
                        <option value='ATG'>Antigua and Barbuda</option>
                        <option value='AUS'>Australia</option>
                        <option value='AUT'>Austria</option>
                        <option value='AZE'>Azerbaijan</option>
                        <option value='BDI'>Burundi</option>
                        <option value='BEL'>Belgium</option>
                        <option value='BEN'>Benin</option>
                        <option value='BFA'>Burkina Faso</option>
                        <option value='BGD'>Bangladesh</option>
                        <option value='BGR'>Bulgaria</option>
                        <option value='BHR'>Bahrain</option>
                        <option value='BHS'>Bahamas</option>
                        <option value='BIH'>Bosnia and Herzevina</option>
                        <option value='BLR'>Belarus</option>
                        <option value='BLZ'>Belize</option>
                        <option value='BMU'>Bermuda</option>
                        <option value='BOL'>Bolivia</option>
                        <option value='BRA'>Brazil</option>
                        <option value='BRB'>Barbados</option>
                        <option value='BRN'>Brunei Darussalam</option>
                        <option value='BTN'>Bhutan</option>
                        <option value='BVT'>Bouvet Island</option>
                        <option value='BWA'>Botswana</option>
                        <option value='CAF'>Central African Republic</option>
                        <option value='CAN'>Canada'</option>
                        <option value='CCK'>Cocos (Keeling) Islands</option>
                        <option value='CHE'>Switzerland</option>
                        <option value='CHL'>Chile</option>
                        <option value='CHN'>China</option>
                        <option value='CIV'>Côte d\'Ivoire</option>
                        <option value='CMR'>Cameroon</option>
                        <option value='COD'>Democratic Republic of the Con</option>
                        <option value='COG'>Con</option>
                        <option value='COK'>Cook Islands</option>
                        <option value='COL'>Colombia</option>
                        <option value='COM'>Comoros</option>
                        <option value='CPV'>Cape Verde</option>
                        <option value='CRI'>Costa Rica</option>
                        <option value='CUB'>Cuba</option>
                        <option value='CXR'>Christmas Island</option>
                        <option value='CYM'>Cayman Islands</option>
                        <option value='CYP'>Cyprus</option>
                        <option value='CZE'>Czech Republic</option>
                        <option value='DEU'>Germany</option>
                        <option value='DJI'>Djibouti</option>
                        <option value='DMA'>Dominica</option>
                        <option value='DNK'>Denmark</option>
                        <option value='DOM'>Dominican Republic</option>
                        <option value='DZA'>Algeria</option>
                        <option value='ECU'>Ecuador</option>
                        <option value='EGY'>Egypt</option>
                        <option value='ENG'>England</option>
                        <option value='ERI'>Eritrea</option>
                        <option value='ESH'>Western Sahara</option>
                        <option value='ESP'>Spain</option>
                        <option value='EST'>Estonia</option>
                        <option value='ETH'>Ethiopia</option>
                        <option value='FIN'>Finland</option>
                        <option value='FJI'>Fiji</option>
                        <option value='FLK'>Falkland Islands (Malvinas)</option>
                        <option value='FRA'>France</option>
                        <option value='FRO'>Faroe Islands</option>
                        <option value='FSM'>Micronesia, Federated States of</option>
                        <option value='GAB'>Gabon</option>
                        <option value='GBR'>United Kingdom</option>
                        <option value='GEO'>Georgia/option>
                        <option value='GGY'>Guernsey</option>
                        <option value='GHA'>Ghana</option>
                        <option value='GIB'>Gibraltar</option>
                        <option value='GIN'>Guinea</option>
                        <option value='GLP'>Guadeloupe</option>
                        <option value='GMB'>Gambia</option>
                        <option value='GNB'>Guinea-Bissau</option>
                        <option value='GNQ'>Equatorial Guinea</option>
                        <option value='GRC'>Greece</option>
                        <option value='GRD'>Grenada</option>
                        <option value='GRL'>Greenland</option>
                        <option value='GTM'>Guatemala</option>
                        <option value='GUF'>French Guiana</option>
                        <option value='GUM'>Guam</option>
                        <option value='GUY'>Guyana</option>
                        <option value='HKG'>Hong Kong</option>
                        <option value='HMD'>Heard Island and McDonald Island</option>
                        <option value='HND'>Honduras</option>
                        <option value='HRV'>Croatia</option>
                        <option value='HTI'>Haiti</option>
                        <option value='HUN'>Hungary</option>
                        <option value='IDN'>Indonesia</option>
                        <option value='IMN'>Isle of Man</option>
                        <option value='IND'>India</option>
                        <option value='IOT'>British Indian Ocean Territory</option>
                        <option value='IRL'>Ireland</option>
                        <option value='IRN'>Iran, Islamic Republic of</option>
                        <option value='IRQ'>Iraq</option>
                        <option value='ISL'>Iceland</option>
                        <option value='ISR'>Israel</option>
                        <option value='ITA'>Italy</option>
                        <option value='JAM'>Jamaica</option>
                        <option value='JEY'>Jersey</option>
                        <option value='JOR'>Jordan</option>
                        <option value='JPN'>Japan</option>
                        <option value='KAZ'>Kazakhstan</option>
                        <option value='KEN'>Kenya</option>
                        <option value='KGZ'>Kyrgyzstan</option>
                        <option value='KHM'>Cambodia</option>
                        <option value='KIR'>Kiribati</option>
                        <option value='KNA'>Saint Kitts and Nevis</option>
                        <option value='KOR'>Republic of Korea</option>
                        <option value='KWT'>Kuwait</option>
                        <option value='LAO'>Lao People\'s Democratic Republic</option>
                        <option value='LBN'>Lebanon</option>
                        <option value='LBR'>Liberia</option>
                        <option value='LBY'>Libyan Arab Jamahiriya</option>
                        <option value='LCA'>Saint Lucia</option>
                        <option value='LIE'>Liechtenstein</option>
                        <option value='LKA'>Sri Lanka</option>
                        <option value='LSO'>Lesotho</option>
                        <option value='LTU'>Lithuania</option>
                        <option value='LUX'>Luxembourg</option>
                        <option value='LVA'>Latvia</option>
                        <option value='MAC'>Macao</option>
                        <option value='MAR'>Morocco</option>
                        <option value='MCO'>Monaco</option>
                        <option value='MDA'>Moldova</option>
                        <option value='MDG'>Madagascar</option>
                        <option value='MDV'>Maldives</option>
                        <option value='MEX'>Mexico</option>
                        <option value='MHL'>Marshall Islands</option>
                        <option value='MKD'>Macedonia, the former Yuslav Repunlic</option>
                        <option value='MLI'>Mali</option>
                        <option value='MLT'>Malta'</option>
                        <option value='MMR'>Myanmar</option>
                        <option value='MNE'>Montenegro</option>
                        <option value='MNG'>Monlia</option>
                        <option value='MNP'>Northern Mariana Islands</option>
                        <option value='MOZ'>Mozambique</option>
                        <option value='MRT'>Mauritania</option>
                        <option value='MSR'>Montserrat</option>
                        <option value='MTQ'>Martinique</option>
                        <option value='MUS'>Mauritius</option>
                        <option value='MWI'>Malawi</option>
                        <option value='MYS'>Malaysia</option>
                        <option value='MYT'>Mayotte</option>
                        <option value='NAM'>Namibia</option>
                        <option value='NCL'>New Caledonia</option>
                        <option value='NER'>Niger</option>
                        <option value='NFK'>Norfolk Island</option>
                        <option value='NGA'>Nigeria</option>
                        <option value='NIC'>Nicaragua</option>
                        <option value='NIR'>Northern Island</option>
                        <option value='NIU'>Niue</option>
                        <option value='NLD'>Netherlands</option>
                        <option value='NOR'>Norway</option>
                        <option value='NPL'>Nepal</option>
                        <option value='NRU'>Nauru</option>
                        <option value='NZL'>New Zealand</option>
                        <option value='OMN'>Oman</option>
                        <option value='PAK'>Pakistan</option>
                        <option value='PAN'>Panama</option>
                        <option value='PCN'>Pitcairn</option>
                        <option value='PER'>Peru</option>
                        <option value='PHL'>Philippines</option>
                        <option value='PLW'>Palau</option>
                        <option value='PNG'>Papua New Guinea</option>
                        <option value='POL'>Poland</option>
                        <option value='PRI'>Puerto Rico</option>
                        <option value='PRK'>Korea, Democratic People\'s Republic</option>
                        <option value='PRT'>Portugal</option>
                        <option value='PRY'>Paraguay'</option>
                        <option value='PSE'>Palestinian Territory, Occupied</option>
                        <option value='PYF'>French Polynesia</option>
                        <option value='QAT'>Qatar</option>
                        <option value='REU'>Réunion</option>
                        <option value='ROU'>Romania</option>
                        <option value='RUS'>Russian Fede</option>
                        <option value='RWA'>Rwanda</option>
                        <option value='SAU'>Saudi Arabia</option>
                        <option value='SCO'>Scotland</option>
                        <option value='SDN'>Sudan</option>
                        <option value='SEN'>Senega</option>
                        <option value='SGP'>Singapore</option>
                        <option value='SGS'>South Georgia and the South Sandwich Islands</option>
                        <option value='SHN'>Saint Helena</option>
                        <option value='SJM'>Svalbard and Jan Mayen</option>
                        <option value='SLB'>Solomon Islands</option>
                        <option value='SLE'>Sierra Leone</option>
                        <option value='SLV'>El Salvador</option>
                        <option value='SMR'>San Marino</option>
                        <option value='SOM'>Somalia</option>
                        <option value='SPM'>Saint Pierre and Miquelon</option>
                        <option value='SRB'>Serbia</option>
                        <option value='STP'>Sao Tome and Principe</option>
                        <option value='SUR'>Suriname</option>
                        <option value='SVK'>Slovakia</option>
                        <option value='SVN'>Slovenia</option>
                        <option value='SWE'>Sweden</option>
                        <option value='SWZ'>Swaziland</option>
                        <option value='SYC'>Seychelles</option>
                        <option value='SYR'>Syrian Arab Republic</option>
                        <option value='T'>To</option>
                        <option value='TCA'>Turks and Caicos Islands</option>
                        <option value='TCD'>Chad</option>
                        <option value='THA'>Thailand</option>
                        <option value='TJK'>Tajikistan</option>
                        <option value='TKL'>Tokelau</option>
                        <option value='TKM'>Turkmenistan</option>
                        <option value='TLS'>Timor-Leste</option>
                        <option value='TON'>Tonga</option>
                        <option value='TTO'>Trinidad and Toba</option>
                        <option value='TUN'>Tunisia</option>
                        <option value='TUR'>Turkey</option>
                        <option value='TUV'>Tuvalu</option>
                        <option value='TWN'>Taiwan</option>
                        <option value='TZA'>Tanzania, United Republic of</option>
                        <option value='UGA'>Uganda</option>
                        <option value='UKR'>Ukraine</option>
                        <option value='UMI'>United States Minor Outlying Islands</option>
                        <option value='URY'>Uruguay</option>
                        <option value='USA'>United States</option>
                        <option value='UZB'>Uzbekistan</option>
                        <option value='VAT'>Holy See (Vatican City State)</option>
                        <option value='VCT'>Saint Vincent and the Grenadines</option>
                        <option value='VEN'>Venezuela</option>
                        <option value='VGB'>Virgin Islands, British</option>
                        <option value='VIR'>Virgin Islands, U.S.</option>
                        <option value='VNM'>Viet Nam</option>
                        <option value='VUT'>Vanuatu</option>
                        <option value='WAL'>Wales</option>
                        <option value='WLF'>Wallis and Futuna</option>
                        <option value='WSM'>Samoa</option>
                        <option value='YEM'>Yemen</option>
                        <option value='ZAF'>South Africa</option>
                        <option value='ZMB'>Zambia</option>
                        <option value='ZWE'>Zimbabwe</option>
                    </datalist>
                </form>
            </div>

        </div>


</main>

</body>
</html>
