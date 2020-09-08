package com.ws.hugs.data.db;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mm131_article")
public class MM131ArticleModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    public String title;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    public String base64Image;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public long titleCode;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int size;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int width;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int height;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int minHeight;

    @ColumnInfo(typeAffinity = ColumnInfo.UNDEFINED)
    public long date;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    public String ftpPath;

    @Override
    public String toString() {
        return "MM131ArticleModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", base64Image='" + base64Image + '\'' +
                ", titleCode=" + titleCode +
                ", size=" + size +
                ", width=" + width +
                ", height=" + height +
                ", minHeight=" + minHeight +
                ", date=" + date +
                '}';
    }
}
