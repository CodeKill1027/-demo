package com.example.Springcloud.util;

/**
 * 基地业务异常
 *
 * @author yzj
 * @date 2022/05/21
 */
public class BaseBusinessException extends RuntimeException {

    private Integer code;
     private String message;

    public BaseBusinessException(Integer code,String message) {
       this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

