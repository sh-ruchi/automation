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
7. Cuurently the framework is automated to use chrome browser which can be further extended 

## steps to execute the tests 

run the below command from the directory contaning pom.xml

mvn clean install


Note: 1. For your reference I have Also committed the target folder which contains 
sure-fire report with the name "emailable-report.html"  to view the test results 
location : nuna_automation/target/surefire-reports/

![image](https://user-images.githubusercontent.com/39658431/178170240-ec9d083f-b1e9-4965-9d2e-15b4268a505f.png)

2.  the screenshots folder contains the .png to show the capture screenshot usecase. So for this the testcase was deliveratley failed to verify capture screenshot 
location : nuna_automation/screenshots
![image](https://user-images.githubusercontent.com/39658431/178170300-72f76815-405e-44e9-89b8-029c38b9e295.png)

*************************************************************
PFA screenshot for the use cases mentioned in the email that have been automated  

![image](https://user-images.githubusercontent.com/39658431/178170562-ae890c52-e70e-4da6-90af-b8d9e0ff7641.png)
