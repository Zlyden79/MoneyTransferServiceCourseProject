package ru.netology.zlyden.moneytransferproject.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.zlyden.moneytransferproject.exceptions.Exception400;
import ru.netology.zlyden.moneytransferproject.exceptions.Exception500;
import ru.netology.zlyden.moneytransferproject.models.BadResponse;
import ru.netology.zlyden.moneytransferproject.services.MyLogger;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private final MyLogger myLogger;

    @Autowired
    public ExceptionHandlerAdvice(MyLogger myLogger) {
        this.myLogger = myLogger;
    }

    //HttpStatus.BAD_REQUEST              400
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<BadResponse> exception400Handler(Exception400 e) {
        BadResponse badResponse = new BadResponse(e.getMessage(), 2);
        StringBuilder sb = new StringBuilder();
        sb.append("RESPONSE: ");
        sb.append(HttpStatus.BAD_REQUEST.toString() + " ");
        sb.append(badResponse.toString());
        myLogger.log(sb.toString());
        return new ResponseEntity<>(badResponse, HttpStatus.BAD_REQUEST);
        //return new ResponseEntity<>(new BadResponse(e.getMessage(), 2), HttpStatus.BAD_REQUEST);
    }

    //HttpStatus.INTERNAL_SERVER_ERROR    500
    @ExceptionHandler(Exception500.class)
    public ResponseEntity<BadResponse> exception500Handler(Exception500 e) {
        BadResponse badResponse = new BadResponse(e.getMessage(), 1);
        StringBuilder sb = new StringBuilder();
        sb.append("RESPONSE: ");
        sb.append(HttpStatus.INTERNAL_SERVER_ERROR.toString() + " ");
        sb.append(badResponse.toString());
        myLogger.log(sb.toString());
        return new ResponseEntity<>(badResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        //return new ResponseEntity<>(new BadResponse(e.getMessage(), 1), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}