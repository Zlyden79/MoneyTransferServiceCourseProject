package ru.netology.zlyden.moneytransferproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.zlyden.moneytransferproject.models.ConfirmOperation;
import ru.netology.zlyden.moneytransferproject.models.GoodResponse;
import ru.netology.zlyden.moneytransferproject.services.ConfirmOperationService;
import ru.netology.zlyden.moneytransferproject.services.MoneyTransferServiceLogger;


@RestController
public class ConfirmOperationController {
    private final ConfirmOperationService confirmOperationService;
    private final MoneyTransferServiceLogger moneyTransferServiceLogger;

    @Autowired
    public ConfirmOperationController(ConfirmOperationService confirmOperationService, MoneyTransferServiceLogger moneyTransferServiceLogger) {
        this.confirmOperationService = confirmOperationService;
        this.moneyTransferServiceLogger = moneyTransferServiceLogger;
    }

    @PostMapping("/confirmOperation")
    public GoodResponse confirmOperation(@RequestBody ConfirmOperation  confirmOperation) {
        StringBuilder sb = new StringBuilder();
        sb.append("REQUEST : POST /confirmOperation ");
        sb.append(confirmOperation.toString());
        moneyTransferServiceLogger.log(sb.toString());
        return confirmOperationService.confirmOperationHandler(confirmOperation);
    }
}
