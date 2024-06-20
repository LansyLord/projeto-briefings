package br.com.projeto.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BriefingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleBriefingNotFoundException(BriefingNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleClienteNotFoundException(ClienteNotFoundException ex) {
        return ex.getMessage();
    }
}
