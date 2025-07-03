package ru.netology.zlyden.moneytransferproject.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.netology.zlyden.moneytransferproject.exceptions.Exception400;
import ru.netology.zlyden.moneytransferproject.exceptions.Exception500;
import ru.netology.zlyden.moneytransferproject.models.ConfirmOperation;
import ru.netology.zlyden.moneytransferproject.models.GoodResponse;
import ru.netology.zlyden.moneytransferproject.validators.ConfirmOperationValidator;

public class ConfirmOperationServiceTest {
    @Mock
    MyLogger myLogger;
    @Mock
    ConfirmOperationValidator ConfirmOperationValidator;
    @InjectMocks
    ConfirmOperationService confirmOperationService;
    ConfirmOperation confirmOperation;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        this.confirmOperation = new ConfirmOperation("someId", "123456");
    }

    @Test
    public void getResponseTestException400() {
        //arrange

        //пусть заглушки возвращают false на оба метода (хотя достаточно одного false с любого метода)
        Mockito.when(ConfirmOperationValidator.checkOperationId(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(ConfirmOperationValidator.checkConfirmCode(Mockito.anyString()))
                .thenReturn(false);

        //act + assert
        Assertions.assertThrows(Exception400.class, () -> {
            confirmOperationService.getResponse(confirmOperation);
        });
    }

    @Test
    public void getResponseTestException500() {
        //arrange

        //заглушки возвращают true на оба метода
        Mockito.when(ConfirmOperationValidator.checkOperationId(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(ConfirmOperationValidator.checkConfirmCode(Mockito.anyString()))
                .thenReturn(true);
        //act

        //логика метода с вероятностью 1:5 должна выбрасывать исключение,
        // поэтому за 10 вызовов скорее всего оно хоть один раз да выбросится
        //если выбросится раньше - выходим из цикла
        boolean isExceprion500 = false;
        for (int i = 0; i < 10; i++) {
            try {
                confirmOperationService.getResponse(confirmOperation);
            } catch (Exception500 e) {
                isExceprion500 = true;
                break;
            }
        }

        //assert
        Assertions.assertTrue(isExceprion500);
    }

    @Test
    public void getResponseTestGoodResponse() {
        //arrange

        //заглушки возвращают true на оба метода
        Mockito.when(ConfirmOperationValidator.checkOperationId(Mockito.anyString()))
                .thenReturn(true);
        Mockito.when(ConfirmOperationValidator.checkConfirmCode(Mockito.anyString()))
                .thenReturn(true);
        //act

        //логика метода с вероятностью 4:5 НЕ должна выбрасывать исключение.
        //Если за 3 итерации не выпал нормальный ответ => падение теста
        GoodResponse goodResponse = null;
        for (int i=0; i<3; i++){
            try {
                goodResponse = confirmOperationService.getResponse(confirmOperation);
            } catch (Exception500 e) {
                continue;
            }
            if (goodResponse != null) break;
        }

        //assert
        Assertions.assertNotNull(goodResponse);
    }
}
