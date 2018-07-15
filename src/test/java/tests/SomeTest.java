package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SomeTest extends BaseTest {

    @Test
    public void test(){
        driver.get("http://blazedemo.com/index.php");
        WebElement selectFrom = driver.findElement(By.name("fromPort"));
        Select select = new Select(selectFrom);
        select.selectByValue("Philadelphia");

        WebElement selectTo = driver.findElement(By.name("toPort"));
        Select selectNext = new Select(selectTo);
        selectNext.selectByValue("Berlin");
        driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        WebElement chooseFlight = driver.findElement(By.cssSelector("input.btn.btn-small"));

        //waiting for next page loads
        wait.until(ExpectedConditions.titleIs("BlazeDemo - reserve"));
        wait.until(ExpectedConditions.textToBePresentInElementValue(chooseFlight, "Choose This Flight"));
        assertFalse(chooseFlight.isDisplayed());
    }
}
