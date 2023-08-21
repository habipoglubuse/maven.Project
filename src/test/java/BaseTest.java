import io.github.bonigarcia.wdm.WebDriverManager;
import logger.Log;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testlogger.TestResultLogger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(TestResultLogger.class)
public class BaseTest {
    Log log = new Log();

    WebDriver driver;

    @BeforeAll
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.turkcell.com.tr");
        driver.manage().window().maximize();
        log.info("Turkcell web sitesine gidildi.");
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
        log.info("Web sitesi kapatıldı.");
    }
}