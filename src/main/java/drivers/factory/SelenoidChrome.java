package drivers.factory;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class SelenoidChrome extends DriverManager {
    @Override
    protected void createDriver() {

    }

    public void createRemoteDriver() throws MalformedURLException {
        Configuration.browser = "chrome";
        DriverManagerType type = chromedriver().getDriverManagerType();
        getInstance(type).setup();

        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion("84.0");
        browser.setCapability("enableVNC", true);
        browser.setCapability("enableVideo", false);
        browser.setAcceptInsecureCerts(true);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        browser.setCapability(ChromeOptions.CAPABILITY, options);
        browser.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        remoteDriver = new RemoteWebDriver(
                URI.create("http://192.168.31.155:4444/wd/hub").toURL(), // use correct IP address
                browser
        );
    }
}
