![Cognifide logo](http://cognifide.github.io/images/cognifide-logo.png)

# Gradle AEM Template

## Description

This project should be used while starting new project based on AEM.
Currently Gradle does not support Maven's like archetypes, so you have to copy this project at start and customize it for your needs.
Documentation for used plugins is available in project [Gradle AEM Plugins](https://stash.cognifide.com/projects/RND/repos/gradle-aem-plugins/browse).
Project structure is based on Adobe's [multimodule-content-package](https://docs.adobe.com/docs/en/cq/5-6-1/core/how_to/how_to_use_the_vlttool/vlt-mavenplugin.html) archetype.

## Environment

* Java 1.8
* Gradle 2.x
* Maven 3.x (optionally)
* Adobe AEM 6.1

## Build

1. Copy into your home user directory and modify required configuration files:
    * gradle/gradle.properties -> ~/.grade/gradle.properties
2. Install Gradle
    * Use bundled wrapper (always use command `gradlew` instead of `gradle`). It will be downloaded automatically.
    * Use standalone from [here](https://docs.gradle.org/current/userguide/installation.html).
3. Run `gradle idea` or `gradle eclipse` to generate configuration for your favourite IDE.
4. Build application using commands:
    * `gradle deployContent` or just `gradle`,
    * `gradle deployBundle`.

Hints:

* to skip tests or any other task by name use `-x test`
* to apply profile from command line, for example for integration, use `-Penv=integration`


## Troubleshoot

* According to [recommendations](https://docs.gradle.org/current/userguide/gradle_daemon.html), daemon should be: 
    * enabled on development environments,
    * disabled on continous integration environments,
* If build caches to much, you could try with `--rerun-tasks` option. See this [link](https://docs.gradle.org/current/userguide/gradle_command_line.html) for more details.
* Too see more descriptive details about errors, you could use `--full-stacktrace`, `--debug`, `-i` options.


## Attaching debugger

1. Append to _gradle.properties_: `org.gradle.jvmargs=-XX:MaxPermSize=4g -XX:+HeapDumpOnOutOfMemoryError -Xmx4g -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5011`,
2. Execute build command`gradle clean build`, it will suspend,
3. Attach debugger on port 5011,
4. Suspension will be released and build should stop at breakpoint.


## Extending build

The easiest way to implement custom plugins and use them in project is a technique related with _buildSrc/_ directory.
For more details please read [documentation](https://docs.gradle.org/current/userguide/organizing_build_logic.html#sec:build_sources).