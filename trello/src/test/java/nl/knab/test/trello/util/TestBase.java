package nl.knab.test.trello.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import nl.knab.test.trello.pages.BoardPage;
import nl.knab.test.trello.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
@Slf4j
public class TestBase {
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static BoardPage createBoardPage;
    public WebDriverWait wait;
    public void start(){
        WebDriverManager.chromedriver().setup(); //WebDriverManager manages the drivers and no need to manually download the driver jar
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        createBoardPage = PageFactory.initElements(driver, BoardPage.class);
    }
   protected void waitVisibility(WebElement element){
         wait = new WebDriverWait(driver, Duration.ofSeconds(25));
         wait.until(ExpectedConditions.visibilityOf(element)); //Waits until the expected element is visible
    }
}