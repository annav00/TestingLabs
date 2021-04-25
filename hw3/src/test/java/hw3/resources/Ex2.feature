Feature: feature file for ex2 of hw3
  Background:
    Given I open the page
    And I log as "Roman" - "Jdi1234"

  Scenario: Assert username
    Then Username is "ROMAN IOVLEV"

  Scenario: Assert browser title
    Then Browser title is "Home Page"

  Scenario: Assert the top "Service" subcategory
    Then Drop down options of top Service have proper values

  Scenario: Assert the left "Service" subcategory
    Then Drop down options of left Service have proper values

  Scenario: Assert elements
    Given Open "Different Elements" Page
    Then There are 4 checkboxes
    Then There are 4 radios
    Then There are 1 dropdown
    Then There are 2 buttons

  Scenario: Assert Right and Left Section
    Given Open "Different Elements" Page
    Then Right Section displayed
    And Left Section displayed

  Scenario: Select checkboxes
    Given Open "Different Elements" Page
    Then Checkboxes "Water" and "Wind" are selected
    And Status of checkboxes in Log row are displayed and corresponding
    Then Checkboxes "Water" and "Wind" are unselected
    And Status of checkboxes are displayed and corresponding

  Scenario: Select radio
    Given Open "Different Elements" Page
    Then Radio "Selen" is selected
    And Status of radios in Log row is displayed and corresponding

  Scenario: Select in dropdown
    Given Open "Different Elements" Page
    Then "Yellow" in dropdown is selected
    And Status of dropdown in Log row is displayed and corresponding

  Scenario: Close browser
    Then Close browser