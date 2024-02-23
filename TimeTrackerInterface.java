import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * The TimeTrackerInterface class provides a graphical user interface to track start and end times.
 * It allows a user to record two time entries per day and displays the duration between these times.
 */
public class TimeTrackerInterface {
    // Maximum number of entries that can be recorded
    private static int MAX_ENTRIES = 2;

    // Array to store the datetime entries
    private static LocalDateTime[] dateTimeArray = new LocalDateTime[MAX_ENTRIES];

    /**
     * Main method to set up and display the GUI.
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Create an instance of TimeTracker for time tracking logic
        TimeTracker tracker = new TimeTracker();

        // Setup the main frame of the application
        JFrame frame = new JFrame("ClockInCube");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);

        // Create a panel at the bottom of the frame and add components
        JPanel panel = new JPanel();
        JButton button = new JButton("Get Current DateTime");

        // TextArea in the center of the frame to display time entries and messages
        JTextArea textArea = new JTextArea();
        
        // Add the button to the panel and the panel to the frame
        panel.add(button);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, textArea);

        // Add ActionListener to the button to handle clicks
        button.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             * @param e ActionEvent object
             */
            public void actionPerformed(ActionEvent e) {
                tracker.createTimeSheet();
                LocalDateTime now = LocalDateTime.now();

                // Process the first click to set the start time
                if (tracker.getEntryCount() == 1) {
                    tracker.setStartTime(now);
                }
                // Process the second click to set the end time and display date
                else if (tracker.getEntryCount() == 2) {
                    tracker.setEndTime(now);
                    textArea.append("Date : "+ tracker.dateFormatConverter(now).toString() + "\n");
                }

                // Handle additional entries and display time information
                if (tracker.getEntryCount() < tracker.getMaxDailyEntries()) {
                    dateTimeArray[tracker.getEntryCount()] = now;
                    tracker.setEntryCount(tracker.getEntryCount() + 1);
                    textArea.append(tracker.getEntryCount() + " Time Retrieved: " + tracker.timeFormatConverter(now).toString() + "\n");
                    textArea.append("\n");
                } else {
                    // Display message when maximum entries are reached and show duration
                    textArea.append("\n");
                    textArea.append("Maximum daily entries reached.\n");
                    textArea.append("\n");
                    textArea.append("Congratulations, you worked for: " + tracker.findDifference(tracker.getStartTime().toString(),
                    tracker.getEndTime().toString()) + "\n");
                    textArea.append("\n");

                    // Write time entries to a file or another storage
                    tracker.writeTimeSeet(tracker.getStartTime());
                    tracker.writeTimeSeet(tracker.getEndTime());
                }
            }
        });

        // Make the frame visible
        frame.setVisible(true);
    }
}


