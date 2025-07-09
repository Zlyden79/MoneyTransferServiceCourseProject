package ru.netology.zlyden.moneytransferproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.netology.zlyden.moneytransferproject.exceptions.*;
import ru.netology.zlyden.moneytransferproject.models.ConfirmOperation;
import ru.netology.zlyden.moneytransferproject.models.GoodResponse;
import ru.netology.zlyden.moneytransferproject.validators.ConfirmOperationValidator;

import java.util.Random;

@Service
public class ConfirmOperationService {
    private final ConfirmOperationValidator confirmOperationValidator;
    private final MoneyTransferServiceLogger moneyTransferServiceLogger;

    @Autowired
    public ConfirmOperationService(ConfirmOperationValidator confirmOperationValidator, MoneyTransferServiceLogger moneyTransferServiceLogger) {
        this.confirmOperationValidator = confirmOperationValidator;
        this.moneyTransferServiceLogger = moneyTransferServiceLogger;
    }

    public GoodResponse confirmOperationHandler(ConfirmOperation confirmOperation) {
        if (!confirmOperationValidator.checkConfirmCode(confirmOperation.getCode())){
            throw new ParametersValidationException("Invalid confirmCode: " + confirmOperation.getCode());
        }

        if (!confirmOperationValidator.checkOperationId(confirmOperation.getOperationId())){
            throw new ParametersValidationException("Invalid operationId: " + confirmOperation.getOperationId());
        }

        //имитация запроса к банку - с вероятностью 1:5 отказ/успех
        Random random = new Random();
        int rnd = random.nextInt(0, 5);
        if (rnd == 0) {
            //отказ
            throw new ExternalServiceException("Confirmation failed. operationId: " + confirmOperation.getOperationId());
        } else {
            //успех
            GoodResponse goodResponse = new GoodResponse(confirmOperation.getOperationId());
            StringBuilder sb = new StringBuilder();
            sb.append("RESPONSE: ");
            sb.append(HttpStatus.OK.toString() + " ");
            sb.append(goodResponse.toString());
            moneyTransferServiceLogger.log(sb.toString());
            return goodResponse;
        }
    }
}
