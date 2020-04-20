const merger = require('mochawesome-merge');
const generator = require('mochawesome-report-generator');
const cypressOptions = require('../cypress');
const reportDir = cypressOptions.reporterOptions.reportDir;

async function generateReport() {
    const jsonReport = await merger.merge({ files: [`./${reportDir}/mochawesome*.json`] });
    await generator.create(jsonReport, { reportDir: reportDir});
}

generateReport().then(() => console.log(`Cypress Mochawesome report available at path: ${process.cwd()}/${reportDir}/mochawesome.html`));
