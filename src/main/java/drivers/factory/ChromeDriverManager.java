package drivers.factory;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.bonigarcia.wdm.WebDriverManager.getInstance;

public class ChromeDriverManager extends DriverManager{

    @Override
    public void createDriver() {
        // For Selenoid
        Configuration.browser = "chrome";

        WebDriverManager.chromedriver().setup();

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();

        options.addArguments("test-type");
        options.addArguments("start-maximized");

        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);

        driver = new ChromeDriver(options);
    }

    @Override
    protected void createRemoteDriver() {

    }
}
