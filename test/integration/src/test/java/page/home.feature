Feature: Home Page

  Scenario: Check page response

    Given url 'http://demo.example.com/en-us.html'
    When method get
    Then status 200
    And match response contains 'Home Page of English US market'
    * def componentMarkup =
      """
      function(arg) {
        var Util = Java.type('Util').INSTANCE;
        return Util.test();
      }
      """
    * def richText = call componentMarkup 'AAA'
    And print richText
