Feature: Google search As a user i want to have ability to search by Google information

    @web @google
    Scenario: Google search of valid value
        Given user opens Google search page
        When the search phrase "test" is entered
        When user clicks on Google search button
        Then results for "test" are shown
    @web @google
    Scenario: Google search with empty value
        Given user opens Google search page
        When user clicks on Google search button
        Then user stays on Google search page
    @web @google
    Scenario: Google search of probably valid value
        Given user opens Google search page
        When user changes language to english
        When the search phrase "testtest" is entered
        When user clicks on Google search button
        Then user sees “Did you mean” label
        When user clicks on link near “Did you mean” label
        Then results for "test test" are shown
    @web @google
    Scenario: Google search of very long value
        Given user opens Google search page
        When user changes language to english
        When the search phrase "ttesttesttesttesttesttestwjewgdhjgwjhdghjwgedhjwekdggwdhgwedgewdt" is entered
        When user clicks on Google search button
        Then user receives that no search results appears