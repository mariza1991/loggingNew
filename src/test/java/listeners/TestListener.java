package listeners;

import org.apache.log4j.Logger;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tools.Screenshot;

import java.io.*;
import java.util.Date;

import static utils.WebdriverManager.getDriver;

public class TestListener implements ITestListener {

    private Logger logger = Logger.getLogger(TestListener.class);

    public void onTestStart(ITestResult iTestResult) {
        logger.info("Test starts...");
    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult result) {

        logger.warn("FAIL");
        Date failedTime = new Date(result.getEndMillis());
        logger.error("" + failedTime + result.getTestClass() + result.getTestName());
        StringWriter sb = new StringWriter();
        PrintWriter pw = new PrintWriter(sb);
        Throwable cause = result.getThrowable();
        cause.printStackTrace();
        logger.error(sb.getBuffer().toString());

        Screenshot screenshot = new Screenshot();
        screenshot.makeScreenshot();

        LogEntries logEntries = getDriver().manage().logs().get(LogType.PERFORMANCE);

        File file = new File("target/output/log.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(LogEntry entry : logEntries) {
            try {
                writer.write(entry.getLevel() + " " + entry.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {

    }
}
