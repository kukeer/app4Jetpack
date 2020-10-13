package com.ws.hugs.db.book.tb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "book")
public class BookModel {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    private int bookId;

    @ColumnInfo(typeAffinity = ColumnInfo.UNDEFINED)
    long id;

    //书籍名称
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    String bookName;
    //书籍封面链接地址
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    String bookImgUrl;
    //作者
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    String author;
    //小说介绍
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    String introduce;
    //创建时间
    @ColumnInfo(typeAffinity = ColumnInfo.UNDEFINED)
    Date createTime;
    //更新时间
    @ColumnInfo(typeAffinity = ColumnInfo.UNDEFINED)
    Date lastUpdateTime;
    //最新章节
    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    String lastChapter;
    //收藏数量
    @ColumnInfo(typeAffinity = ColumnInfo.INTEGER)
    int collection;


}
