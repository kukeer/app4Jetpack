package com.ws.hugs.data.model;

public class BannerImage {



    private String imgUrl;

    private String url;
    private int resId;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public BannerImage(String imgUrl, String url, int resId) {
        this.imgUrl = imgUrl;
        this.url = url;
        this.resId = resId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
