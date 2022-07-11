# nuna_automation

# Test Environment 
1. Chrome browser Version 103.0.5060.66 (Official Build) (64-bit)
# Tools used for Framework 

1. Selenium(4.3.0) + Java () 
2. TestNg Annotations - to implement the automated tests, to add Assertions  and for report generation
3. Build tool - Maven 
4. Apache POI Library - to read data from excel
5. Page Object Model 

# Key Highlights in framework (which can further be enhanced)

1. Resuable webdriver methods are placed in a separate file to ensure reusability 
2. The data is read from Excel file using Apache POI library 
3. The functionality to capture screenshot in case of test failure 
4. The methods related to scenario are placed in a separate file and invoked inside @test method to ensure readability
5. Page Object Model to store the locators page-wise and at a central location .
6. Effort has been made to make the the automation robust and efficient. 
7. Currently the framework executes the tests on chrome browser which can be further extended 
to execute on other browsers as well based on the business needs .
8. Assertions are added for verification of the expected results .


## steps to execute the tests 

i. open commandline
ii. run the below command from commandline  the directory containing pom.xml, which will build and 
execute the tests.

mvn clean test


Note: 1. For your reference I have Also committed the target folder which contains 
sure-fire report with the name "emailable-report.html"  to view the test results 


2.  the screenshots folder contains the .png to show the capture screenshot usecase. So for this the testcase was deliveratley failed to verify capture screenshot 

*************************************************************
Steps & scenarios to be automated as per the email 
1. Based on Page Object Model, create automated test for the scenario: Using Google Translate application (https://translate.google.com/)  

▪ select source language from the drop-down menu on the left 

▪ select translation language from the drop-down menu on the right 

▪ enter the initial text in the input field on the left 

▪ make sure that the actual translation result in the right field is correct 

Source, Translation languages, initial text and expected result should be taken from a separate  data file (.json, .yaml, or .xlsx), for example (but not necessarily the exact word!): 

▪ source language: German 

▪ translation language: Spanish  

▪ initial text: "Demokratien"  

▪ expected result: "Democracias" 

2. Add scenario: click swap languages button and verify the result. 

3. Add scenario: clear the input field, click "select input tool" button, select "screen keyboard" and  enter "Hi!" 
