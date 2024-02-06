# HuntJob

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java CI](https://github.com/hippy0/HuntJob/actions/workflows/java-ci.yml/badge.svg)](https://github.com/hippy0/HuntJob/actions/workflows/java-ci.yml)

HuntJob is an application that helps keep a record of all the companies whose jobs you have responded to and track the status of respond.

## Codebase

The codebase is Spring Framework and h2 memory database. Quite a simple setup.

## Setup

You'll need:
  <ul>
    <li>Java (version 21)</li>
    <li>Make utility</li>
  </ul>


For convenience, the project uses the make utility.
Open up the project folder in your terminal and run the commands mentioned below.


```
make build
make run
```

You also can use docker for run application isolated, if you are - you should bind port with `-p` option.
**For example:** `docker run -p 7070:7070`

More info: https://docs.docker.com/engine/reference/commandline/container_run/#publish.
## Properties

You can customize the application as you wish using the application.yml file.

```
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: "jdbc:h2:mem:huntjob"
``` 

Spring uses an opinionated algorithm to scan for and configure a DataSource. Initially, the project uses a h2 database, the data in which is stored in memory and deleted after the application is stopped. You can use any database using this property. To do this, you need to add a runtime dependency with the driver.

```
server:
  address: 0.0.0.0
  port: 7070
```

The project initially runs on localhost with port 7070. You can configure this property as you wish.

## Api Documentation

You can find api documentation that generates automatically when application runs at:

`http://{your-address}:{your-port}/swagger-ui/index.html` **for example:** `http://localhost:7070/swagger-ui/index.html`

## License

HuntJob is distributed under an Apache 2.0 license.
