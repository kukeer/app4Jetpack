package com.ws.hugs.db.mm131;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ws.hugs.db.mm131.tb.MM131VideoArticleModel;

import java.util.List;

@Dao
public interface VideoArticleDao {


    @Insert
    void insertArticles(List<MM131VideoArticleModel> list);

    @Query("delete from mm131_video")
    void clear();

    @Delete
    void deleteArticle(MM131VideoArticleModel model);
//    DataSource

    @Query("select * from mm131_video")
    DataSource.Factory<Integer,MM131VideoArticleModel> getUserList();


    @Query("select * from mm131_video")
    List<MM131VideoArticleModel> getUserArrayList();
    @Query("select count(*) from mm131_video")
    int getCount();
}
