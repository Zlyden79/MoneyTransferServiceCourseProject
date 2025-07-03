package ru.netology.zlyden.moneytransferproject.validators;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CardValidator {

    //алгоритм Ханса Питера Луна для 16-значного cardNumber
    public boolean checkCardNumber(String cardNumber) {
        if (cardNumber.length() != 16) {
            return false;
        }
        int[] digitArray = new int[16];
        try {
            for (int i = 0; i < 16; i++) {
                digitArray[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
            }
            System.out.println();
        } catch (NumberFormatException nfe) {
            return false;
        }
        for (int i = 0; i < 16; i += 2) {
            digitArray[i] = (digitArray[i] * 2 < 9) ? (digitArray[i] * 2) : (digitArray[i] * 2 - 9);
        }
        int summa = 0;
        for (int i = 0; i < 16; i++) {
            summa += digitArray[i];
        }
        if (summa % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }

    //проверка срока действия карты вида "mm/yy"
    public boolean checkValidTill(String cardFromValidTill) {
        if (cardFromValidTill.length() != 5 || cardFromValidTill.charAt(2) !='/') {
            return false;
        }
        String[] parts = cardFromValidTill.split("/");
        int month, year, currentYear, currentMonth;
        LocalDate today = LocalDate.now();
        currentMonth = today.getMonthValue();
        currentYear = today.getYear();
        try {
            month = Integer.parseInt(parts[0]);
            year = Integer.parseInt(parts[1]) + 2000;
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (month > 12 || month < 1) {
            return false;
        } else if (year < currentYear) {
            return false;
        } else if (year > currentYear) {
            return true;
        } else if (month >= currentMonth) {
            return true;
        }
        return false;
    }

    //проверка CVV кода на 3 цифры
    public boolean checkCVV(String cvv) {
        if (cvv.length() != 3) {
            return false;
        }
        //если содержатся нецифровые символы - возвращаем false
        try {
            int code =  Integer.parseInt(cvv);
        } catch  (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
