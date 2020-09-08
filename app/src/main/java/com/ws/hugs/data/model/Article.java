package com.ws.hugs.data.model;

import java.util.List;

public class Article {

    public Article(List<String> imageList, String thinkAb, int code) {
        this.imageList = imageList;
        this.thinkAb = thinkAb;
        this.code = code;
    }

    private List<String> imageList;

    private String thinkAb;

    private int code;

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getThinkAb() {
        return thinkAb;
    }

    public void setThinkAb(String thinkAb) {
        this.thinkAb = thinkAb;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
