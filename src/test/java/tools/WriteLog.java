package tools;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static utils.WebdriverManager.getDriver;

public class WriteLog {

    private static final String PATH_TO_LOG = "target/output/";

    public void driverLog(){

        DateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy.HH.mm.ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = dateFormat.format(today);

        LogEntries logEntries = getDriver().manage().logs().get(LogType.PERFORMANCE);

        File file = new File(PATH_TO_LOG + "log" + reportDate + ".txt");
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
}
