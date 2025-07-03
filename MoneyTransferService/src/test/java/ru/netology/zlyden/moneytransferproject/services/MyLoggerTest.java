package ru.netology.zlyden.moneytransferproject.services;

import org.junit.jupiter.api.Test;

public class MyLoggerTest {

    @Test
    public void logTest() {
        //arrange
        MyLogger myLogger = new MyLogger();
        myLogger.setLogFileName("./moneytransferservice.log");
        String logRecord = "Тестируем логгер";
        //act
        myLogger.log(logRecord);
        //accept
        //а тут мы просто открываем лог-файл и глядим чего написано
    }
}
