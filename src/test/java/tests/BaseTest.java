package tests;

import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utils.WebdriverManager;

@Listeners(TestListener.class)
public class BaseTest {

    public WebDriver driver;
    public WebDriverWait wait;
    int timeout = 30;

    @BeforeClass(alwaysRun = true)
    public void beforeTest(){
        driver = WebdriverManager.getDriver();
        wait = new WebDriverWait(driver, timeout);
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
        driver = null;
    }
}
