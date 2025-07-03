package ru.netology.zlyden.moneytransferproject.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.zlyden.moneytransferproject.models.ConfirmOperation;
import ru.netology.zlyden.moneytransferproject.models.GoodResponse;
import ru.netology.zlyden.moneytransferproject.services.ConfirmOperationService;
import ru.netology.zlyden.moneytransferproject.services.MyLogger;


@RestController
public class ConfirmOperationController {
    private final ConfirmOperationService confirmOperationService;
    private final MyLogger myLogger;

    @Autowired
    public ConfirmOperationController(ConfirmOperationService confirmOperationService, MyLogger myLogger) {
        this.confirmOperationService = confirmOperationService;
        this.myLogger = myLogger;
    }

    @PostMapping("/confirmOperation")
    public GoodResponse confirmOperation(@RequestBody /*@Valid*/ ConfirmOperation  confirmOperation) {
        StringBuilder sb = new StringBuilder();
        sb.append("REQUEST : POST /confirmOperation ");
        sb.append(confirmOperation.toString());
        myLogger.log(sb.toString());
        return confirmOperationService.getResponse(confirmOperation);
    }
}
