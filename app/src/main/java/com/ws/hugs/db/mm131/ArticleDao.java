package com.ws.hugs.db.mm131;

import java.util.List;


import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ws.hugs.db.mm131.tb.MM131ArticleModel;

@Dao
public interface ArticleDao {


    @Insert
    void insertArticles(List<MM131ArticleModel> list);

    @Query("delete from mm131_article")
    void clear();

    @Delete
    void deleteArticle(MM131ArticleModel model);
//    DataSource

    @Query("select * from mm131_article")
    DataSource.Factory<Integer,MM131ArticleModel> getUserList();

}
