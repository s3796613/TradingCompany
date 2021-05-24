package com.code.company.controller;

import com.code.company.controller.exception.NoResult;
import com.code.company.controller.exception.NotFound;
import com.code.company.response.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {


    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> exceptionHandler() {
        Response response = new Response();
        response.setStatus("Request failed");
        response.setStatusCode(404);
        response.setError("This entity is link to other entities, please delete them first before delete this one");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(value = {NotFound.class})
    public ResponseEntity<Object> exceptionNotFound(NotFound ex) {
        Response response = new Response();
        response.setStatus("Bad request");
        response.setStatusCode(404);
        response.setError(ex.getReason());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(value = {NoResult.class})
    public ResponseEntity<Object> exceptionNoResult(NoResult ex) {
        Response response = new Response();
        response.setStatus("No result");
        response.setStatusCode(404);
        response.setError(ex.getReason());
        return ResponseEntity.ok(response);
    }


}
