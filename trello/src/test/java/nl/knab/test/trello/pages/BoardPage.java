package nl.knab.test.trello.pages;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Locators on the board page
@Slf4j
@Getter
public class BoardPage {

    @FindBy(className = "X7iA6JJNiXCA2r")
    private WebElement createButton;

    @FindBy(xpath = "//span[contains(text(),'Create board')]")
    private WebElement createBoardOption;

    @FindBy(className = "TzntopStGOcVjM")
    private WebElement createBoardHeading;

    @FindBy(className = "fMvxkh4DHKJGnq")
    private WebElement boardTitleInput;

    @FindBy(xpath = "//div[contains(text(),'Workspace')]")
    private WebElement workspaceVisibilty;

    @FindBy(xpath = "//button[contains(text(), 'Create')]")
    private WebElement createButtonOnCreateBoard;

    @FindBy(xpath = "//*[contains(text(),'YOUR WORKSPACES')]")
    private WebElement yourWorkSpaceHeader;

    @FindBy(xpath = "//*[contains(text(),'YOUR WORKSPACES')]/../descendant::h3[@class='boards-page-board-section-header-name']")
    private WebElement yourPersonalWorkSpaceName;

    @FindBy(xpath = "//*[@class='board-tile-details-name']")
    private WebElement createdWorkSpaceName;

    @FindBy(xpath = "//*[@data-testid='board-name-display']")
    private WebElement createdBoardName;

    @FindBy(className = "board-tile-details-name")
    private WebElement boardTileOnWorkspace;

    //To close board
    @FindBy(xpath = "//button[@aria-label = 'Show menu']")
    private WebElement additionalOptionsMenu; //on the board page, click this to close

    @FindBy(xpath = "//*[@class='board-menu-navigation-item']//*[contains(text(), 'More')]")
    private WebElement moreOption;

    @FindBy(partialLinkText = "Close board")
    private WebElement closeBoardOption;

    @FindBy(xpath = "//*[@class='pop-over-header-title'][contains(text(), 'Close board?')]")
    private WebElement closeBoardPopup;

    @FindBy(xpath = "//input[@value='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "//*[@data-testid='close-board-big-message']")
    private WebElement deleteSuccessText;

    //Add list and card
    @FindBy(xpath = "//*[@data-testid='list-header']/h2[contains(text(), 'To Do')]")
    private WebElement toDoList;

    @FindBy(xpath = "//*[contains(text(), 'Add a card')]")
    private WebElement addACardOnList;

    @FindBy(xpath = "//*[@placeholder='Enter a title for this card…']")
    private WebElement addTitleForCard;

    @FindBy(xpath = "//input[@value='Add card']")
    private WebElement addCardButton;

    @FindBy(xpath = "//span[contains(text(), \'CI/Cd\')]")
    private WebElement addedCardLabel;

    @FindBy(xpath = "//*[@class='placeholder'][contains(text(), 'Add a list')]")
    private WebElement addAList;

    @FindBy(className = "list-name-input")
    private WebElement enterListTitleInput;

    @FindBy(xpath = "//input[@value='Add list']")
    private WebElement addListButton;

    @FindBy(xpath = "//*[contains(text(), 'Action Items')]/../following::span[contains(text(), 'Add a card')]")
    private WebElement addACardOption;

    @FindBy(xpath = "//*[@placeholder = 'Enter a title for this card…']")
    private WebElement enterTitleForCard;

    @FindBy(xpath = "//input[@value = 'Add card']")
    private WebElement addCard;

    @FindBy(xpath = "//*[@data-testid='list-header']")
    private WebElement listHeader;

    @FindBy(xpath = "//*[contains(text(), 'Implement CI/CD')]")
    private WebElement cardLabel;
}
