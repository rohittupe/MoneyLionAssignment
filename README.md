Money Lion Assignment - Test Automation
========

Create a runnable automation for  Web and API test automation

## Usage

The Web and API test can be executed in 2 ways - 
1. Executing using TestRunner.java
2. Execution using Maven command "mvn clean test"

## Prerequsites
1. Below softwares/tools need to be installed:
* Java 
* Eclipse
* Apache Maven

2. Import -> Maven -> Existing Maven Projects.

## Preparing for Execution

Follow below steps:

1. Ensure Selenium Chrome and Chrome Driver verison >= 78.0 is install
2. Clone repository: `git clone https://github.com/rohittupe/MoneyLionAssignment.git`.
3. `cd` into the repository.
4. Run test `mvn clean test`
5. Upon test run complete. A detailed report will be generated in `/target/cucumber/index.html`
6. Open `index.html` file with a browser to view report
