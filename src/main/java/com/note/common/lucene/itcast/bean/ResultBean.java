package com.note.common.lucene.itcast.bean;

import java.util.List;

public class ResultBean<T> {
    private int total;//总记录数
    private List<T> result;//结果集

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

}
