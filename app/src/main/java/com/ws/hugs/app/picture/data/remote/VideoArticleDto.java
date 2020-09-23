package com.ws.hugs.app.picture.data.remote;

public class VideoArticleDto {

    private String dianzan;
    private String pinglun;
    private String aixin;
    private boolean collect;

    private String imgUrl;

    private String title;

    private long videoCode;



    public String getDianzan() {
        return dianzan;
    }

    public void setDianzan(String dianzan) {
        this.dianzan = dianzan;
    }

    public String getPinglun() {
        return pinglun;
    }

    public void setPinglun(String pinglun) {
        this.pinglun = pinglun;
    }

    public String getAixin() {
        return aixin;
    }

    public void setAixin(String aixin) {
        this.aixin = aixin;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(long videoCode) {
        this.videoCode = videoCode;
    }
}
