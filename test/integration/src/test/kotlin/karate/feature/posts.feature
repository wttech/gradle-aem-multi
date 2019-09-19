@3rd-party
Feature: Posts from 3rd-party Web Service

  Background:
    * url 'https://jsonplaceholder.typicode.com'

  Scenario: Check posts data format

    Given path '/posts'
    When method get
    Then status 200
    And match $ == '#[100]'
    And match each $ ==
    """
    {
    "userId": #number,
    "id": #number,
    "title": "#string",
    "body": "#string"
    }
    """
