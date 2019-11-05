const merger = require('mochawesome-merge');
const generator = require('mochawesome-report-generator');

async function generateReport() {
    const opts = { reportDir: 'build/cypress/reports'};
    const jsonReport = await merger.merge(opts);
    await generator.create(jsonReport, opts);
}

generateReport();
