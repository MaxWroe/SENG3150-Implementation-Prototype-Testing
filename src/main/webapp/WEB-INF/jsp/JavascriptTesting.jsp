<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Test Suite</title>
    <link rel="stylesheet" href="https://code.jquery.com/qunit/qunit-2.11.3.css">

</head>
<body>
<div id="qunit"></div>
<div id="qunit-fixture"></div>
<script src="https://code.jquery.com/qunit/qunit-2.11.3.js"></script>
<script src="${pageContext.request.contextPath}/js/test.js"></script>
<script src="${pageContext.request.contextPath}/js/formValidations.js"></script>
<script src="${pageContext.request.contextPath}/js/dynamicLink.js"></script>
<script src="${pageContext.request.contextPath}/js/flightSelectAssistor.js"></script>
<script src="${pageContext.request.contextPath}/js/searchFormAssistor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<a style="visibility:hidden" id="password"></a>
<a style="visibility:hidden" id="confirmPassword"></a>
<a style="visibility:hidden" type ="tel" id="phone" name ="phone" pattern="[0-9].{8,}"></a>
<a style="visibility:hidden" id="dateOfBirth"></a>
<a style="visibility:hidden" id="dynamicLink"></a>
<!-- Airport destinations -->
<datalist id="destinations">
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
<form style="visibility:hidden" name="searchFlight" method="post" id="searchFlight" action="${pageContext.request.contextPath}/search" onsubmit="return validateForm()">
    <a  style="visibility:hidden" id="departureLocation" name="departureLocation" for="departureLocation"></a>
    <a  style="visibility:hidden" id="arrivalLocation" name="arrivalLocation" for="arrivalLocation"></a>
</form>
<script>
    QUnit.test('All valid information', function(assert){
        setVarsFormValidation('password','password','0000000000','1990-01-01')
        assert.true(validateRegister(), "true succeeds");
    });
    QUnit.test('wrong password', function(assert){
        setVarsFormValidation('password','nope','0000000000','1990-01-01')
        assert.false(validateRegister(), "should be false");
    });
    QUnit.test('Under 18', function(assert){
        setVarsFormValidation('password','password','0000000000','2010-01-01')
        assert.false(validateRegister(), "should be false");
    });
    QUnit.test('wrong password', function(assert){
        setVarsFormValidation('Thiscantbeequal','a','0000000000','1990-01-01')
        assert.false(validateRegister(), "should be false");
    });
    QUnit.test('Under 18', function(assert){
        setVarsFormValidation('password','password','0000000000','2020-01-01')
        assert.false(validateRegister(), "should be false");
    });
</script>

<script>
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('1990-01-01'),"30", "testing getAge function(30)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('1990-12-01'),"29", "testing getAge function(29)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('-271821-05-01'),"273841", "testing getAge function(273841)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('2026-05-01'),"-6", "testing getAge function(-5)");
    });

</script>

<script>
    QUnit.test('All valid information', function(assert){
        dynamicLink('a', 'b')
        assert.equal('b', document.getElementById("dynamicLink").innerHTML, 'testing dynamic Link inner true');
    });
    QUnit.test('Invalid Test', function(assert){
        dynamicLink('b', 'a')
        assert.equal('a', document.getElementById("dynamicLink").innerHTML, 'testing dynamic Link inner false');
    });
    QUnit.test('Test should fail', function(assert){
        dynamicLink('b', 'a')
        assert.equal('http://localhost:8080/b', document.getElementById("dynamicLink").href, 'testing dynamic Link href false');
    });
    QUnit.test('All valid information', function(assert){
        dynamicLink('a', 'b')
        assert.equal('http://localhost:8080/a', document.getElementById("dynamicLink").href, 'testing dynamic Link href true');
    });
</script>

<script>
    QUnit.test('Valid search form', function(assert){
        setVarsFormValidationFlights('ADL', 'BNE')
        $('#departureLocation').val(document.getElementById("departureLocation"))
        $('#arrivalLocation').val(document.getElementById("arrivalLocation"))
        assert.true(validateForm(), "true succeeds");
    });
    QUnit.test('Valid search form', function(assert){
        setVarsFormValidationFlights('BNE', 'ADL')
        $('#departureLocation').val(document.getElementById("departureLocation"))
        $('#arrivalLocation').val(document.getElementById("arrivalLocation"))
        assert.true(validateForm(), "true succeeds");
    });
    QUnit.test('Invalid duplicate search destination', function(assert){
        setVarsFormValidationFlights('BNE', 'BNE')
        $('#departureLocation').val(document.getElementById("departureLocation"))
        $('#arrivalLocation').val(document.getElementById("arrivalLocation"))
        assert.false(validateForm(), "should return false");
    });
    QUnit.test('Valid search form', function(assert){
        setVarsFormValidationFlights('ADL', 'YYZ')
        $('#departureLocation').val(document.getElementById("departureLocation"))
        $('#arrivalLocation').val(document.getElementById("arrivalLocation"))
        assert.true(validateForm(), "true succeeds");
    });
</script>


</body>
</html>