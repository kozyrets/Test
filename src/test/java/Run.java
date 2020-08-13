import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.factory.DriverManager;
import drivers.factory.DriverManagerFactory;
import drivers.factory.DriverType;
import listeners.AllureSelenide;
import listeners.Log;
import listeners.LogType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.open;

public class Run {

    // Choose driver
    private DriverManager driverManager = DriverManagerFactory.getManager(DriverType.CHROME);

    public Run() throws MalformedURLException {
    }

    @BeforeTest(alwaysRun = true)
    public void setUp() throws MalformedURLException { // MalformedURLException using for RemoteDriver

        // For selenide logging to console and logFile.log
        SelenideLogger.addListener("LogToConsole", new Log());

        // For selenide logging in Allure report
        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                .enableLogs(LogType.BROWSER, Level.ALL)
                .screenshots(true)
                .includeSelenideSteps(true)
        );

        WebDriverRunner.setWebDriver(driverManager.getDriver());  // for RemoteDriver use - driverManager.getRemoteDriver
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driverManager.quitDriver();
    }   // for RemoteDriver use - driverManager.quitRemoteDriver

    @Test
    public void openURL() {
        open("https://rewardjet.com/main");
    }
}
