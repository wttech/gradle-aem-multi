## Functional tests

### Running the tests

Download dependencies, set up configuration and run test just run:

`./gradlew testFunctional -Paem.env=local-author`

parameters:

* `aem.env` - name of instance from `gradle.properties`

Run test from node.js:

`npm test -- --config=/env/local-author.config.js`

### Tasks

* `testFunctional` - run tests

* `testSetup` - create configuration files from aem instances

* `testLint` - js syntax check 

* `testInstallNode` - install node dependencies  

### Stack
[Jest](https://jestjs.io/) and [Puppeteer](https://pptr.dev/)

