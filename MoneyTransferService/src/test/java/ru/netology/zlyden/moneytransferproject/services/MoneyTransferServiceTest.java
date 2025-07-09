package ru.netology.zlyden.moneytransferproject.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.netology.zlyden.moneytransferproject.exceptions.ParametersValidationException;
import ru.netology.zlyden.moneytransferproject.exceptions.ExternalServiceException;
import ru.netology.zlyden.moneytransferproject.models.Amount;
import ru.netology.zlyden.moneytransferproject.models.GoodResponse;
import ru.netology.zlyden.moneytransferproject.models.MoneyTransfer;
import ru.netology.zlyden.moneytransferproject.validators.AmountValidator;
import ru.netology.zlyden.moneytransferproject.validators.CardValidator;

public class MoneyTransferServiceTest {
    MoneyTransfer moneyTransfer;
    @Mock
    MoneyTransferServiceLogger mockMoneyTransferServiceLogger;
    @Mock
    CardValidator cardValidator;
    @Mock
    AmountValidator amountValidator;
    @InjectMocks
    MoneyTransferService moneyTransferService;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        this.moneyTransfer = new MoneyTransfer(
                "1111222233334444",
                "9999555544446666",
                "666",
                "06/29",
                new Amount("RUR", 1256900));
    }

    @Test
    public void transferHandlerTestException400() {
        //arrange

        //заглушки возвращают false на все методы (хотя достаточно одного false с любого метода)
        Mockito.when(cardValidator.checkCardNumber(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(cardValidator.checkValidTill(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(cardValidator.checkCVV(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(amountValidator.checkCurrency(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(amountValidator.checkValue(Mockito.anyInt()))
                .thenReturn(false);

        //act + assert
        Assertions.assertThrows(ParametersValidationException.class, () -> {
            moneyTransferService.transferHandler(moneyTransfer);
        });
    }

    @Test
    public void transferHandlerTestException500() {
        //arrange

        //Заглушки возвращают true на все методы
        Mockito.when(cardValidator.checkCardNumber(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(cardValidator.checkValidTill(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(cardValidator.checkCVV(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(amountValidator.checkCurrency(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(amountValidator.checkValue(Mockito.anyInt()))
                .thenReturn(true);

        //act

        //логика метода с вероятностью 1:5 должна выбрасывать исключение,
        // поэтому за 10 вызовов скорее всего оно хоть один раз да выбросится
        //если выбросится раньше - выходим из цикла
        boolean isExternalServiceException = false;
        for (int i = 0; i < 10; i++) {
            try {
                moneyTransferService.transferHandler(moneyTransfer);
            } catch (ExternalServiceException e) {
                isExternalServiceException = true;
                break;
            }
        }

        //assert
        Assertions.assertTrue(isExternalServiceException);
    }

    @Test
    public void transferHandler() {
        //arrange

        //Заглушки возвращают true на все методы
        Mockito.when(cardValidator.checkCardNumber(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(cardValidator.checkValidTill(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(cardValidator.checkCVV(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(amountValidator.checkCurrency(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(amountValidator.checkValue(Mockito.anyInt()))
                .thenReturn(true);

        //act

        //логика метода с вероятностью 4:5 НЕ должна выбрасывать исключение.
        //Если за 3 итерации не выпал нормальный ответ => падение теста
        GoodResponse goodResponse = null;
        for (int i = 0; i < 3; i++) {
            try {
                goodResponse = moneyTransferService.transferHandler(moneyTransfer);
            } catch (ExternalServiceException e) {
                continue;
            }
            if (goodResponse != null) break;
        }

        //assert
        Assertions.assertNotNull(goodResponse);
    }
}

