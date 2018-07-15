package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class WebdriverManager {

    private static WebDriver driver;

    private static final String PATH_TO_PROPERTIES = "properties/settings.properties";

    public static WebDriver getDriver() {
        String browserName =
                PropertyReader.getPropertyFromFile(PATH_TO_PROPERTIES, "browser");
        if (driver == null) {
            switch (browserName) {
                case "chrome":
                    createChromeDriver(false, false);
                    break;
                case "chrome-headless":
                    createChromeDriver(true, false);
                    break;
                case "chrome-logging":
                    createChromeDriver(false, true);
                    break;
            }
        }
        return driver;
    }

    private static WebDriver createChromeDriver(Boolean headless, Boolean logging){
        if (headless) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (logging) {
            DesiredCapabilities caps = new DesiredCapabilities();
            LoggingPreferences logs = new LoggingPreferences();
            logs.enable(LogType.PERFORMANCE, Level.INFO);
            caps.setCapability(CapabilityType.LOGGING_PREFS, logs);
            driver = new ChromeDriver(caps);
        } else {
            driver = new ChromeDriver();
        }
        return driver;
    }
}
