package nl.knab.test.trello.util;

import io.restassured.path.json.JsonPath;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class Util {
    @Value("${trello.api.endpoint}")
    @Getter
    private String apiEndpointForTrello;
    @Value("${trello.api.user.key}")
    @Getter
    private String apiKey;
    @Value("${trello.api.user.token}")
    @Getter
    private String userSecurityToken;
    public static String response;
    public static JsonPath jsonPath;
    public static String newBoardId;
    public static String boardNameProvided;
    public static String boardName;
}
