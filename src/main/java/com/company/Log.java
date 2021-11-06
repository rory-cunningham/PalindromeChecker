package com.company;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The Log class is used for basic Java Logging in order to aid
 * Support Teams to triage issues.
 *
 * @author  Rory Cunningham
 * @since   2021-11-01
 */

public class Log {
    public Logger logger;
    FileHandler fh;

    public Log(String file_name) throws SecurityException, IOException{
        File f = new File(file_name);
        if(!f.exists())
        {
            f.createNewFile();
        }

        fh = new FileHandler(file_name,true);
        logger = Logger.getLogger("test");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);
    }

}
