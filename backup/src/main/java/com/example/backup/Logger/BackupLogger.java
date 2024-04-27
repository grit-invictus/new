package com.example.backup.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BackupLogger {

    private static final Logger logger = LogManager.getLogger(BackupLogger.class.getName());
    private static BackupLogger instance =null;
    private BackupLogger()
    {}

    public static BackupLogger getInstance() {
        if (instance == null) {
            instance = new BackupLogger();

        }
        return instance;
    }

    public void loginfo(String message)
    {
        logger.info(message);
    }
    public void logerror(String message)
    {
        logger.error(message);
    }
    public void loggerwarn(String message)
    {
        logger.warn(message);
    }



}
