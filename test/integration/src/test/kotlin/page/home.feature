Feature: Home Page

  Scenario: Check page response

    Given url 'http://demo.example.com/en-us.html'
    When method get
    Then status 200
    And match response contains 'Home Page of English US market'
