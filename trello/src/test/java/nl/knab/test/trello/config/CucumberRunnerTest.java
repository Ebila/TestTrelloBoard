package nl.knab.test.trello.config;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;

@RunWith(Cucumber.class)
@CucumberOptions( features={"src/test/resources/features"},
        glue={"nl/knab/test/trello/config", "nl/knab/test/trello/hooks", "nl/knab/test/trello/stepdefinitions"},
        plugin={"pretty", "json::target/cucumber-reports/cucumber-report.json",
                "junit:target/cucumber-reports/cucumber.xml",
                "html:target/cucumber-reports/report.html"},
        monochrome = true
)
@ComponentScan("nl.knab.test.trello")
public class CucumberRunnerTest {
}
