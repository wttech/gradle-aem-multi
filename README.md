![Cognifide logo](docs/cognifide-logo.png)

[![Gradle Status](https://gradleupdate.appspot.com/Cognifide/gradle-aem-multi/status.svg?random=456)](https://gradleupdate.appspot.com/Cognifide/gradle-aem-multi/status)
[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/Cognifide/gradle-aem-multi.svg?label=License)](http://www.apache.org/licenses/)

# AEM Multi-Project Example

<br>
<p align="center">
  <img src="docs/logo.png" alt="Gradle AEM Plugin Logo"/>
</p>
<br>

## Screenshot

<p align="center">
  <img src="docs/gradle-aem-multi-build.gif" alt="Gradle AEM Multi Build"/>
</p>

## Description

This project could be used to start developing **long-term project** based on AEM.

To start developing **application/library** based on AEM it is recommended to use [Gradle AEM Single](https://github.com/Cognifide/gradle-aem-single) instead.

Documentation for AEM plugin is available in project [Gradle AEM Plugin](https://github.com/Cognifide/gradle-aem-plugin).

## Table of Contents

* [Features](#features)
* [Quickstart](#quickstart)
* [Environment](#environment)
* [Structure](#structure)
* [Building](#building)
* [Tips &amp; tricks](#tips--tricks)
* [Running tests](#running-tests)
* [Attaching debugger](#attaching-debugger)
* [Extending build](#extending-build)

## Features

Main motivation of this project is to automate all aspects of AEM development and make it a breeze.

- - -

Archetyping:

* [Generating project from archetype](gradle/fork.gradle.kts) using [Gradle Fork Plugin](https://github.com/neva-dev/gradle-fork-plugin),
* [Generating user-specific build properties](gradle/fork/gradle.properties.peb) using [friendly GUI](gradle/fork.gradle.kts)

Environment:

* [Automatic native local AEM instance(s) setup](https://github.com/Cognifide/gradle-aem-plugin#instance-plugin),
* [Automatic HTTPD server with AEM dispatcher setup](https://github.com/Cognifide/gradle-aem-plugin#environment-plugin) (based on [Docker](https://www.docker.com)),
* [Hosts file amendment](https://github.com/Cognifide/gradle-aem-plugin#task-environmenthosts) - [`aem.environment.hosts`](aem/build.gradle.kts),
* [Health checking](https://github.com/Cognifide/gradle-aem-plugin#task-environmentawait) - [`aem.environment.healthChecks`](aem/build.gradle.kts)

Back-end:

* Interoperable [Java](aem/sites/src/main/java) and [Kotlin](aem/sites/src/main/kotlin) code examples.
* Examples for:
    * [Embedding JAR file into OSGI bundle](https://github.com/Cognifide/gradle-aem-plugin#embedding-jar-file-into-osgi-bundle) - [`bundlePrivateEmbed`](aem/common/build.gradle.kts)
    * [Including additional OSGi bundle into CRX package](https://github.com/Cognifide/gradle-aem-plugin#including-additional-osgi-bundle-into-crx-package) - [`packageCompose.fromJar`](aem/common/build.gradle.kts)

Front-end:

* [Setup of popular UI build toolkit](aem/sites/package.json): [NodeJS](https://nodejs.org/en/), [Yarn](https://yarnpkg.com) and [Webpack](https://webpack.github.io/) for advanced assets bundling (modular JS, ECMAScript6 transpilation, SCSS compilation with [PostCSS](http://postcss.org), code style checks etc).
* [Integrated SCSS compilation on AEM side](aem/common/build.gradle.kts) using [AEM Sass Compiler](https://github.com/mickleroy/aem-sass-compiler).

Testing:

* [Unit tests](aem/sites/src/test) using popular AEM testing toolkit - [wcm.io Testing](http://wcm.io/testing).
* [Integration tests](test/integration) using [Karate Framework](https://github.com/intuit/karate) and [JSoup](https://jsoup.org/).
* [Functional tests](test/functional) using [Cypress](http://cypress.io)
* [Performance tests](test/performance) using [Gradle Lighthouse Plugin](https://github.com/Cognifide/gradle-lighthouse-plugin)

Maintenance:

* [Automatic AEM migration scripts execution](aem/migration/build.gradle.kts) using [AEM Easy Content Upgrade](https://github.com/valtech/aem-easy-content-upgrade),
* [Automatic AEM access control configuration applying](aem/sites/build.gradle.kts) using [Access Control Tool](https://github.com/Netcentric/accesscontroltool),
* [Interactive incident monitoring / logs monitoring](https://github.com/Cognifide/gradle-aem-plugin#task-instancetail) with [filtering](aem/gradle/instanceTail/incidentFilter.txt).

## Quickstart

1. Fork project using command:

    ```bash
    git clone https://github.com/Cognifide/gradle-aem-multi.git && cd gradle-aem-multi && sh gradlew fork
    ```

    and specify properties:

    ![Fork Props Dialog](docs/fork-default-dialog.png)
    
    and wait until project is forked then enter configured target directory.

2. Setup user specific project configuration using command:

    ```bash
    sh gradlew props
    ```
    
    and specify properties:

    ![Fork Props Dialog](docs/fork-props-dialog.png)

3. Setup local AEM instances with dependencies and AEM dispatcher (see [prerequisites](https://github.com/Cognifide/gradle-aem-plugin/tree/develop#environment-configuration)) then build application using command:

    ```bash
    sh aem/hosts
    sh gradlew setup
    ```
    
    and wait till complete AEM environment will be ready to use.
  
4. Develop continuously application using command:

    ```bash
    sh gradlew
    ```
   
    which is an alias for:
    
    ```bash
    sh gradlew develop
    ```
    
    or to just deploy AEM application (without running anything else):
    
    ```bash
    sh gradlew :aem:assembly:full:packageDeploy
    ```

## Prerequisites

Tested on:

* Java 1.8
* Gradle 5.4.1
* Adobe AEM 6.5
* Docker 2.0.0.3

## Structure

Project is divided into subpackages (designed with reinstallabilty on production environments in mind):

* *aem/assembly/full* - non-reinstallable complete all-in-one package with application and contents (combination of subpackages: all). Useful to deploy all code by installing single package in a project stage when application is not live.
* *aem/assembly/app* - reinstallable assembly package that contains only application code, not content (combination of subpackages: *common*, *sites*). Useful to deploy application code only in a project stage when application is live and content should remain untouched on production server.

* *aem/common* - OSGi bundle with integrations of libraries needed by other bundles and global AEM extensions (dialogs, form controls etc). Only code unrelated to any site / AEM platform wide.
* *aem/sites* - AEM sites module extension consisting of site specific code like: OSGi bundle with business logic, AEM components, templates, design.
* *aem/site.demo* - consists of extra AEM pages that presents features of application (useful for testing). Helps application testers and developers in QA/UAT application feature tests.
* *aem/site.live* - contains minimal set of pages needed initially to rollout new site(s) using installed application. Helps content authors to start working with application.

## Environment

Project is configured to have local environment which consists of:

* native AEM instances running on local file system, 
* virtualized Apache HTTP Server with AEM Dispatcher module running on Docker ([official httpd image](https://hub.docker.com/_/httpd)).

Assumptions:

* AEM author available at [http://localhost:4502](http://localhost:4502)
* AEM publish available at [http://localhost:4503](http://localhost:4503)
* Apache web server with Virtual hosts configured for domains:
  * http://example.com -> which maps to `/content/example/live` content root on publish
  * http://demo.example.com -> which maps to `/content/example/demo` content root on publish
  * http://author.example.com -> which is proxy to the author instance

## Building

1. Use command `gradlew` so that Gradle in version according to project will be downloaded automatically.
2. Deploy application:
    * Full assembly and run all tests
        * `sh gradlew` <=> `sh gradlew :develop`
    * Only assembly packages:
        * `sh gradlew :aem:assembly:full:packageDeploy`
        * `sh gradlew :aem:assembly:app:packageDeploy`
    * Only single package:
        * `sh gradlew :aem:sites:packageDeploy`,
        * `sh gradlew :aem:common:packageDeploy`,
        * `sh gradlew :aem:site.demo:packageDeploy`.
        * `sh gradlew :aem:site.live:packageDeploy`,
        
## Tooling

1. Monitoring errors in logs: `sh gradlew instanceTail`
2. Synchronizing JCR content from AEM to local file system:
    * `sh gradlew :aem:site.demo:sync`
    * `sh gradlew :aem:site.live:sync`
3. Interactively updating HTTPD Virtual-Host & AEM Dispatcher configuration: `sh gradlew environmentDev`
4. Copying JCR content between AEM instances: `sh gradlew :aem:sites:demo:rcp -Prcp.source=http://user:pass@x.x.x.x:4502 -Prcp.target=local-author -Prcp.paths=[/content/example,/content/dam/example]`

## Tips & tricks

* To run some task only for subproject, use project path as a prefix, for instance: `sh gradlew :aem:sites:packageDeploy`.
* According to [recommendations](https://docs.gradle.org/current/userguide/gradle_daemon.html), Gradle daemon should be: 
    * enabled on development environments,
    * disabled on continuous integration environments.
* To see more descriptive errors or want to skip some tasks, see command line [documentation](https://docs.gradle.org/current/userguide/command_line_interface.html).

## Running tests 

### IntelliJ

Certain unit tests may depend on the results of running gradle tasks. One such example is the testing of OSGi Services using [OSGi Mocks](https://sling.apache.org/documentation/development/osgi-mock.html) where in order to run a test, the SCR metadata must be available for a class. Running a test like this in IntelliJ results in errors because the IDE is not aware of the Bundle plugin.

This can be worked around by configuring IntelliJ to delegate test execution to Gradle. In order to set this up, go to _Settings > Build, Execution, Deployment > Gradle > Runner_ and set your IDE to delegate IDE build/run actions to Gradle. Alternatively, you can use a dropdown menu to use a specific runner or to decide on a test-by-test basis.

## Attaching debugger

1. Execute build with options `-Dorg.gradle.debug=true --no-daemon`, it will suspend,
2. Attach debugger on port 5005,
3. Suspension will be released and build should stop at breakpoint.

## Extending build

For defining new tasks directly in build see:

 * [Build Script Basics](https://docs.gradle.org/current/userguide/tutorial_using_tasks.html)
 * [More about Tasks](https://docs.gradle.org/current/userguide/more_about_tasks.html)

The easiest way to implement custom plugins and use them in project is a technique related with _buildSrc/_ directory.
For more details please read [documentation](https://docs.gradle.org/current/userguide/organizing_build_logic.html#sec:build_sources).
