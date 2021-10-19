# BDD_Identity_Automation
CarDetailsCheck BDD Automation

## Background
User wants to automate the car identity check with the following requirements:
1. provides an input text file to which contains the Car/Vehicle registration numbers to search.
2. provides a comma separated output file with the car/vehicle details provided for each registration
3. search the car details on https://cartaxcheck.co.uk and get the car details
4. compare the car details from output file with the actual website and highlight the MISMATCHES
5. this process should be repeated based on the multiple input/output files

#Framework:
Above feature is automated using Cucumber-BDD and PageObject framework

#TechStack:
Selenium Webdriver, TestNG, Maven, Cucumber and Java

#Pre-req:
1. Download chromedriver and firefox driver.
2. Specify the driver path and browser choice in configuration.properties file

#Usage:
Some possible ways to test the framework. 
User should have basic installation and configuration knowledge of using the mentioned tech stack
1. Using TestNG
 mvn clean test -DsuiteXmlFile=testng.xm

2. Maven:
mvn clean test

See the reports in target\surefire-reports

#TODO:
There are a lot of improvements and code optimizations that needs to be done.
This framework only touch base the framework to automate the feature 
#Other improvements
1. Better documentation
2. Reporting
3. Exception handling
4. Driver waits
5. Additional browsers
6. Parallel execution and multiple runners
7. Code optimization
8. Logging
9. and more...

