# Hikit - ERT microservice
Hikit ERT is a microservice responsible for retrieving data from ER Tourism API (https://apt-servizi.github.io/openapi-ert/), that will serve to integrate ERT data with Hikit service.

## Build
### Prerequisites
- JDK 11
- Maven 3

### Development build
First, clone and build commons: https://github.com/SeC-Hikit/Hikit-Common.
Then, after cloning this repo, simply run `mvn install -f root/pom.xml`.

### Deployment Build
To include all dependencies in a portable uber-jar, run `mvn install -f root/pom.xml -P package`.

### Test
There are two main types of tests written to address the Hikit QA: unit test and integration test.
The latter requires the [dependencies](#Dependencies) to be up and running as it tests application operations connecting to them.
Once the dependencies are made available, to run the integration tests, simply execute `mvn install -P it`.

## Run

### Dependencies
Hikit ERT requires a [MongoDB 4.x](https://www.mongodb.com) instance in order to store ERT data

### Production
Since it does not support authentication, Hikit ERT must run in a DMZ.

### Properties
Configure the `application.yml` file to fit the application runtime to your needs.
For more on the system properties, check out the repo wiki pages.

## The API

### OpenAPI
By default, the openAPI v3 specs are exposed at `api/v1/api-docs`.
Alternatively, to interact directly with the REST API, the Swagger UI can be found at `api/v1/api-ui.html`.
