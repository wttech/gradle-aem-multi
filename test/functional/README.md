## Functional tests

### Running

Simply run command: `sh gradlew :test:functional:runJestPuppeteer`. 

Available parameters:

|Parameter|Description|Default value|Sample value|
|---|---|---|---|
|`aem.instance.name`|Name of instance from *gradle.properties*|`local-publish`|`int-publish`|

To check style of tests source code, run command: `sh gradlew :test:functional:runLint`.
### Stack

[Jest](https://jestjs.io/) and [Puppeteer](https://pptr.dev/)
