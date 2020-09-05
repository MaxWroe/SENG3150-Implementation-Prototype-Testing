
INSERT INTO `OtherTransport` (`Type`, `Description`, `Destination`)
VALUES (''Bus'',''There a busses available to and from the airport'', ''LAX''),
		(''Train'',''There a trains available to and from the airport'', ''LAX''),
        (''Uber'',''Uber is avaialble within all parts of the city'', ''LAX'');

INSERT INTO `HolidayPackages` (`Description`, `Destination`, `countryCode3`, `countryName`, `type`)
VALUES 	(''This is dummy data 1'', ''LAX'', ''USA'', ''United States'', ''0''),
		(''This is dummy data 2'', ''SYD'', ''AUS'',''Australia'', ''0''),
		(''This is dummy data 3'', ''LHR'', ''GBR'', ''United Kingdom'', ''0''),
        (''This is dummy data 4'', ''MEL'', ''AUS'',''Australia'', ''0''),
        (''This is dummy data 5'', ''GIG'', ''BRA'',''Rio De Janeiro'', ''0'');

INSERT INTO `WishListEntry` (`UserID`, `countryCode3`, `countryName`)
VALUES  (''1'', ''USA'',  ''United States''),
		(''1'', ''AUS'',  ''Australia''),
        (''2'', ''USA'',  ''United States''),
        (''2'', ''GBR'',  ''United Kingdom'');

INSERT INTO `UserAccount`(`UserID`,`FirstName`,`MiddleNames`,`LastName`,`Email`,`Phone`, `Gender`, `Citizenship`, `UserType`,`DateOfBirth` ,`Password`, `ROLEID`, `closestAirport`)
VALUES
	(''3'', ''reece'', ''James'', ''Doe'', ''reece@gmail.com'', ''0487876543'', ''0'', ''Australian'', ''0'', ''1990-03-31'', ''reece'', ''CUSTOMER'', ''GIG'');

		