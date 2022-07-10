# nuna_automation

# Tools used for Framework 

1. Selenium(4.3.0) + Java () 
2. TestNg Annotations - to implement the automated tests and for report generation
3. Build tool - Maven 
4. Apache POI Library - to read data from excel
5. Page Object Model 

# Key Highlights in framework (which can further be enhanced)

1. the resuable webdriver methods are placed in a separate file to ensure reusability 
2. the data is read from Excel file using Apache POI library 
3. the functionality to capture screenshot in case of test failure 
4. the methods related to scenario are placed in a separate file and invoked inside @test method to ensure readability
5. Page Object Model to store the locators page-wise and at a central location .
6. Effort has been made to make the the automation robust and efficient. 

## steps to execute the tests 

run the below command from the directory contaning pom.xml

mvn clean install


Note: 1. For your reference I have Also committed the target folder which contains 
sure-fire report with the name "emailable-report.html"  to view the test results 




2.  the screenshots folder contains the .png to show the capture screenshot usecase. So for this the testcase was deliveratley failed to verify capture screenshot 


*************************************************************
PFA screenshot for the use cases mentioned in the email that have been automated  
