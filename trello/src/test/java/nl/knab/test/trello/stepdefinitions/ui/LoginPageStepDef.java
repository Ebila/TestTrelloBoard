package nl.knab.test.trello.stepdefinitions.ui;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import nl.knab.test.trello.util.TestBase;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class LoginPageStepDef extends TestBase {
    @Value("${trello.web.url}")
    private String webURL;
    @Value(("${trello.web.username}"))
    private String userName;
    @Value("${trello.web.password}")
    private String userPassword;

    /* Navigate to the Login page url
       Verify whether the logo is displayed and the title is matching
    */
    @Given("I navigate to the Login page of Trello")
    public void navigateToLoginPage(){
        driver.get(webURL);
        waitVisibility(loginPage.getWebsiteLogo());
        assertTrue(loginPage.getWebsiteLogo().isDisplayed());
        assertEquals("Log in to Trello", driver.getTitle());
        log.info("Navigated to the login page successfully");
    }
    /*
        Enter the username, password and login
     */
    @When("I enter the correct credentials and submit")
    public void enterUserCredentials(){
        waitVisibility(loginPage.getUsername());
        loginPage.getUsername().sendKeys(userName);
        waitVisibility(loginPage.getContinueButton());
        loginPage.getContinueButton().click();
        waitVisibility(loginPage.getPassword());
        loginPage.getPassword().sendKeys(userPassword);
        waitVisibility(loginPage.getLoginButton());
        loginPage.getLoginButton().click();
        log.info("Login is successful");
    }
     //Verified the page title and URL to confirm
    @Then("I am logged in the home page")
    public void loggedInToHomePage(){
        waitVisibility(loginPage.getLogoOnHomePage());
        assertEquals("Boards | Trello", driver.getTitle());
        assertTrue(driver.getCurrentUrl().contains("/boards"));
        log.info("Login is verified");
    }
}
