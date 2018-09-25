const config = require('./config');

console.log(`Welcome on ${config.siteName}!`);

const add = (a, b) => {
    return a + b;
 }

console.log(add(4,5));