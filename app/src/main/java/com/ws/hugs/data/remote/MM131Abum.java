package com.ws.hugs.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MM131Abum {

    List<MM131Picture> list;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("titleCode")
    private long titleCode;

    public List<MM131Picture> getList() {
        return list;
    }

    public void setList(List<MM131Picture> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(long titleCode) {
        this.titleCode = titleCode;
    }

    @Override
    public String toString() {
        return "MM131Abum{" +
                "title='" + title + '\'' +
                ", titleCode=" + titleCode +
                '}';
    }
}
