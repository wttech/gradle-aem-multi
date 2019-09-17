/// <reference types="Cypress" />

context('Home Page', () => {
  beforeEach(() => {
    cy.visit('/en-us.html')
  });

  it('should have correct window title', () => {
    // https://on.cypress.io/title
    cy.title().should('include', 'Example')
  })
});
