package ru.netology.zlyden.moneytransferproject.validators;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AmountValidatorTest {
    AmountValidator amountValidator = new AmountValidator();

    @Test
    public void checkCurrencyTest() {
        //arrange
        String a1 = "RUB";
        String a2 = "RUR";
        boolean result1, result2;
        // act
        result1 = amountValidator.checkCurrency(a1);
        result2 = amountValidator.checkCurrency(a2);
        // assert
        Assertions.assertFalse(result1);
        Assertions.assertTrue(result2);
    }

    @Test
    public void checkValueTest() {
        //arrange
        int a1 = 1234500; //норм - на 00 заканчивается
        int a2 = 12345; // не норм, фронд-энд рожает с приписанными 00 в конце
        int a3 = -1234500; // отрицательная сумма
        boolean result1, result2, result3;
        // act
        result1 = amountValidator.checkValue(a1);
        result2 = amountValidator.checkValue(a2);
        result3 = amountValidator.checkValue(a3);

        // assert
        Assertions.assertTrue(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);

    }
}
