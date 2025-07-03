package ru.netology.zlyden.moneytransferproject.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardValidatorTest {
    CardValidator cardValidator = new CardValidator();

    @Test
    public void checkCardNumberTest() {
        //arrange
        String cardNumber1 = "4026843483168683"; //контрольная сумма ok
        String cardNumber2 = "27301684615161841"; //контрольная сумма bad
        String cardNumber3 = "2730168464ff1841"; //присутствуют буквы
        boolean result1, result2, result3;
        //act
        result1 = cardValidator.checkCardNumber(cardNumber1);
        result2 = cardValidator.checkCardNumber(cardNumber2);
        result3 = cardValidator.checkCardNumber(cardNumber3);
        //assert
        Assertions.assertTrue(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
    }

    @Test
    public void checkValidTillTest() {
        //arrange
        String cardFromValidTill1 = "12/24"; //устарел
        String cardFromValidTill2 = "12/30"; //норм
        String cardFromValidTill3 = "08/25"; //текущий год, месяц ок
        String cardFromValidTill4 = "05/25"; //текущий год, месяц bad
        String cardFromValidTill5 = "16/30"; //месяц кривой
        String cardFromValidTill6 = "01-30"; //не тот разделитель
        String cardFromValidTill7 = "011/098"; //слишком длинная строка
        boolean result1, result2, result3, result4, result5, result6, result7;
        //act
        result1 = cardValidator.checkValidTill(cardFromValidTill1);
        result2 = cardValidator.checkValidTill(cardFromValidTill2);
        result3 = cardValidator.checkValidTill(cardFromValidTill3);
        result4 = cardValidator.checkValidTill(cardFromValidTill4);
        result5 = cardValidator.checkValidTill(cardFromValidTill5);
        result6 = cardValidator.checkValidTill(cardFromValidTill6);
        result7 = cardValidator.checkValidTill(cardFromValidTill7);
        //assert
        Assertions.assertFalse(result1);
        Assertions.assertTrue(result2);
        Assertions.assertTrue(result3);
        Assertions.assertFalse(result4);
        Assertions.assertFalse(result5);
        Assertions.assertFalse(result6);
        Assertions.assertFalse(result7);
    }

    @Test
    public void checkCVVTest() {
        //arrange
        String cvv1 = "917"; //норм
        String cvv2 = "9170"; //не 3 цифры
        String cvv3 = "91"; //не 3 цифры
        String cvv4 = "9f7"; //есть не цифры
        boolean result1, result2, result3, result4;
        //act
        result1 = cardValidator.checkCVV(cvv1);
        result2 = cardValidator.checkCVV(cvv2);
        result3 = cardValidator.checkCVV(cvv3);
        result4 = cardValidator.checkCVV(cvv4);
        //assert
        Assertions.assertTrue(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
        Assertions.assertFalse(result4);
    }
}
