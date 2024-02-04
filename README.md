# HuntJob

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java CI](https://github.com/hippy0/HuntJob/actions/workflows/java-ci.yml/badge.svg)](https://github.com/hippy0/HuntJob/actions/workflows/java-ci.yml)

HuntJob is a application that helps keep a record of all the companies whose jobs you have responded to and track the status of respond.

# Codebase

The codebase is Spring Framework and h2 memory database. Quite a simple setup.

# Setup

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

# Api Documentation

You can find api documentation that generates automatically when application runs at:

`http://{your-address}:{your-port}/swagger-ui/index.html` **for example:** `http://localhost:7070/swagger-ui/index.html`

# License

HuntJob is distributed under an Apache 2.0 license.
