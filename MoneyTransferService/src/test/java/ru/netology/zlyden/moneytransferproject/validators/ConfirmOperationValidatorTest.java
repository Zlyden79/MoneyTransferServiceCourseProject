package ru.netology.zlyden.moneytransferproject.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.zlyden.moneytransferproject.models.ConfirmOperation;

public class ConfirmOperationValidatorTest {
    ConfirmOperationValidator confirmOperationValidator = new ConfirmOperationValidator();

    @Test
    public void checkConfirmCodeTest() {
        //arrange
        String co1 = ""; //пустой operationId
        String co2 = "   "; //operationId из одних пробелов
        String co3 = null;  //вообще без operationId
        String co4 = "sdkfj"; //норм operationId
        boolean result1, result2, result3, result4;
        //act
        result1 = confirmOperationValidator.checkOperationId(co1);
        result2 = confirmOperationValidator.checkOperationId(co2);
        result3 = confirmOperationValidator.checkOperationId(co3);
        result4 = confirmOperationValidator.checkOperationId(co4);

        //assert
        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
        Assertions.assertTrue(result4);
    }
    @Test
    public void checkOperationIdTest() {
        //arrange
        String co1 = "6157"; //норм code
        String co2 = "615"; //код меньше 4 символов
        String co3 = "6198557"; //код больше 6 символов
        String co4 = "61gd5"; //нецифровые символы
        boolean result1, result2, result3, result4;
        //act
        result1 = confirmOperationValidator.checkConfirmCode(co1);
        result2 = confirmOperationValidator.checkConfirmCode(co2);
        result3 = confirmOperationValidator.checkConfirmCode(co3);
        result4 = confirmOperationValidator.checkConfirmCode(co4);
        //assert
        Assertions.assertTrue(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
        Assertions.assertFalse(result4);
    }
}
