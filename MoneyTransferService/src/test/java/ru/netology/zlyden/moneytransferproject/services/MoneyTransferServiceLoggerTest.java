package ru.netology.zlyden.moneytransferproject.services;

import org.junit.jupiter.api.Test;

public class MoneyTransferServiceLoggerTest {

    @Test
    public void logTest() {
        //arrange
        MoneyTransferServiceLogger moneyTransferServiceLogger = new MoneyTransferServiceLogger();
        moneyTransferServiceLogger.setLogFileName("./moneytransferservice.log");
        String logRecord = "Тестируем логгер";
        //act
        moneyTransferServiceLogger.log(logRecord);
        //accept
        //а тут мы просто открываем лог-файл и глядим чего написано
    }
}
