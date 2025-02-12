@allure.label.owner:dsans
@UI
Feature: ToolsShopDEMO. UI Automation Challenge Test

  Scenario: Navigate and search
    Given I visit the website
    When  I Navigate to the ‘Search’ input box
    And  In the ‘Search’ input box, type: “Pliers” and hit the search button
    Then Verify that only “Pliers” are present in the page
    And I take a screenshot
    And  Shutdown browser


