package com.example.logger;

import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

@UtilityClass
public class Logger {
        private static final String ANSI_RESET = "\u001B[0m";
        private static final String ANSI_RED = "\u001B[31m";
        private static final String ANSI_GREEN = "\u001B[32m";
        private static final String ANSI_CYAN = "\u001B[36m";
        private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Logger.class.getName());


    public void logDataStartUploadToRedis() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        log.info(ANSI_GREEN + "Data upload started at - " + formatter.format(date) + ANSI_RESET);
    }

    public void logDataStopUploadToRedis() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        log.info(ANSI_GREEN + "Data upload finished at - " + formatter.format(date) + ANSI_RESET);
    }

    public void logDataStartFlushDbToRedis() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        log.info(ANSI_GREEN + "DB flush started at - " + formatter.format(date) + ANSI_RESET);
    }

    public void logDataStopFlushDbToRedis() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        log.info(ANSI_GREEN + "DB flush finished at - " + formatter.format(date) + ANSI_RESET);
    }
}
