# Calcs (Calculations API)

This API was created to calculate information provided on the last minute.

It currently holds only one endpoint that will return an array with the following:

sum
avg
max
min
count
 
 You can check it through swagger running it at localhost:8081 (8081 by default)

## Getting Started

You need to have java 1.8 to run this project
To compile the project you need maven 3.2+ 
All repositories used here are public and should not have any restriction.

### Prerequisites

Java 1.8
Maven 3.2+

```
You can compile it using mvn clean install or mvn clean install -DskipTests (to avoid running the tests)
After that you can execute: java -jar $PROJECT_PATH/target/demo-VERSION.jar
```


## Running the tests

The tests will run when compiling the project.
You can isolate run the tests as well if you prefer.
Integration tests and unit tests are present

### Break down into end to end tests

It currently have 2 unit tests and one integration test

```
StatsControllerTest and StatisticCalculatorServiceTest are unit test, both test and assert the results of both controller and service
TransacFeignClientIT: Stubs the response from the transaction service API that will provide me information, running through the real context of the application.
```

## Deployment

You can run the jar directly or deploy it on an application server.

## Parameters

If you prefer you can override the following parameters:

server.port  -- value default:8081 -- It is the port where the API will be running
transactions.api.url -- value default:http://localhost:8080/ -- It is the url where the transaction API will be running


## Authors

Juan Hernandez

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


 
