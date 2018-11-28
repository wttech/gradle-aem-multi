const PuppeteerEnvironment = require('../config/common/puppeteer-common-environment.js');

class Env extends PuppeteerEnvironment {
    constructor(config) {
        super(config);
    }

    async setup() {
        await super.setup();
        this.global.httpUrl = '{{instance-url}}';
    }
}

module.exports = Env;
