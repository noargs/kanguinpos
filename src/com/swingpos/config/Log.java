package com.swingpos.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

    public Logger logger;
    public FileHandler fh;

    public Log(String file_name) throws SecurityException, IOException
    {
        String file = Helper.getAppPath() + "/" + AppProperties.getDevDir() + "/" + file_name;
        File f = new File(file);
        if(!f.exists())
        {
            f.createNewFile();
        }

        fh = new FileHandler( file, true);
        logger = Logger.getLogger("kanguinPOS");
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);

    }

    public void logError(String error)
    {
        logger.setLevel(Level.WARNING);

        logger.warning(error);
    }

}
