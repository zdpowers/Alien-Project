package com.ccsu.designpatterns.fall23.alieninvasionsim.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


import android.content.Context;

/**
 * This class is a singleton that is used to log events that occur in the AlienInvasion Sim
 * @author Zack Powers
 * @version 1.0
 * @since 2023-26-10
 */

public class EventLogger {

    private static EventLogger instance;
    private final File eventFile;
    // memory leak issue if class holds onto context instance because not garbage collected
    // private final Context context;

    /**
     * Since this is a singleton, to control access to the EventLogger, the constructor is private
     * the public method "getInstance" is required to get an instance
     * @param context the application context must be passed for system file access
     * @author Zack Powers
     * @since 2023-26-10
     */
    private EventLogger(Context context) {
        //this.context = context;
        eventFile = new File(context.getFilesDir(), "AISimEvents");
        if(!eventFile.exists()) {
            try {
                eventFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create file.");
            }
        }
    }

    /**
     * Since the constructor method is private, this method must be used to get an instance of the EventLogger
     * @param context the application context must be passed for system file access
     * @return the single instance of the event logger is returned from this method
     * @author Zack Powers
     * @since 2023-26-10
     */
    public static EventLogger getInstance(Context context) {
        if (instance == null) {
            instance = new EventLogger(context);
        }
        return instance;
    }

    /**
     * This method is used to write an event to the EventLogger file
     * @param context the application context must be passed for system file access
     * @param event a string representation of the event that is written to the file
     * @author Zack Powers
     * @since 2023-26-10
     */
    public void logEvent(Context context, String event) {
        // append newline to end of event
        event += "\n";
        // Try opening the file to be read
        try (FileOutputStream fos = context.openFileOutput(eventFile.getName(), Context.MODE_APPEND)) {
            fos.write(event.getBytes(StandardCharsets.UTF_8));
            fos.close(); // Close the FileOutputStream
        } catch (FileNotFoundException e) {
            //throw new RuntimeException(e);
            System.out.println("File not found when trying to write to EventLog");
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("File output stream error when trying to write to EventLog.");
        }
    }

    /**
     * This method is used to return the full contents of the event log file
     * @param context the application context must be passed for system file access
     * @return a string representation of the event log file is returned by this method
     * @author Zack Powers
     * @since 2023-26-10
     */
    public String getLogs(Context context) {
        String contents = "";
        // Try opening the file to be read
        try {
            FileInputStream fis  = context.openFileInput(eventFile.getName());
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            // Try reading the file
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while(line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {
                System.out.println("Error occurred when opening raw event file for reading");
            } finally {
                contents = stringBuilder.toString();
            }
            fis.close(); // Close th FileInputStream
        } catch (IOException e) {
            System.out.println("Event log not found when attempting to read.");
        }
        return contents;
    }

}
