package ru.netology.zlyden.moneytransferproject.validators;

import org.springframework.stereotype.Component;
import ru.netology.zlyden.moneytransferproject.models.ConfirmOperation;

@Component
public class ConfirmOperationValidator {

    //код подтверждения должен быть 4-6 цифр - обычно у банков так
    public boolean checkConfirmCode(String confirmCode) {
        if (confirmCode.length() < 4 || confirmCode.length() > 6) {
            return false;
        }
        //если содержатся нецифровые символы - возвращаем false
        try{
            int code = Integer.parseInt(confirmCode);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public boolean checkOperationId(String operationId) {
        //если operationId не прислали - возвращаем false
        if (operationId == null) {
            return false;
        }
        //если пустой или из пробелов - возвращаем false
        if (operationId.isEmpty() || operationId.isBlank()) {
            return false;
        }
        return true;
    }
}
