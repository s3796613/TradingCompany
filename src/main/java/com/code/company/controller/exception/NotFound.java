package com.code.company.controller.exception;

public class NotFound extends RuntimeException{


    private String reason;
    private int code = 1;

    public NotFound() {
    }

    public NotFound(String reason) {
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }




}
