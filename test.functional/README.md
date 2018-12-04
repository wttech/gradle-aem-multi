## Functional tests

### Running the tests

Download dependencies, set up configuration and run test just run:

`./gradlew test-functional -Paem.env=local-author`

parameters:

* `env` - name of instance from `gradle.properties`

Run test from node.js:

`npm test -- --config=/env/local-author.config.js`

## Tasks

* `test-functional` - run tests

* `test-set-up` - create configuration files from aem instances

* `test-lint` - js syntax check 

* `test-install-node` - install node dependencies  

### Stack
[Jest](https://jestjs.io/) and [Puppeteer](https://pptr.dev/)

