package com.ws.hugs.db.book;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.ws.hugs.db.book.tb.BookModel;
import com.ws.hugs.db.mm131.tb.MM131VideoArticleModel;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insertArticles(List<BookModel> list);

    @Query("delete from book")
    void clear();

    @Delete
    void deleteArticle(BookModel model);
//    DataSource

    @Query("select * from book")
    DataSource.Factory<Integer,MM131VideoArticleModel> getUserList();


    @Query("select * from book order by id limit :size offset :offset;")
    List<MM131VideoArticleModel> getUserArrayList(int size,int offset);

    @Query("select count(*) from mm131_video")
    int getCount();
}
