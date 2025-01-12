# README.md

# Java Maven App

This is a basic Java application built using Maven.

## Project Structure

```
java-maven-app
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── example
│   │               └── App.java
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── AppTest.java
├── pom.xml
└── README.md
```

## Requirements

- OpenJDK 21
- Maven 3.9.9

## Build Instructions

1. Navigate to the project directory:
   ```bash
   cd java-maven-app
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Running the Application

To run the application, use the following command:
```bash
java -cp target/java-maven-app-1.0-SNAPSHOT.jar com.example.App
```

## Running Tests

To run the tests, use the following command:
```bash
mvn test
```