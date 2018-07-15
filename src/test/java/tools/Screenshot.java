package tools;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static utils.WebdriverManager.getDriver;

public class Screenshot {

    private static final String PATH_TO_SCRENSHOT = "target/output/";

    public void makeScreenshot()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = dateFormat.format(today);

        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(PATH_TO_SCRENSHOT + "screenshot." + reportDate + ".png"));
        } catch (IOException e) {}
    }
}
