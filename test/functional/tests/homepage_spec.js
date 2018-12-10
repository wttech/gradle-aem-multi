const timeout = 5000;

const puppeteer = require('puppeteer')

let browser
let page

describe(
    '/ (Home Page)',
    () => {
        let page;
        beforeAll(async () => {
            page = await global.__BROWSER__.newPage();
            await page.goto(instance.httpUrl + "/content/example-demo/en-us.html")
        }, timeout);

        it('should load without error', async () => {
            let text = await page.evaluate(() => document.body.textContent);
            await page.waitForSelector('header h1');
            expect(text).toContain('English')
        });

        afterAll(async () => {
            await page.close()
        });
    },
    timeout
);
