Feature: Used cars in chennai

  @sanity
  Scenario: Validaing the functionality of used cars drop-down
    Given the user opens the zigwheels website
    When the user hovers on the used cars option
    Then a drop-down opens displaying the options under used cars topic

  @sanity @regression
  Scenario: Validating whether the chennai button under used cars section is clickable
    Given the user opens the zigwheels website
    When the user hovers on the used cars option
    Then chennai button under used cars section is clickable

  @regression
  Scenario: Validating whether Used-Cars-In-Chennai-Page is getting opened after Clicking
    Given the user opens the zigwheels website
    When the user hovers on the used cars option
    And the user clicks on the chennai button
    Then a page displaying used cars in chennai is opened

  @sanity @regression
  Scenario: Validating whether Popular Models section is displayed or not
    Given the user opens the zigwheels website
    When the user hovers on the used cars option
    And the user clicks on the chennai button
    Then the popular model section is displayed

  @regression
  Scenario: Displaying All Popular Models in Chennai-Used-Cars Page
    Given the user opens the zigwheels website
    When the user hovers on the used cars option
    And the user clicks on the chennai button
    Then the popular models in the page is printed in the console
