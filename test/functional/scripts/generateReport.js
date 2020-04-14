const merger = require('mochawesome-merge');
const generator = require('mochawesome-report-generator');
const cypressOptions = require('../cypress');

async function generateReport() {
    const reportDir = cypressOptions.reporterOptions.reportDir;
    const jsonReport = await merger.merge({ files: [`./${reportDir}/mochawesome*.json`] });
    await generator.create(jsonReport, { reportDir: reportDir});
}

generateReport();
