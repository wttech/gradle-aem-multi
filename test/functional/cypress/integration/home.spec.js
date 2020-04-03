/// <reference types="Cypress" />

context('Home Page', () => {
  beforeEach(() => {
    cy.visit('/')
  });

  it('should have correct window title', () => {
    // https://on.cypress.io/title
    cy.title().should('include', 'en')
  })
});
