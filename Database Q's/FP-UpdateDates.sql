USE flightpub;

SET SQL_SAFE_UPDATES = 0;

UPDATE Availability
SET DepartureTime=DATE_ADD(DepartureTime, INTERVAL 5 YEAR)
WHERE TRUE;

UPDATE Price
SET StartDate=DATE_ADD(StartDate, INTERVAL 5 YEAR),
	EndDate=DATE_ADD(EndDate, INTERVAL 5 YEAR)
WHERE TRUE;

UPDATE Flights
SET DepartureTime=DATE_ADD(DepartureTime, INTERVAL 5 YEAR),
	ArrivalTime=DATE_ADD(ArrivalTime, INTERVAL 5 YEAR)
WHERE TRUE;

UPDATE Flights
SET ArrivalTimeStopOver=DATE_ADD(ArrivalTimeStopOver, INTERVAL 5 YEAR),
	DepartureTimeStopOver=DATE_ADD(DepartureTimeStopOver, INTERVAL 5 YEAR)
WHERE ArrivalTimeStopOver IS NOT NULL;

UPDATE Price
SET
	EndDate=DATE_ADD(EndDate, INTERVAL 23 HOUR),
    EndDate=DATE_ADD(EndDate, INTERVAL 59 MINUTE),
    EndDate=DATE_ADD(EndDate, INTERVAL 59 SECOND)
WHERE TRUE;

SET SQL_SAFE_UPDATES = 1;



SELECT *
FROM Availability
LIMIT 10;

SELECT *
FROM Price
LIMIT 10;

SELECT *
FROM Flights
WHERE ArrivalTimeStopOver IS NOT NULL
LIMIT 10;

SELECT *
FROM Flights
WHERE ArrivalTimeStopOver IS NULL
LIMIT 10;