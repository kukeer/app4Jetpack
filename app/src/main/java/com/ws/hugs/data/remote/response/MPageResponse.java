package com.ws.hugs.data.remote.response;

import java.util.List;

public class MPageResponse<T> {
    private int size;
    private int total;
    private int current;

    private List<T> data;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "MPageResponse{" +
                "size=" + size +
                ", total=" + total +
                ", current=" + current +
                ", data=" + data +
                '}';
    }
}
