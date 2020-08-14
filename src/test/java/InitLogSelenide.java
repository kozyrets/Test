import com.codeborne.selenide.logevents.SelenideLogger;
import listeners.AllureSelenide;
import listeners.Log;
import listeners.LogType;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Level;

public interface InitLogSelenide {

    @BeforeSuite(alwaysRun = true)
    static void setUpLogging() {
        // For selenide logging to console and logFile.log
        SelenideLogger.addListener("LogToConsole", new Log());

        // For selenide logging in Allure report
        SelenideLogger.addListener(
                "AllureSelenide",
                new AllureSelenide()
                        .enableLogs(LogType.BROWSER, Level.ALL)
                        .screenshots(true)
                        .savePageSource(false)
                        .includeSelenideSteps(false)
        );
    }
}
