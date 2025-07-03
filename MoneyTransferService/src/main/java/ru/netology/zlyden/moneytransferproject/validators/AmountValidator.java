package ru.netology.zlyden.moneytransferproject.validators;

import org.springframework.stereotype.Component;

@Component
public class AmountValidator {
    public boolean checkCurrency(String currency){
        if ("RUR".equals(currency)){
            return true;
        }
        return false;
    }

    public boolean checkValue(int value){
        if (value > 0 && (value % 100 == 0)){
            return true;
        }
        return false;
    }
}
