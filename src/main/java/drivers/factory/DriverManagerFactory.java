package drivers.factory;

import java.net.MalformedURLException;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType type) throws MalformedURLException {

        DriverManager driverManager;

        switch (type) {
            case SELENOID_CHROME:
                driverManager = new SelenoidChrome();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new ChromeDriverManager();
                break;
        }
        return driverManager;

    }
}
