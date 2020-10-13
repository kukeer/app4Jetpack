package com.ws.hugs.data.remote.response;

import java.util.Collections;
import java.util.List;

public class Page<T> {

    private int size;
    private long total;

    private long current;

    List<T> records = Collections.emptyList();


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
