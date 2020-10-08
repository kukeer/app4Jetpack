package com.ws.hugs.api;

import com.ws.hugs.app.picture.data.remote.VideoArticleDto;
import com.ws.hugs.data.remote.MM131Abum;
import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.data.remote.response.MPageResponse;
import com.ws.hugs.data.remote.response.MResponse;
import com.xcheng.retrofit.Call;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MM131RequestCenter {

    public static final int DEFAULT_PAGE_SIZE = 8;

    @GET("/getImageListById")
    Call<MResponse<MM131Abum>> getImageList(@Query("titleId") String id, @Query("time")int time);

    /**
     * 根据id获取后面的8条数据
     * @param id
     * @return
     */
    @GET("/searchContentById")
    Call<MPageResponse<MM131Article>> getListArticleById(@Query("themeId")long id);

    @GET("/searchContent")
    Call<MPageResponse<MM131Article>> getListArticle(@Query("cate")String cate, @Query("current") int current);

    @GET("/getPicById")
    Call<ResponseBody> getPicById(@Query("id") String id);


    @GET("/play/list")
    Call<MPageResponse<VideoArticleDto>> getVideoList(@Query("offset") int current);


}
