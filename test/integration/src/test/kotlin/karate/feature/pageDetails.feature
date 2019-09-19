@aem
Feature: Page Details Endpoint

  Background:
    * url baseUrl

  Scenario: Check response for home page

    Given path '/en-us.details.json'
    When method get
    Then status 200
    And match response ==
    """
    {
      "title": "English US",
      "description": "Home Page of English US market",
      "created": #object,
      "userId": "#string",
      "posts": #array,
      "languageCode": "en"
    }
    """
