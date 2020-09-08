package com.ws.hugs.data.model;

public class PhoneInfo {

    private int width;
    private int height;


    public PhoneInfo() {
    }

    public PhoneInfo(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
