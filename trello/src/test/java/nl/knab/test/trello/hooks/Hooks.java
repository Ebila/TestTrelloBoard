package nl.knab.test.trello.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.extern.slf4j.Slf4j;
import nl.knab.test.trello.util.TestBase;

@Slf4j
public class Hooks extends TestBase {
    @Before  //Cucumber annotation which executes this method before each test to launch the browser
    public void initialization(Scenario scenario) throws Exception {
        super.start();
        log.info("Scenario started - {}{}", scenario.getName(), System.lineSeparator());
    }
    @After  //Cucumber annotation which executes this method after each test to close the browser instance
    public void quitGlobalInstance(Scenario scenario){
        if(driver != null){
            driver.quit();
        }
        log.info("Driver instance is closed");
    }
}
