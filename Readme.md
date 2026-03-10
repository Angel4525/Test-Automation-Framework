# Test Automation Framework
This project is a Java-based Test Automation Framework designed to execute UI tests efficiently across multiple environments. 

The framework follows modern automation best practices such as Page Object Model (POM), data-driven testing, cloud execution(LamdaTest), logging, and detailed reporting.

## 🚀 About Me
Hi! I'm a Computer Science graduate from Arizona State University with a strong passion for software development and automation engineering.

I enjoy building systems that improve software reliability and efficiency. Through projects like this automation framework, I’ve gained experience designing structured test architectures, working with modern development tools, and solving real-world testing challenges.

I’m a quick learner who enjoys tackling technical problems, writing clean and maintainable code, and continuously improving my development skills. I’m excited to begin my career where I can contribute to building reliable, high-quality software and innovative technical solutions.
## Author

- [LinkedIn](https://www.linkedin.com/in/jose-aguirre-424b07189/)
- [GitHub](https://github.com/Angel4525)
- EmailAddress: ang_elfg@hotmail.com




## Tech Stack

**Programming Language:** Java


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/Angel4525)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/jose-aguirre-11713937a/)


## Prerequisites

Before running the framework ensure you have:

- Java JDK 17+ 
- Maven 
- LambdaTest account (for cloud execution)


## Features
- **Data-Driven Testing**: Using OpenCSV, Apache POI, and Gson for reading test data from CSV and Excel files and JSON.
- **Cross-Browser Testing**: Supports running tets on different browsers.
- **Headless Mode**: Faster execution by running tests in headless mode.
- **Cloud Testing**: Integrated with LambdaTest to run tests on the cloud.
- **Logging**: Uses Log4j for detailed logs.
- **Reporting**: Generates detailed reports using Extent Reports.


## Technologies Used
- Java
- TestNG
- OpenCSV
- Gson
- Apache POI
- Faker
- LambdaTest
- Log4j
- Extent Reports

## Installation

**Clone the Repository**

```bash
  git clone https://github.com/Angel4525/Test-Automation-Framework.git
  cd Test-Automation-Framework
```
**Running Tests on LambdaTest**

```bash
  mvn clean test -Dbrowser=chrome -DisLambdaTest=true -DisHeadless=false
```
**Running Tests on Chrome browser on Local Machine in Headless Mode**

```bash
  mvn clean test -Dbrowser=chrome -DisLambdaTest=false -DisHeadless=true
```

## Reports:
After execution, a detailed HTML report will be generated at ./report.html.

The report contains information on executed test cases that passed, failed and skipped, along with screenshots for failed tests.

## Logs:
Logs are created during the test execution and stored in the ./logs/directory.

##Integrated the project Github Actions
This automation framework is integrated with github actions. The tests will be executed at 11:00PM UTC every day.
