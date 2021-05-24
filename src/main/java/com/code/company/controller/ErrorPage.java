package com.code.company.controller;

import com.code.company.response.Response;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value="${server.error.path:${error.path:/error}}")
public class ErrorPage extends AbstractErrorController {
//    private ErrorAttributes errorAttributes;
//    private final static String ERROR_PATH = "/error";

    public ErrorPage(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }


    @GetMapping
    public ResponseEntity<Object> unknownError() {
        Response response = new Response();
        response.setStatusCode(404);
        response.setStatus("Bad request");
        response.setError("Unknown error");
        return ResponseEntity.ok(response);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
