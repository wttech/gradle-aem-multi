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
Currently Gradle does not support Maven's like archetypes, so you have to copy this project at start and customize it for your needs.
Documentation for AEM plugin is available in project [Gradle AEM Plugin](https://github.com/Cognifide/gradle-aem-plugin).


## Environment

Tested on:

* Java 1.8
* Gradle 4.0.1
* Adobe AEM 6.2

## Build

1. Install Gradle
    * Use bundled wrapper (always use command `sh gradlew` instead of `gradle`). It will be downloaded automatically.
    * Use standalone from [here](https://docs.gradle.org/current/userguide/installation.html).
2. Run `gradle idea` or `gradle eclipse` to generate configuration for your favourite IDE.
3. Build application and deploy:
    * Assembly packages:
        * `gradle aemRootDeploy` or just `gradle`,
        * `gradle aemAppDeploy`,
        * `gradle aemContentDeploy`.
    * Single package:
        * `gradle aemAppCoreDeploy`,
        * `gradle aemAppCommonDeploy`,
        * `gradle aemAppConfigDeploy`,
        * `gradle aemAppDesignDeploy`,
        * `gradle aemContentInitDeploy`,
        * `gradle aemContentDemoDeploy`.

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
