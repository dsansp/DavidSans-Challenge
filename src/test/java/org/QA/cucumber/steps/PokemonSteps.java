//package org.QA.cucumber.steps;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.qameta.allure.Step;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//
//import org.QA.cucumber.objects.ObjectsStored;
//import org.junit.jupiter.api.Assertions;
//
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PokemonSteps {
//    private static final String BASE_URL = "https://pokeapi.co/api/v2";
//    ObjectsStored objects = new ObjectsStored();
//
//    @Step("Retrieve a random Pokemon from the API")
//    @Given("I retrieve a random Pokemon from the API")
//    public void iRetrieveARandomPokemonFromTheApi() {
//        Response allPokemonResponse = RestAssured.given()
//                .baseUri(BASE_URL)
//                .when()
//                .get("/pokemon/")
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();
//
//        List<Map<String, String>> pokemonResults = allPokemonResponse.jsonPath().getList("results");
//        String selectedPokemon = pokemonResults.get((int) (Math.random() * pokemonResults.size())).get("name");
//        System.out.println("Selected Pokemon: " + selectedPokemon);
//        objects.setPokemon(selectedPokemon);
//    }
//
//    @Step("Retrieve the Pokemon's abilities")
//    @And("I retrieve the Pokemon's abilities")
//    public void iRetrieveThePokemonAbilities() {
//        String selectedPokemon = objects.getPokemon();
//        Response pokemonInfoResponse = RestAssured.given()
//                .baseUri(BASE_URL)
//                .when()
//                .get("/pokemon/" + selectedPokemon)
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();
//
//        objects.setStatus(pokemonInfoResponse.statusCode());
//        List<Map<String, Object>> abilities = pokemonInfoResponse.jsonPath().getList("abilities");
//       objects.setPokemonStats(abilities);
//
//
//    }
//
//    @Step("Validate ability details and verify the Pokemon exists in the ability list")
//    @Then("I validate the ability details and verify the Pokemon exists in the ability list")
//    public void iValidateTheAbilityDetailsAndVerifyThePokemonExistsInTheAbilityList() {
//        String selectedPokemon = objects.getPokemon();
//        String selectedAbility = objects.getAbility();
//        String abilityName = objects.getAbilityName();
//        List<Map<String, Object>> pokemonList = objects.getPokemonList();
//
//        assertEquals(selectedAbility, abilityName, "Ability name mismatch!");
//        Assertions.assertTrue(pokemonList.stream()
//                        .anyMatch(p -> ((Map<String, Object>) p.get("pokemon")).get("name").equals(selectedPokemon)),
//                "Pokemon not found in ability list!");
//    }
//
//    @And("Status Code should return 200")
//    public void statusCodeShouldReturn200() {
//        int statusCode = objects.getStatus();
//        Assertions.assertEquals(statusCode, 200);
//    }
//
//    @Then("I get a pokemon from the API")
//    public void iGetAPokemonFromTheAPI() {
//
//        List<Map<String, Object>> abilities =objects.getPokemonStats();
//        String selectedAbility = (String) ((Map<String, Object>) abilities.get((int) (Math.random() * abilities.size())).get("ability")).get("name");
//        System.out.println("Selected Ability: " + selectedAbility);
//        objects.setAbility(selectedAbility);
//        Assertions.assertFalse(selectedAbility.isEmpty());
//
//    }
//
//    @Given("I get the abilities from previous Pokemon")
//    public void iGetTheAbilitiesFromPreviousPokemon() {
//        String selectedPokemon = objects.getPokemon();
//        String selectedAbility = objects.getAbility();
//
//        Response abilityInfoResponse = RestAssured.given()
//                .baseUri(BASE_URL)
//                .when()
//                .get("/ability/" + selectedAbility)
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();
//
//        String abilityName = abilityInfoResponse.jsonPath().getString("name");
//        objects.setAbilityName(abilityName);
//        List<Map<String, Object>> pokemonList = abilityInfoResponse.jsonPath().getList("pokemon");
//        objects.setPokemonList(pokemonList);
//   }
//
//    @Given("I check in this ability if the pokemon exist")
//    public void iCheckInThisAbilityIfThePokemonExist() {
//    }
//}

package org.QA.cucumber.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.QA.cucumber.objects.ObjectsStored;
import util.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokemonSteps {
    ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private final String BASE_URL = cfg.url();
    private final String POKEMONSERVER = cfg.servicePokemonList();
    private final String ABILITIESSERVER = cfg.servicePokemonAbility();
    ObjectsStored objects = new ObjectsStored();
    @Order(1)
    @Step("Retrieve a random Pokemon from the API")
    @Given("I retrieve a random Pokemon from the API")
    public void iRetrieveARandomPokemonFromTheApi() {
        Response allPokemonResponse = RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .get(POKEMONSERVER)
                .then()
                .statusCode(200)
                .extract()
                .response();
        objects.setLastResponse(allPokemonResponse);
        Assertions.assertEquals(200, allPokemonResponse.getStatusCode(), "Expected status 200 but got " + allPokemonResponse.getStatusCode());
        List<Map<String, String>> pokemonResults = allPokemonResponse.jsonPath().getList("results");
        String selectedPokemon = pokemonResults.get((int) (Math.random() * pokemonResults.size())).get("name");
        System.out.println("Selected Pokemon: " + selectedPokemon);
        objects.setPokemon(selectedPokemon);
        statusCodeShouldReturn200();
    }
    @Order(3)
    @Step("Retrieve the Pokemon's abilities")
    @Given("I retrieve the Pokemon's abilities")
    public void iRetrieveThePokemonAbilities() {
        String selectedPokemon = objects.getPokemon();
        System.out.println("Pokemon: "+selectedPokemon);


        Response pokemonInfoResponse = RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .get(POKEMONSERVER + selectedPokemon)
                .then()
                .statusCode(200)
                .extract()
                .response();
        objects.setLastResponse(pokemonInfoResponse);
        Assertions.assertEquals(200, pokemonInfoResponse.getStatusCode(), "Expected status 200 but got " + pokemonInfoResponse.getStatusCode());
        objects.setStatus(pokemonInfoResponse.statusCode());
        List<Map<String, Object>> abilities = pokemonInfoResponse.jsonPath().getList("abilities");
        String selectedAbility = (String) ((Map<String, Object>) abilities.get((int) (Math.random() * abilities.size())).get("ability")).get("name");
        System.out.println("Selected Ability: " + selectedAbility);
        objects.setAbility(selectedAbility);
        statusCodeShouldReturn200();
    }
    @Order(5)
    @Step("Validate ability details and verify the Pokemon exists in the ability list")
    @Given("I validate the ability details and verify the Pokemon exists in the ability list")
    public void iValidateTheAbilityDetailsAndVerifyThePokemonExistsInTheAbilityList() {
        String selectedPokemon = objects.getPokemon();
        String selectedAbility = objects.getAbility();

        Response abilityInfoResponse = RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .get(ABILITIESSERVER + selectedAbility)
                .then()
                .statusCode(200)
                .extract()
                .response();
        objects.setLastResponse(abilityInfoResponse);
        Assertions.assertEquals(200, abilityInfoResponse.getStatusCode(), "Expected status 200 but got " + abilityInfoResponse.getStatusCode());
        String abilityName = abilityInfoResponse.jsonPath().getString("name");
        List<Map<String, Object>> pokemonList = abilityInfoResponse.jsonPath().getList("pokemon");

        assertEquals(selectedAbility, abilityName, "Ability name mismatch!");
        Assertions.assertTrue(pokemonList.stream()
                        .anyMatch(p -> ((Map<String, Object>) p.get("pokemon")).get("name").equals(selectedPokemon)),
                "Pokemon not found in ability list!");
        statusCodeShouldReturn200();
    }


    @Then("Status Code should return 200")
    public void statusCodeShouldReturn200() {
        Assertions.assertEquals(200, objects.getLastStatusCode(), "Expected status code 200 but got " + objects.getLastStatusCode());
    }

    @Given("I look for a flying type Pokemon named {string}")
    public void iLookForAFlyingTypePokemonNamed(String pokemonName) {
        Response pokemonResponse = RestAssured.given()
                .baseUri(BASE_URL)
                .when()
                .get(POKEMONSERVER + pokemonName)
                .then()
                .statusCode(200)
                .extract()
                .response();
        objects.setLastResponse(pokemonResponse);
        Assertions.assertEquals(200, pokemonResponse.getStatusCode(), "Expected status 200 but got " + pokemonResponse.getStatusCode());
        objects.setPokemon(pokemonName);
        objects.setStatus(pokemonResponse.statusCode());
        objects.setPokemonType(pokemonResponse.jsonPath().getString("types[0].type.name"));
        System.out.println("types: "+ pokemonResponse.jsonPath().getString("types[0]"));
    }

    @Then("The search should return Pokemon info")
    public void theSearchShouldReturnPokemonInfo() {
        int statusCode = objects.getStatus();
        Assertions.assertEquals(statusCode, 200);
    }

    @And("The Pokemon type is {string}")
    public void thePokemonTypeIs(String expectedType) {
        String actualType = objects.getPokemonType();
        assertEquals(expectedType, actualType, "Pokemon type mismatch!");
    }
}



