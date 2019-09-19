# Performance tests

## About

Tests are using [lighthouse-batch](https://www.npmjs.com/package/lighthouse-batch) tool which supports executing Lighthouse tests for multiple sites. 

## Configuration

Add all paths you want to test to `sites/default.conf` file, one per line.
If paths are specific to site create dedicated file `sites/${host}.conf`.

## Running

Simply run command: `sh gradlew :test:performance:run`. 
Optionally configure with `-Ptest.baseUrl=http://aem-host.com` to run tests on other host (using default paths).

Test results will be saved in `build/reports` directory. 
They include one HTML file (standard Lighthouse report) and one JSON file for each path and a single `summary.json` file aggregating stats for all paths.
