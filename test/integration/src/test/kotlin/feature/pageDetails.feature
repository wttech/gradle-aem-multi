Feature: Page Details Endpoint

  Scenario: Check response for home page

    Given url 'http://demo.example.com/en-us.details.json'
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
