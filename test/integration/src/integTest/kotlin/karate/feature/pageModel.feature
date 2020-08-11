@aem
Feature: Page Model Endpoint

  Background:
    * url publishUrl

  Scenario: Check response format

    Given path '/content/example/us/en.model.json'
    When method get
    Then status 200
    And match response ==
    """
    {
      "designPath": "/libs/settings/wcm/designs/default",
      "title": "en",
      "templateName": "page-content",
      "cssClassNames": "page basicpage",
      "language": "en",
      ":items": "#object",
      ":itemsOrder": [
        "root"
        ],
      ":type": "example/components/page"
    }
    """
