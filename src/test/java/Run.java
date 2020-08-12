import com.codeborne.selenide.WebDriverRunner;
import drivers.factory.DriverManager;
import drivers.factory.DriverManagerFactory;
import drivers.factory.DriverType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;

public class Run {

    private DriverManager driverManager = DriverManagerFactory.getManager(DriverType.CHROME);

    public Run() throws MalformedURLException {
    }

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        WebDriverRunner.setWebDriver(driverManager.getDriver());
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driverManager.quitDriver();
    }

    @Test
    public void openURL() {
        open("https://rewardjet.com/main");
    }
}
