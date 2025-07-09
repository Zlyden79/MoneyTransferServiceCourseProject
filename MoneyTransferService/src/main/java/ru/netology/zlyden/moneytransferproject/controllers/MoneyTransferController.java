package ru.netology.zlyden.moneytransferproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.zlyden.moneytransferproject.models.GoodResponse;
import ru.netology.zlyden.moneytransferproject.models.MoneyTransfer;
import ru.netology.zlyden.moneytransferproject.services.MoneyTransferService;
import ru.netology.zlyden.moneytransferproject.services.MoneyTransferServiceLogger;

@RestController
public class MoneyTransferController {
    private final MoneyTransferService moneyTransferService;
    private final MoneyTransferServiceLogger moneyTransferServiceLogger;

    @Autowired
    public MoneyTransferController(MoneyTransferService moneyTransferService, MoneyTransferServiceLogger moneyTransferServiceLogger) {
        this.moneyTransferService = moneyTransferService;
        this.moneyTransferServiceLogger = moneyTransferServiceLogger;
    }

    @PostMapping("/transfer")
    public GoodResponse transferMoney(@RequestBody /*@Valid*/ MoneyTransfer moneyTransfer) {
        StringBuilder sb = new StringBuilder();
        sb.append("REQUEST : POST /transfer ");
        sb.append(moneyTransfer.toString());
        moneyTransferServiceLogger.log(sb.toString());
        return moneyTransferService.transferHandler(moneyTransfer);
    }
}