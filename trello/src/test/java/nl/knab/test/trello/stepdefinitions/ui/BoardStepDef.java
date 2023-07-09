package nl.knab.test.trello.stepdefinitions.ui;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import nl.knab.test.trello.util.Constants;
import nl.knab.test.trello.util.TestBase;
import nl.knab.test.trello.util.Util;
import static org.junit.Assert.*;
@Slf4j
public class BoardStepDef extends TestBase{
    private String listLabel;
     /*
        Click the big Create button on the homepage
        Select the 'Create board' option
     */
    @When("I click the Create button to create a new board")
    public void createANewBoardFromHomePage() {
        waitVisibility(createBoardPage.getCreateButton());
        createBoardPage.getCreateButton().click();
        waitVisibility(createBoardPage.getCreateBoardOption());
        createBoardPage.getCreateBoardOption().click();
        log.info("A request is made to create a new board");
    }
    /*
       Enter the board name and leave the rest to default
       Click the Create button to create the board
    */
    @And("I enter the board name {string} and submit")
    public void enterTheDetailsOfBoard(String newBoardName){
        Util.boardName = newBoardName;
        waitVisibility(createBoardPage.getCreateBoardHeading());
        assertEquals("Create board", createBoardPage.getCreateBoardHeading().getText());
        createBoardPage.getBoardTitleInput().sendKeys(newBoardName);
        assertTrue(createBoardPage.getWorkspaceVisibilty().isEnabled());
        createBoardPage.getCreateButtonOnCreateBoard().click();
        log.info("A new board is created");
    }
    /*
        The board creation is verified by checking the board name.
     */
    @Then("a new board is created successfully")
    public void aNewBoardIsCreatedSuccessfully() throws InterruptedException {
        Thread.sleep(2000);
        String boardNameOnPath = Util.boardName.toLowerCase();
        if (driver.getCurrentUrl().contains("/" + boardNameOnPath)) {
            log.info("The board name is verified");
        } else {
            assertEquals(Util.boardName, createBoardPage.getBoardTileOnWorkspace().getAttribute("title"));
            log.info("The board is visible on the workspace");
        }
    }
    /*
        Click the board name to open it
     */
    @Given("I have opened the Trello board page")
    public void iHaveOpenedTheTrelloBoardPage() {
       waitVisibility(createBoardPage.getYourWorkSpaceHeader());
       createBoardPage.getCreatedWorkSpaceName().click();
    }
    /*
        Added a new card to a list. If the list is not there, a new list is added and card is added next.
     */
    @When("I edit a board to add a new card")
    public void iEditABoardToAddANewCard() throws InterruptedException {
            Thread.sleep(1000);
        if (driver.getPageSource().contains(Constants.LIST_LABEL_TO_DO)){
            listLabel = Constants.LIST_LABEL_TO_DO;
            Thread.sleep(2000);
            createBoardPage.getAddACardOnList().click();
            createBoardPage.getAddTitleForCard().sendKeys(Constants.CARD_LABEL);
            createBoardPage.getAddCardButton().click();
            log.info("Added a new card");
        }else{
            listLabel = Constants.LIST_LABEL_ACTION_ITEM;
            createBoardPage.getAddAList().click();
            createBoardPage.getEnterListTitleInput().sendKeys(Constants.LIST_LABEL_ACTION_ITEM);
            createBoardPage.getAddListButton().click();
            createBoardPage.getAddACardOption().click();
            createBoardPage.getEnterTitleForCard().sendKeys(Constants.CARD_LABEL);
            createBoardPage.getAddCard().click();
            log.info("Added a new card");
        }
    }
    /*
        Verify the card added by checking the list label and card label
     */
    @Then("the card is added and board is updated")
    public void theCardIsAddedAndBoardIsUpdated() {
        assertEquals( listLabel, createBoardPage.getListHeader().getText());
        assertTrue(createBoardPage.getCardLabel().isDisplayed());
        log.info("New card is added successfully");
    }
    /*
        Method to close the board. Open the board first, then click on the additional options menu.
        From the options displayed, select option to close the board
     */
    @When("I click the menu options and close the board")
    public void iClickTheMenuOptionsAndCloseTheBoard() throws InterruptedException {
        waitVisibility(createBoardPage.getCreatedBoardName());
        Thread.sleep(1000);
        if(createBoardPage.getAdditionalOptionsMenu().isDisplayed()){
            createBoardPage.getAdditionalOptionsMenu().click();
        }
        Thread.sleep(1000);
        createBoardPage.getMoreOption().click();
        createBoardPage.getCloseBoardOption().click();
        assertTrue(createBoardPage.getCloseBoardPopup().isDisplayed());
        createBoardPage.getCloseButton().click();
        log.info("A request is submitted to close the board");
    }
    /*
        Verify whether the board is closed by asserting the success text shown on closing the board.
     */
    @Then("the board is closed successfully")
    public void theBoardIsClosedSuccessfully() throws InterruptedException {
        Thread.sleep(1000);
        assertTrue(createBoardPage.getDeleteSuccessText().getText().contains("is closed"));
        assertTrue(createBoardPage.getDeleteSuccessText().getText().contains(Util.boardName));
        log.info("The board is closed");
    }
}
