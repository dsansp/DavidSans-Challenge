package org.QA.cucumber.steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import pageObject.PracticeSoftwarePage;
import util.ServerConfig;

public class WebSteps {
    ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    PracticeSoftwarePage  page = new PracticeSoftwarePage();
    @Given("I visit the website")
    public void iVisitTheWebsite() {

        page.visitUrl(cfg.urlWeb());
    }

    @And("Shutdown browser")
    public void shutdownBrowser() {
        page.closeBrowser();
    }

    @When("I Navigate to the ‘Search’ input box")
    public void iNavigateToTheSearchInputBox() {
        page.clickSearch();
    }

    @And("In the ‘Search’ input box, type: “Pliers” and hit the search button")
    public void inTheSearchInputBoxTypePliersAndHitTheSearchButton() {
        page.insertText("Pliers");
        Assertions.assertEquals("Pliers", page.getSearchText());
        page.clickSearchButton();
    }

    @Then("Verify that only “Pliers” are present in the page")
    public void verifyThatOnlyPliersArePresentInThePage() {
        page.waitSearchComplete("Pliers");
      Assertions.assertTrue(page.checkAllItems("Pliers"));
    }

    @And("I take a screenshot")
    public void iTakeAScreenshot() {
        page.captureScreen();
    }
}
