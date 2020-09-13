
INSERT INTO `OtherTransport` (`Type`, `Description`, `Destination`)
VALUES ('Bus','There a busses available to and from the airport', 'LAX'),
		('Train','There a trains available to and from the airport', 'LAX'),
        ('Uber','Uber is avaialble within all parts of the city', 'LAX');

INSERT INTO `HolidayPackages` (`Description`, `Destination`, `countryCode3`, `countryName`, `type`)
VALUES 	('Known for its Mediterranean climate, ethnic diversity, Hollywood entertainment industry, and its sprawling metropolis.', 'LAX', 'USA', 'United States', '0'),
		('A coastal metropolis whose five million residents make it the largest city in Australia.', 'SYD', 'AUS','Australia', '0'),
		('London is famous for more than its magnificent ancient buildings, Trafalgar Square, London Eye, and Big Ben.', 'LHR', 'GBR', 'United Kingdom', '0'),
        ('The city is known for its many laneways, its cultural diversity, excellent dining options for all budgets, and amazing street art. ', 'MEL', 'AUS','Australia', '0'),
        ('Known for its natural settings, Carnival, samba, bossa nova, and balneario beaches.', 'GIG', 'BRA','Rio De Janeiro', '0');

INSERT INTO `WishListEntry` (`UserID`, `countryCode3`, `countryName`)
VALUES  ('1', 'USA',  'United States'),
		('1', 'AUS',  'Australia'),
        ('2', 'USA',  'United States'),
        ('2', 'GBR',  'United Kingdom');

INSERT INTO `UserAccount`(`UserID`,`FirstName`,`MiddleNames`,`LastName`,`Email`,`Phone`, `Gender`, `Citizenship`, `UserType`,`DateOfBirth` ,`Password`, `ROLEID`, `closestAirport`)
VALUES
	('6', 'reece', 'James', 'Doe', 'reece@gmail.com', '0487876543', '0', 'Australian', '0', '1990-03-31', 'reece', 'CUSTOMER', 'GIG');


UPDATE Airlines
SET
    Sponsored = '1'
WHERE
    AirlineCode = 'AA';

        UPDATE Airlines
SET
    Sponsored = '0'
WHERE
    AirlineCode != 'AA';