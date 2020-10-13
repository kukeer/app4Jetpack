package com.ws.hugs.db.mm131.tb;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mm131_video")
public class MM131VideoArticleModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int id;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int index;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int dianzan;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int pinglun;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int aixin;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public boolean collect;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    public String imgUrl;

    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    public String title;

    @ColumnInfo(typeAffinity = ColumnInfo.UNDEFINED)
    public long videoCode;

    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    public int mesureWidth;

}
