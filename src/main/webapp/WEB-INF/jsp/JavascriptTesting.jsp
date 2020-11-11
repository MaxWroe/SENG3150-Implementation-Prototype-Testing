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

<a style="visibility:hidden" id="password"></a>
<a style="visibility:hidden" id="confirmPassword"></a>
<a style="visibility:hidden" type ="tel" id="phone" name ="phone" pattern="[0-9].{8,}"></a>
<a style="visibility:hidden" id="dateOfBirth"></a>


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
    QUnit.test('All valid information', function(assert){
        setVarsFormValidation('password','password','0000000000','2002-10-26')
        assert.true(validateRegister(), "true succeeds");
    });

    QUnit.test('All valid information', function(assert){
        setVarsFormValidation('a','a','0000000000','1980-10-26')
        assert.true(validateRegister(), "true succeeds");
    });
    QUnit.test('All valid information', function(assert){
        setVarsFormValidation('passwords','passwords','0000000000','1980-10-26')
        assert.true(validateRegister(), "true succeeds");
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
        assert.equal(getAge('2005-01-01'),"15", "testing getAge function(15)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('2020-01-01'),"0", "testing getAge function(0)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('2019-12-01'),"0", "testing getAge function(0)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('2019-10-27'),"1", "testing getAge function(1)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('1969-01-01'),"51", "testing getAge function(51)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('2010-01-01'),"10", "testing getAge function(10)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('1998-06-15'),"22", "testing getAge function(22)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('-271821-05-01'),"273841", "testing getAge function(273841)");
    });
    QUnit.test('All valid information', function(assert){
        assert.equal(getAge('2026-05-01'),"-5", "testing getAge function(-5)");
    });

</script>


</body>
</html>