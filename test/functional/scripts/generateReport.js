const merger = require('mochawesome-merge');
const generator = require('mochawesome-report-generator');

async function generateReport() {
    const jsonReport = await merger.merge();
    await generator.create(jsonReport);
}

generateReport();
