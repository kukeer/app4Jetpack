package com.ws.hugs.data.remote;


import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.ws.hugs.HugApplication;

public class MM131Article {


    private String title;
    private String base64Image;
    private long titleCode;
    private int size;
    private int width;
    private int height;
    private int minHeight;
    private String ftpPath;

    public String getFtpPath() {
        return ftpPath;
    }

    public void setFtpPath(String ftpPath) {
        this.ftpPath = ftpPath;
    }
    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
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

    public String getSize() {
        return size+"张";
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public long getTitleCode() {
        return titleCode;
    }

    public void setTitleCode(long titleCode) {
        this.titleCode = titleCode;
    }

    @Override
    public String toString() {
        return "MM131Article{" +
                "title='" + title + '\'' +
                ", base64Image=" + base64Image +
                ", titleCode=" + titleCode +
                ", size=" + size +
                '}';
    }

    public String getBase64Image() {
        return base64Image;
    }


}
