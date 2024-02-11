package core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

abstract public class BaseTest implements Constants{
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static JavascriptExecutor jsExecutor;
    protected static MainPage mainPage;
    protected static Random random;
    protected static Actions actions;
    public static byte[] screenshot;

    @ExtendWith(MonitorRule.class)
    public MonitorRule monitorRule = new MonitorRule();

    @BeforeAll
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(IMPLICIT_WAIT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(IMPLICIT_WAIT));
        jsExecutor = (JavascriptExecutor)driver;
        mainPage = new MainPage();
        random = new Random();
        actions = new Actions(driver);
        driver.get(STORE_URL);
        acceptDataPolicy();
    }

    @AfterEach
    public void afterEach() {
        screenshot = ((TakesScreenshot) BaseTest.driver)
                .getScreenshotAs(OutputType.BYTES);
    }


    @AfterAll
    public static void tearDown(){
        driver.close();
        driver.quit();
    }

    private static void acceptDataPolicy(){
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.modalWindowAcceptBtn)).click();
        wait.until(CustomExpectedConditions.disappearanceOfElement(mainPage.modalWindowAcceptBtn));
    }
}
