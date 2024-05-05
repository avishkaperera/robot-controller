describe('Validate Robot Controller Elements', () => {
  it('passes', () => {
    cy.visit('http://localhost:3000');

    cy.get('[class="title"]').should('exist').should('have.text', 'Robot Controller');
    cy.get('[class="grid-table"]').should('exist');
    cy.get('[data-testid="7"]').should('exist').children().should('have.length', 1);

    cy.get('[class="script-title"]').should('exist').should('have.text', 'Input Script Below');
    cy.get('[class="script-input"]').should('exist');
    cy.get('[class="script-execute"]').should('exist').children()
      .should('have.text', 'Execute Script');
  })
})