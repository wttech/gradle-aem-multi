const { merge } = require('mochawesome-merge');
const generator = require('mochawesome-report-generator');

async function generateReport() {
    const jsonReport = await merge();
    await generator.create(jsonReport);
    process.exit(0);
}

generateReport();
