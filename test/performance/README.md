## Performance tests

### About

Performance tests are run using [lighthouse-batch](https://www.npmjs.com/package/lighthouse-batch) tool which supports executing Lighthouse tests for multiple sites. 

### Configuration

Add all URLs you want to test to `config/sites.conf` file, one per line.

### Running

Simply run: `sh gradlew :test:performance:test`. 

Test results will be saved in `reports` directory. They include one html file (standard Lighthouse report) and one json file for each site and a single `summary.json` file aggregating stats for all sites.
