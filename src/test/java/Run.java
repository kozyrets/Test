import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.SoftAsserts;
import drivers.factory.DriverManager;
import drivers.factory.DriverManagerFactory;
import drivers.factory.DriverType;
import listeners.Log;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ParametersExcel;

import java.io.IOException;
import java.net.MalformedURLException;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.Selenide.*;

@Listeners({SoftAsserts.class}) // Don't stop on assertion fail Selenide
public class Run extends ParametersExcel implements InitLogSelenide {

    // Choose browser
    private DriverManager driverManager = DriverManagerFactory.getManager(DriverType.CHROME);

    public Run() throws MalformedURLException {
    }

    @BeforeTest(alwaysRun = true)
    public void setUp() throws MalformedURLException {            // MalformedURLException using for RemoteDriver
        WebDriverRunner.setWebDriver(driverManager.getDriver());  // for RemoteDriver use - driverManager.getRemoteDriver
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driverManager.quitDriver();
    }        // for RemoteDriver - driverManager.quitRemoteDriver

    @Test
    public void openURL() throws IOException {

        GetExcelFile("d:\\TMP\\Example.xlsx");
        GetExcelSheet("Task");

        // Don't stop on assertion fail Selenide
        Configuration.assertionMode = SOFT;

        Log log = new Log();

        log.setLog("Open Main Page");
        open(GetParamValue("URL"));
        log.setLog("Check title");
        $x("//*[@class='logo _head']").should(Condition.exactText(GetParamValue("TITLE")));
        log.setLog("logIn");
        $(".items__item").click();
        log.setLog("Get Started");
        $("button-brand").click();

    }


}
