const path = require('path');

module.exports = args => ({
    globalSetup: './config/setup.js',
    globalTeardown: './config/teardown.js',
    testEnvironment: `${args.env}`,
    rootDir: path.join(__dirname, '../../'),
    testMatch: ['**/tests/**/*.js'],
});
