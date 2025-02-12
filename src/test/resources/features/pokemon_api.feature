@allure.label.owner:dsans
@Api
Feature: Pokemon API Test

  Scenario: Validate that a random Pokemon can be retrieved and its ability verified
    Given I retrieve a random Pokemon from the API
    Then  Status Code should return 200


    Given  I retrieve the Pokemon's abilities
    Then  Status Code should return 200


    Given I validate the ability details and verify the Pokemon exists in the ability list
    Then  Status Code should return 200


  @allure.label.owner:dsans
  @Api
  Scenario: Validate that Charizard is a flying type Pokemon
    Given I look for a flying type Pokemon named "charizard"
    Then The search should return Pokemon info
    And Status Code should return 200
    And The Pokemon type is "flying"