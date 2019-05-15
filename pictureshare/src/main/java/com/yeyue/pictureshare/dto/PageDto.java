package com.yeyue.pictureshare.dto;

import java.util.List;

public class PageDto {
    /**
     * 数据
     */
    private List<?> list;
    /**
     * 数量
     */
    private Integer count;

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
