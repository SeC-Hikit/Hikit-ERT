# Hikit - ERT microservice
Hikit ERT is a microservice responsible to retrieving data from ER Tourism API (https://apt-servizi.github.io/openapi-ert/).
That will serve to integrate ERT data with Hikit service.

## Build
### Prerequisites
- JDK 11
- Maven 3

### Development build
First, clone and build commons: https://github.com/SeC-Hikit/Hikit-Common.
Then, after cloning this repo, simply run `mvn install -f root/pom.xml`.

### Deployment Build
To include all dependencies in a portable uber-jar, run `mvn install -P package`.

### Test
There are two main types of tests written to address the Hikit QA: unit test and integration test.
The latter requires the [dependencies](#Dependencies) to be up and running as it tests application operations connecting to them.
Once the dependencies are made available, to run the integration tests, simply execute `mvn install -P it`.

## Run

### Dependencies
Hikit ERT requires the following service up and running in order to fully operate:
- [MongoDB 4.x](https://www.mongodb.com)

To get these up and running in the least time possible, use the included `docker-compose.yml`:
```
cd docker
sh rset_download.sh
docker-compose up
```
Mind that the above set-up is for *test/dev only* as all services are exposed in the network and they do not
use authentication.

### Hikit on Docker
In case you want to test run Hikit in combination with all its dependencies:
- Run a deployment build (see [Deployment build](#Deployment-build))
- Run `sh docker/build.sh`
- Run `docker-compose -f ./docker/docker-compose.yml -f ./docker/docker-compose.cluster.yml up`
  The service will be available at `http://localhost:8990/api/v<N>` (where `<N>` stands for the version number)

### Production
Since it does not support authentication, Hikit ERT should run in a DMZ, not reachable from the outer word.

### Properties
Configure the `application.yml` file to fit the application runtime to your needs.
For more on the system properties, check out the repo wiki pages.

## The API

### OpenAPI
By default, the openAPI v3 specs are exposed at `api/v1/api-docs`.
Alternatively, to interact directly with the REST API, the Swagger UI can be found at `api/v1/api-ui.html`.
