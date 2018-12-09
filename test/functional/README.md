## Functional tests

### Running

Download dependencies, set up configuration and run test:

`./gradlew testFunctional -Paem.instance=local-publish`

Parameters:

* `-Paem.instance` - name of instance from `gradle.properties`

Run test from node.js:

`npm test -- --config=/env/local-author.config.js`

### Tasks

* `runJestPuppeteer` - run tests
* `runLint` - js syntax check
 
### Stack

[Jest](https://jestjs.io/) and [Puppeteer](https://pptr.dev/)

