package com.uscboard.dashboard.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoggingService {
    Logger logger = LoggerFactory.getLogger(LoggingService.class);

    public void loggingError(String message){
        logger.error(message);
    }
    public void loggingWarn(String message){
        logger.warn(message);
    }
    public void loggingInfo(String message){
        logger.info(message);
    }
    public void loggingDebug(String message){
        logger.debug(message);
    }
    public void loggingTrace(String message){
        logger.trace(message);
    }


    
}
