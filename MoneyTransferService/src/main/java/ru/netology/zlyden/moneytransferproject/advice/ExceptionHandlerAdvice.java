package ru.netology.zlyden.moneytransferproject.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.zlyden.moneytransferproject.exceptions.ParametersValidationException;
import ru.netology.zlyden.moneytransferproject.exceptions.ExternalServiceException;
import ru.netology.zlyden.moneytransferproject.models.BadResponse;
import ru.netology.zlyden.moneytransferproject.services.MoneyTransferServiceLogger;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private final MoneyTransferServiceLogger moneyTransferServiceLogger;

    @Autowired
    public ExceptionHandlerAdvice(MoneyTransferServiceLogger moneyTransferServiceLogger) {
        this.moneyTransferServiceLogger = moneyTransferServiceLogger;
    }

    @ExceptionHandler(ParametersValidationException.class)
    public ResponseEntity<BadResponse> exception400Handler(ParametersValidationException e) {
        BadResponse badResponse = new BadResponse(e.getMessage(), 2);
        StringBuilder sb = new StringBuilder();
        sb.append("RESPONSE: ");
        sb.append(HttpStatus.BAD_REQUEST.toString() + " ");
        sb.append(badResponse.toString());
        moneyTransferServiceLogger.log(sb.toString());
        return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<BadResponse> exception500Handler(ExternalServiceException e) {
        BadResponse badResponse = new BadResponse(e.getMessage(), 1);
        StringBuilder sb = new StringBuilder();
        sb.append("RESPONSE: ");
        sb.append(HttpStatus.INTERNAL_SERVER_ERROR.toString() + " ");
        sb.append(badResponse.toString());
        moneyTransferServiceLogger.log(sb.toString());
        return new ResponseEntity<>(badResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}