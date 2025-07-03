package ru.netology.zlyden.moneytransferproject.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class MyLogger {
    @Value(value = "${servicelog.filename}")
    private String logFileName;

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void log(String logString) {

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss:SSS");
        String dateTimeStamp ="[" + dateTime.format(formatter) + "] ";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFileName, true))) {
            bufferedWriter.write(dateTimeStamp + logString + "\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
