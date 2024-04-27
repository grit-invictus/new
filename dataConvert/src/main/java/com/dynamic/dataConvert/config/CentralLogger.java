package com.dynamic.dataConvert.config;

import com.dynamic.dataConvert.DataConvertApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CentralLogger {

    private static CentralLogger logger_instance = null;
    public static final Logger logger = LogManager.getLogger(DataConvertApplication.class.getName());

    private CentralLogger()
    {}
    public synchronized static CentralLogger getInstance()
    {
        if(logger_instance==null)
        {
            logger_instance= new CentralLogger();
        }
        return logger_instance;
    }

   public void logInfo(String message)
   {
       logger.info(message);
   }
   public void logwarn(String message)
   {
       logger.warn(message);

   }
   public void logerror(String message)
   {
       logger.error(message);
   }


}
