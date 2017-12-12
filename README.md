![Cognifide logo](docs/cognifide-logo.png)

[![Gradle Status](https://gradleupdate.appspot.com/Cognifide/gradle-aem-example/status.svg)](https://gradleupdate.appspot.com/Cognifide/gradle-aem-example/status)
[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/Cognifide/gradle-aem-example.svg?label=License)](http://www.apache.org/licenses/)

# Gradle AEM Example

<br>
<p align="center">
  <img src="docs/logo.png" alt="Gradle AEM Plugin Logo"/>
</p>
<br>


## Description

This project should be used while starting new project based on AEM.
Currently Gradle does not support Maven's like archetypes, so you have to copy this project at start and customize it for your needs. Don't worry! Comparing to Maven, much less to do - only few lines to change.
Documentation for AEM plugin is available in project [Gradle AEM Plugin](https://github.com/Cognifide/gradle-aem-plugin).

## Environment

Tested on:

* Java 1.8
* Gradle 4.4
* Adobe AEM 6.3

## Structure

Project is divided into subpackages (designed with reinstallabilty on production environments in mind):

* *root* - non-reinstallable complete all-in-one package with application and contents.
* *app* - reinstallable assembly package that contains all sub-parts of application:
    * *common* - OSGi bundle with integrations of libraries needed by other bundles and AEM extensions (dialogs, form controls etc).
    * *core* - OSGi bundle with core business logic and AEM components implementation.
    * *config* - OSGi services configuration.
    * *design* - AEM design configuration responsible for look & feel of AEM pages.
* *content* - non-reinstallable assembly package that contains all type of contents listed below:
    * *init* - contains all JCR content needed initially to rollout new site(s) using installed application.
    * *demo* - consists of extra AEM pages that presents features of application (useful for testing).


## Features

* Interoperable Java and [Kotlin](https://kotlinlang.org) code examples.
* Integrated popular UI build toolkit: [NodeJS](https://nodejs.org/en/), [Yarn](https://yarnpkg.com) and [Webpack](https://webpack.github.io/) for advanced assets bundling (modular JS, ECMAScript6 transpilation, SCSS compilation with [PostCSS](http://postcss.org), code style checks etc).
* Integrated SCSS compilation on AEM side using [AEM Sass Compiler](https://github.com/mickleroy/aem-sass-compiler).
* Example configuration for embedding OSGi bundles into CRX package (`aemInstall`, `aemEmbed`).
* Example configuration for installing dependant CRX packages on AEM before application deployment (`aemSatisfy`).


## Build

1. Install Gradle
    * Use bundled wrapper (always use command `sh gradlew` instead of `gradle`). It will be downloaded automatically.
    * Use standalone from [here](https://docs.gradle.org/current/userguide/installation.html).
2. Run `gradle idea` or `gradle eclipse` to generate configuration for your favourite IDE.
3. Build application and deploy:
    * Assembly packages:
        * `gradle` <=> `:aemSatisfy :aemBuild`, `:aemAwait`,
        * `gradle :app:aemBuild` <=> `aemAppBuild`,
        * `gradle :content:aemBuild` <=> `aemContentBuild`.
    * Single package:
        * `gradle :app:core:aemBuild` <=> `aemAppCoreBuild`,
        * `gradle :app:common:aemBuild` <=> `aemAppCommonBuild`,
        * `gradle :app:config:aemBuild` <=> `aemAppConfigBuild`,
        * `gradle :app:design:aemBuild` <=> `aemAppDesignBuild`,
        * `gradle :content:init:aemBuild` <=> `aemContentInitBuild`,
        * `gradle :content:demo:aemBuild` <=> `aemContentDemoBuild`.

## Tips & tricks

* To speed up build, use [build cache](https://docs.gradle.org/current/userguide/build_cache.html) by appending to command `--build-cache` option.
* To run some task only for subproject, use project path as a prefix, for instance: `sh gradlew :app:design:aemSync`.
* Declare bundle dependencies available on AEM (like Maven's provided scope) in root project *build.gradle* in section `plugins.withId 'org.dm.bundle'` to avoid defining them separately for each subproject.
* According to [recommendations](https://docs.gradle.org/current/userguide/gradle_daemon.html), Gradle daemon should be: 
    * enabled on development environments,
    * disabled on continuous integration environments.
* If build caches to much, you could try with `--rerun-tasks` option. See this [link](https://docs.gradle.org/current/userguide/gradle_command_line.html) for more details.
* To see more descriptive details about errors, you could use `-i`, `--stacktrace`, `--debug` options.
* To skip tests or any other task by name use `-x test`


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
