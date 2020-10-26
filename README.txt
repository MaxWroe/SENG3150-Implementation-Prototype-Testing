SENG3150-Implemenation-Prototype
Authors: Jarrad Price, Ace Cangco, Angus Simmons, Jacob Andronicos and Chris Mather

overview:
The program is set up with gradle using maven dependencies. This means that when the web application is deployed it uses the WAR file in the build/libs folder.

Tomcat:
The application runs using the tomcat servlet, this will need to be established for the runtime in the configurations of the IDE

Database:
The application will also need to connect to a mysql database, it is currently configure to use mariadb if it is running locally and cleardb for a heroku deployment. 
For running on with mariadb the server details will need to be updated in the persistence.xml file (located at webapp/classes/META-INF/). 
This will only be needed for running on a local host.

SQL:
4 SQL queires have been provided in the root directy within the Database Q's folder. They should be run in order of:

	1.FP-Schema
	2.FP-Data
	3.FP-DummyData
	4.FP-UpdateDates
Users can be found in data and DummyData

IDE:
the system has been developemnt with intellij and as such this would be the easiest IDE to intergrate the system with.

heroku: 
	The system has been deployed to heroku at https://group3-seng3150-v1.herokuapp.com/.
	NOTE: There is a JDBC connection error that may occur when using the system, this is due some issues using clearDB and reattempting the desired action should resolve the issue.

	clearDB:
	Since this is a free database service, there is a limit to the amount of data allowed. the data that has been added is for proof of concept

	Search:
	2021-01-01 to 2021-01-06 are the only dates that flights will be avaiable (this is only for the online deployement)

	SQL:
	Heroku uses different scripts for deployment, located in the heroku SQL folder. They should be run in order of:
		1.Heroku-Schema
		2.Heroku-Data
		3.Heroku-DummyData
		4.Heroku-UpdateDates
