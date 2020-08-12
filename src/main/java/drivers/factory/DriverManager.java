package drivers.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public abstract class DriverManager {

    protected WebDriver driver;
    protected RemoteWebDriver remoteDriver;

    protected abstract void createDriver();

    protected abstract void createRemoteDriver() throws MalformedURLException;

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }

    }

    public WebDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    public RemoteWebDriver getRemoteDriver() throws MalformedURLException {
        if (remoteDriver == null) {
            createRemoteDriver();
        }
        return remoteDriver;
    }

    public void quitRemoteDriver() {
        if (remoteDriver != null) {
            remoteDriver.quit();
            remoteDriver = null;
        }

    }
}
