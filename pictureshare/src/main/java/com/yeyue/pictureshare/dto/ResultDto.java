package com.yeyue.pictureshare.dto;

public class ResultDto {
    /**
     * 返回值
     */
    private Integer code;
    /**
     * 数据
     */
    private PageDto data;
    /**
     * 提示信息
     */
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public PageDto getData() {
        return data;
    }

    public void setData(PageDto data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
