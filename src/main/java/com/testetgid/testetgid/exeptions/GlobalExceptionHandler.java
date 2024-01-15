package com.testetgid.testetgid.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.testetgid.testetgid.controllers")
public class GlobalExceptionHandler {

    @ExceptionHandler({CPFInvalidException.class, CNPJInvalidException.class, InsufficientFundsException.class, NotFoundException.class})
    public ResponseEntity<String> handleSpecificExceptions(Exception e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (e instanceof InsufficientFundsException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (e instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
    }
}
