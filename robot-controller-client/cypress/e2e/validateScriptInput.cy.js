describe('Validate Script Execution Behaviour', () => {
    it('passes', () => {
        cy.visit('http://localhost:3000');

        cy.get('[class="script-txt"]')
            .should('exist')
            .type('POSITION 1 3 EAST\nFORWARD 3\nWAIT\nTURNAROUND\nFORWARD 1\nRIGHT\nFORWARD 2');

        cy.get('[class="execute"]').should('exist')
            .click();

        cy.get('[data-testid="16"]').should('exist').children().should('have.length', 1);    
    })
})