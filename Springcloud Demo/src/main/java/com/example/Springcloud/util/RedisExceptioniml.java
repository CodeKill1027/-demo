package com.example.Springcloud.util;

public class RedisExceptioniml  extends RuntimeException {

    private Integer code;
    private String message;

    public RedisExceptioniml(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

