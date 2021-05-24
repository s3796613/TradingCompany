package com.code.company.controller.exception;

public class NoResult extends RuntimeException{
    private final int code = 2;
    private String reason;

    public NoResult(String reason) {
        this.reason = reason;
    }

    public NoResult() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
