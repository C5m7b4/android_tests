package com.example.drawer;

public class BasicResponse {
    private Integer error;
    private Boolean success;

    public BasicResponse(Integer error, Boolean success, String msg) {
        this.error = error;
        this.success = success;
        this.msg = msg;
    }

    public BasicResponse()
    {

    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
}
