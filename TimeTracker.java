import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

/**
 * Class TimeTracker is used for tracking time and managing time entries.
 */
public class TimeTracker {
    private LocalDateTime startTimeDate;
    private LocalDateTime endTimeDate;
    private int MAX_DAILY_ENTRIES = 2;
    private int entryCount = 0;

    /**
     * Default constructor for TimeTracker.
     */
    public TimeTracker() {
    }

    /**
     * Returns the start time.
     * 
     * @return LocalDateTime representing start time.
     */
    public LocalDateTime getStartTime() {
        return startTimeDate;
    }

    /**
     * Returns the end time.
     * 
     * @return LocalDateTime representing end time.
     */
    public LocalDateTime getEndTime() {
        return endTimeDate;
    }

    /**
     * Returns the maximum number of daily entries.
     * 
     * @return int representing max daily entries.
     */
    public int getMaxDailyEntries() {
        return MAX_DAILY_ENTRIES;
    }

    /**
     * Returns the current entry count.
     * 
     * @return int representing current entry count.
     */
    public int getEntryCount() {
        return entryCount;
    }

    /**
     * Sets the start time to the current time.
     */
    public void setStartTime(LocalDateTime starTimeDate) {
        this.startTimeDate = java.time.LocalDateTime.now();
    }

    /**
     * Sets the end time to the current time.
     */
    public void setEndTime(LocalDateTime endTimeDate) {
        this.endTimeDate = java.time.LocalDateTime.now();
    }

    /**
     * Sets the entry count.
     * 
     * @param entryCount The number of entries.
     */
    public void setEntryCount(int entryCount) {
        this.entryCount = entryCount;
    }

    /**
     * Converts the given LocalDateTime into a time format (HH:mm:ss).
     * 
     * @param now The LocalDateTime to be formatted.
     * @return String representing formatted time.
     */
    public String timeFormatConverter(LocalDateTime now) {
        DateTimeFormatter regularTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(regularTimeFormatter);
    }

    /**
     * Converts the given LocalDateTime into a date format (MM/dd/yyyy).
     * 
     * @param now The LocalDateTime to be formatted.
     * @return String representing formatted date.
     */
    public String dateFormatConverter(LocalDateTime now) {
        DateTimeFormatter regularDayFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return now.format(regularDayFormatter);
    }

    /**
     * Creates a new timesheet file if it does not exist.
     */
    public void createTimeSheet() {
        File file = new File("/Users/aniziocp/Desktop/FProject/timesheet.txt");
        try {
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("The new file is created!");
            } else {
                System.out.println("The file already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the time sheet data into a file.
     * 
     * @param content_date LocalDateTime data to be written into the file.
     */
    public void writeTimeSeet(LocalDateTime content_date) {
        try {
            FileWriter write_text_obj = new FileWriter("timesheet.txt");
            write_text_obj.write(content_date.toString());
            write_text_obj.flush();
            write_text_obj.close();
            System.out.println("Data is written to the file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finds the difference between two dates in seconds.
     * 
     * @param start_date String representing the start date in ISO format.
     * @param end_date   String representing the end date in ISO format.
     * @return String representing the difference in seconds or "Error" if an exception occurs.
     */
    public String findDifference(String start_date, String end_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        try {
            LocalDateTime d1 = LocalDateTime.parse(start_date, formatter);
            LocalDateTime d2 = LocalDateTime.parse(end_date, formatter);
            Duration duration = Duration.between(d1, d2);
            long difference_In_Seconds = duration.toSeconds();
            return String.valueOf(difference_In_Seconds);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

}
