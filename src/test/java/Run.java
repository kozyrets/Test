import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.factory.DriverManager;
import drivers.factory.DriverManagerFactory;
import drivers.factory.DriverType;
import listeners.AllureSelenide;
import listeners.LogType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;

public class Run {

    private DriverManager driverManager = DriverManagerFactory.getManager(DriverType.SELENOID_CHROME);

    public Run() throws MalformedURLException {
    }

    @BeforeTest(alwaysRun = true)
    public void setUp() throws MalformedURLException {

        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                .enableLogs(LogType.BROWSER, Level.ALL)
                .screenshots(true)
                .includeSelenideSteps(true)
        );
        WebDriverRunner.setWebDriver(driverManager.getRemoteDriver());
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driverManager.quitRemoteDriver();
    }

    @Test
    public void openURL() {
        open("https://rewardjet.com/main");
    }
}
