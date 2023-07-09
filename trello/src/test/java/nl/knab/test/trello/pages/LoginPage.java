package nl.knab.test.trello.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class LoginPage {

    @FindBy(id ="user")
    private WebElement username;

    @FindBy(id="login")
    private WebElement continueButton;

    @FindBy(id ="password")
    private WebElement password;

    @FindBy(className = "trello-main-logo")
    private WebElement websiteLogo;

    @FindBy(id="login-submit")
    private WebElement loginButton;

    @FindBy(className= "awhMAO3feX83U4")
    private WebElement logoOnHomePage;
}
