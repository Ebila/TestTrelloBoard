package nl.knab.test.trello.stepdefinitions.api;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import nl.knab.test.trello.util.Constants;
import nl.knab.test.trello.util.Util;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class TestTrelloBoardStepDef {
    private String getTrelloBoardEndpoint;
    private int statusCode;
    private String createBoardEndpoint;
    @Autowired
    private Util util;
    /*
        Request to create a new board
     */
    @When("a request is made to create a new Trello board {string} using the endpoint")
    public void createABoard(String boardNameNew) {
        Util.boardNameProvided = boardNameNew;
        createBoardEndpoint = util.getApiEndpointForTrello() +  "/1/boards/";
        statusCode = 200;
        Util.response = createBoard(boardNameNew, createBoardEndpoint, statusCode);
        Util.jsonPath = JsonPath.from(Util.response);
        assertThat(Util.jsonPath.get("name"), Matchers.equalTo(boardNameNew));
    }
    @Then("the board is created successfully")
    public void verifyTheCreateBoardResponse() {
        Util.jsonPath = JsonPath.from(Util.response); //Extracting the response and asserting it using JsonPath Library
        Util.newBoardId = Util.jsonPath.get("id"); //Unique id of the board is fetched
        Util.jsonPath.get("idOrganization").equals(Constants.ID_ORGANIZATION); //The organization id from response should match the id for which account is related
        log.info("A new board is created successfully via API endpoint");
    }
    /*
        Method to get the details of the board created
        Verified the id, name and IdOrganization
     */
    @When("the details of the board are fetched then they are returned")
    public void getBoardById() {
        getTrelloBoardEndpoint = util.getApiEndpointForTrello() +  "/1/boards/" + Util.newBoardId;
        statusCode = 200;
        Util.response = getBoardDetails(getTrelloBoardEndpoint, statusCode);
        Util.jsonPath = JsonPath.from(Util.response);
        assertThat( Util.jsonPath.get("id"), Matchers.equalTo(Util.newBoardId));
        assertThat(Util.jsonPath.get("name"), Matchers.equalTo(Util.boardNameProvided));
        assertThat(Util.jsonPath.get("idOrganization"), Matchers.equalTo(Constants.ID_ORGANIZATION));
        log.info("The details of the board are fetched successfully");
    }
    /*
        Method to edit the details of the board
        The name of the board is updated.
     */
    @When("a request is made to update the name of the Trello board")
    public void aRequestIsMadeToUpdateTheNameOfTheTrelloBoard() {
        getTrelloBoardEndpoint = util.getApiEndpointForTrello() +  "/1/boards/" + Util.newBoardId;
        statusCode = 200;
        Util.response = updateBoard(getTrelloBoardEndpoint, statusCode);
        Util.jsonPath = JsonPath.from(Util.response);
        assertThat(Util.jsonPath.get("id"), Matchers.equalTo(Util.newBoardId));
        assertThat(Util.jsonPath.get("name"), Matchers.equalTo(Constants.TRELLO_BOARD_NEW_NAME));
        log.info("The details of the board are updated successfully");
    }
    /*
        Method to verify whether the board name is updated correctly
     */
    @Then("the board details are updated")
    public void theBoardDetailsAreUpdated() {
        getTrelloBoardEndpoint = util.getApiEndpointForTrello() +  "/1/boards/" + Util.newBoardId;
        statusCode = 200;
        Util.response = getBoardDetails(getTrelloBoardEndpoint, statusCode);
        Util.jsonPath = JsonPath.from(Util.response);
        assertThat( Util.jsonPath.get("id"), Matchers.equalTo(Util.newBoardId));
        assertThat(Util.jsonPath.get("name"), Matchers.equalTo(Constants.TRELLO_BOARD_NEW_NAME));
        log.info("The details of the board are fetched successfully");
    }
    /*
        Method to delete the board created.
        Board name is added in the request.
     */
    @When("a request is made to delete the Trello board")
    public void aRequestIsMadeToDeleteTheTrelloBoard() {
        getTrelloBoardEndpoint = util.getApiEndpointForTrello() +  "/1/boards/" + Util.newBoardId;
        statusCode = 200;
        Util.response = deleteBoard(getTrelloBoardEndpoint, statusCode);
        Util.jsonPath = JsonPath.from(Util.response);
        assertThat(Util.jsonPath.get("_value"), Matchers.equalTo(null));
        log.info("The board is deleted successfully");
    }
    /*
        Method to verify whether the board is deleted by fetching the board by id
        Board not found error is shown.
     */
    @Then("the board is deleted")
    public void theBoardIsDeleted() {
        getTrelloBoardEndpoint = util.getApiEndpointForTrello() +  "/1/boards/" + Util.newBoardId;
        statusCode = 404;
        getBoardDetails(getTrelloBoardEndpoint, statusCode);
        log.info("Response is {}", getBoardDetails(getTrelloBoardEndpoint, statusCode));
    }
    /*
        Method to get the board details
     */
    private String getBoardDetails(String getTrelloBoardEndpoint, int statusCode){
        return given()
                .queryParam("key", util.getApiKey())
                .queryParam("token", util.getUserSecurityToken())
                .header(Constants.ACCEPT, Constants.ACCEPT_JSON)
                .get(getTrelloBoardEndpoint)
                .then()
                .statusCode(statusCode)
                .extract()
                .response()
                .asString();
    }
    /*
        Method to create a board
     */
    private String createBoard(String boardNameNew, String createBoardEndpoint, int statusCode){
       return given()
                .queryParam("name", boardNameNew)
                .queryParam("key", util.getApiKey())
                .queryParam("token", util.getUserSecurityToken())
                .header(Constants.ACCEPT, Constants.ACCEPT_JSON)
                .post(createBoardEndpoint)
                .then()
                .statusCode(statusCode) //Status code is verified
                .extract()
                .response()
                .asString();
    }
    /*
        Method to edit/update a board
     */
    private String updateBoard(String getTrelloBoardEndpoint, int statusCode){
     return given()
                .queryParam("key", util.getApiKey())
                .queryParam("token", util.getUserSecurityToken())
                .queryParam("name", Constants.TRELLO_BOARD_NEW_NAME)
                .header(Constants.ACCEPT, Constants.ACCEPT_JSON).urlEncodingEnabled(false)
                .put(getTrelloBoardEndpoint)
                .then()
                .statusCode(statusCode)
                .extract()
                .response()
                .asString();
    }
    /*
        Method to delete a board
     */
    private String deleteBoard(String getTrelloBoardEndpoint, int statusCode){
        return given()
                .queryParam("key", util.getApiKey())
                .queryParam("token", util.getUserSecurityToken())
                .header(Constants.ACCEPT, Constants.ACCEPT_JSON).urlEncodingEnabled(false)
                .delete(getTrelloBoardEndpoint)
                .then()
                .statusCode(statusCode)
                .extract()
                .response()
                .asString();
    }
}
