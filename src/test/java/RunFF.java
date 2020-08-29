import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RunFF implements InitLogSelenide {
    private WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        Configuration.browser = "firefox";
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(testName = "FF")
    public void runFF() {

        ProjectConfig config = ConfigFactory.create(ProjectConfig.class);
        driver.get(config.appUrl());
    }
}
