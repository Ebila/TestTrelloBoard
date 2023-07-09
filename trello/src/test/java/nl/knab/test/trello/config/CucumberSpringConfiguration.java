package nl.knab.test.trello.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = nl.knab.test.trello.config.CucumberRunnerTest.class)
public class CucumberSpringConfiguration {
}
