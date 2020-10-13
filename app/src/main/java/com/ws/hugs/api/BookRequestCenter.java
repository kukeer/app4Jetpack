package com.ws.hugs.api;

import com.ws.hugs.app.picture.data.remote.VideoArticleDto;
import com.ws.hugs.data.remote.Book;
import com.ws.hugs.data.remote.BookChapter;
import com.ws.hugs.data.remote.BookChapterDto;
import com.ws.hugs.data.remote.MM131Abum;
import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.data.remote.response.MPageResponse;
import com.ws.hugs.data.remote.response.MResponse;
import com.ws.hugs.data.remote.response.Page;
import com.xcheng.retrofit.Call;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookRequestCenter {

    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 查询书籍列表
     * @param id
     * @param time
     * @return
     */
    @GET("/book/page")
    Call<MResponse<Page<Book>>> getBookList(@Query("index") int index);

    /**
     * 查询书籍单个章节内容
     * @param id
     * @return
     */
    @GET("/book/chapter")
    Call<MResponse<BookChapterDto>> getListArticleByBookId(@Query("index")long index,@Query("index")long bookId);

    /**
     * 查询书籍多个章节
     * @param cate
     * @param current
     * @return
     */
    @GET("/book/chapterList")
    Call<MResponse<Page<BookChapter>>> getListArticle(@Query("index")int index, @Query("bookId") int boodId);


    /**
     * 获取书籍目录
     * @param boodId
     * @return
     */
    @GET("/book/chapterCate")
    Call<MResponse<List<BookChapter>>> getListCates(@Query("bookId") long boodId);

}
