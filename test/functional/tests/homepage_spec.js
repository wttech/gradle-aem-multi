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
            await page.goto(global.httpUrl)
        }, timeout);

        it('should load without error', async () => {
            let text = await page.evaluate(() => document.body.textContent);
            await page.waitForSelector('h4.cart-items-header');
            expect(text).toContain('google')
            await page.goto('https://google.com', { waitUntil: 'networkidle0' })
            const title = await page.title()
            expect(title).toBe('Google')
        })

        afterAll(async () => {
            await page.close()
        });
    },
    timeout
);
