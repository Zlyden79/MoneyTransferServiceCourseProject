package ru.netology.zlyden.moneytransferproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.netology.zlyden.moneytransferproject.exceptions.*;
import ru.netology.zlyden.moneytransferproject.models.GoodResponse;
import ru.netology.zlyden.moneytransferproject.models.MoneyTransfer;
import ru.netology.zlyden.moneytransferproject.validators.AmountValidator;
import ru.netology.zlyden.moneytransferproject.validators.CardValidator;

import java.util.Random;
import java.util.UUID;

@Service
public class MoneyTransferService {
    private final CardValidator сardValidator;
    private final AmountValidator amountValidator;
    private final MoneyTransferServiceLogger moneyTransferServiceLogger;

    @Autowired
    public MoneyTransferService(CardValidator сardValidator, AmountValidator amountValidator, MoneyTransferServiceLogger moneyTransferServiceLogger) {
        this.сardValidator = сardValidator;
        this.amountValidator = amountValidator;
        this.moneyTransferServiceLogger = moneyTransferServiceLogger;
    }

    public GoodResponse transferHandler(MoneyTransfer moneyTransfer) {
        //сначала гоним проверки и швыряемся исключениями в случае неудачи
        if (!сardValidator.checkCardNumber(moneyTransfer.getCardFromNumber())) {
            throw new ParametersValidationException("Invalid cardFrom number: " + moneyTransfer.getCardFromNumber());
        }
        if (!сardValidator.checkCardNumber(moneyTransfer.getCardToNumber())) {
            throw new ParametersValidationException("Invalid cardTo number: " + moneyTransfer.getCardToNumber());
        }
        if (moneyTransfer.getCardFromNumber().equals(moneyTransfer.getCardToNumber())){
            throw new ParametersValidationException("Invalid operation: cardTo " + moneyTransfer.getCardToNumber() +
                    " equals cardFrom " + moneyTransfer.getCardFromNumber());
        }
        if (!сardValidator.checkValidTill(moneyTransfer.getCardFromValidTill())) {
            throw new ParametersValidationException("Invalid cardFromValidTill: " + moneyTransfer.getCardFromValidTill());
        }
        if (!сardValidator.checkCVV(moneyTransfer.getCardFromCVV())) {
            throw new ParametersValidationException("Invalid cardFromCVV: " + moneyTransfer.getCardFromCVV());
        }
        if (!amountValidator.checkCurrency(moneyTransfer.getAmount().getCurrency())) {
            throw new ParametersValidationException("Invalid amount - currency: " + moneyTransfer.getAmount().getCurrency());
        }
        if (!amountValidator.checkValue(moneyTransfer.getAmount().getValue())) {
            throw new ParametersValidationException("Invalid amount - value: " + moneyTransfer.getAmount().getValue());
        }
        //затем бизнес-логика
        //имитация запроса к банку - с вероятностью 1:5 отказ/успех
        Random random = new Random();
        int rnd = random.nextInt(0, 5);
        if (rnd == 0) {
            //отказ
            throw new ExternalServiceException("Money transfer failed: " + moneyTransfer.toString());
        } else {
            //успех
            UUID uuid = UUID.randomUUID();
            GoodResponse goodResponse = new GoodResponse(uuid.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("RESPONSE: ");
            sb.append(HttpStatus.OK.toString() + " ");
            sb.append(goodResponse.toString());
            moneyTransferServiceLogger.log(sb.toString());
            return goodResponse;
        }
    }
}
