package com.example.demo.utils;

import java.io.Serializable;

/**
 * @author yzj
 */
public class ResultResponse implements Serializable {
    private Integer code;
    private String message;
    private Object data;

    public ResultResponse() {

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultResponse SUCCESS() {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(200);
        resultResponse.setMessage("操作成功");
        return resultResponse;
    }

    public static ResultResponse SUCCESS(Object data) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(200);
        resultResponse.setMessage("操作成功");
        resultResponse.setData(data);
        return resultResponse;
    }

    public static ResultResponse FAILED() {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(-1);
        resultResponse.setMessage("操作失败");

        return resultResponse;
    }

    public static ResultResponse FAILED(Object data) {
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(-1);
        resultResponse.setMessage("操作失败");
        resultResponse.setData(data);

        return resultResponse;
    }

    public static ResultResponse FAILED(Integer code, String message) {
      ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(code);
        resultResponse.setMessage(message);

        return resultResponse;
    }
}
