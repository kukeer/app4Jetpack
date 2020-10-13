package com.ws.hugs.repositry;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ws.hugs.HugApplication;
import com.ws.hugs.db.mm131.tb.MM131VideoArticleModel;
import com.ws.hugs.app.picture.data.remote.VideoArticleDto;
import com.ws.hugs.app.picture.paging.MM131DataSource;
import com.ws.hugs.data.remote.response.MPageResponse;
import com.ws.hugs.db.mm131.VideoArticleDao;
import com.xcheng.retrofit.Call;
import com.xcheng.retrofit.Callback;
import com.xcheng.retrofit.HttpError;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class MM131VideoRepositry {

    private String TAG = this.getClass().getSimpleName();

    private VideoArticleDao articleDao;

    private com.ws.hugs.api.MM131RequestCenter MM131RequestCenter;


    public MM131VideoRepositry(VideoArticleDao articleDao, com.ws.hugs.api.MM131RequestCenter MM131RequestCenter) {
        this.articleDao = articleDao;
        this.MM131RequestCenter = MM131RequestCenter;
    }

    private void loadByOffset(int current) {

        MM131RequestCenter.getVideoList(current).enqueue(new Callback<MPageResponse<VideoArticleDto>>() {
            @Override
            public void onStart(Call<MPageResponse<VideoArticleDto>> call) {

            }

            @NonNull
            @Override
            public HttpError parseThrowable(Call<MPageResponse<VideoArticleDto>> call, Throwable t) {
                return new HttpError(t.getMessage());
            }

            @NonNull
            @Override
            public MPageResponse<VideoArticleDto> transform(Call<MPageResponse<VideoArticleDto>> call, MPageResponse<VideoArticleDto> videoArticleDtoMPageResponse) {
                return videoArticleDtoMPageResponse;
            }

            @Override
            public void onError(Call<MPageResponse<VideoArticleDto>> call, HttpError error) {

            }

            @Override
            public void onSuccess(Call<MPageResponse<VideoArticleDto>> call, MPageResponse<VideoArticleDto> videoArticleDtoMPageResponse) {
                List<MM131VideoArticleModel> collect = videoArticleDtoMPageResponse.getData().stream().map(e -> transfrom(e)).collect(Collectors.toList());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        articleDao.insertArticles(collect);
                    }
                }).start();
            }

            @Override
            public void onCompleted(Call<MPageResponse<VideoArticleDto>> call, @Nullable Throwable t) {

            }

            private MM131VideoArticleModel transfrom(VideoArticleDto dto){
                MM131VideoArticleModel model = new MM131VideoArticleModel();
                model.aixin = Integer.valueOf(dto.getAixin());
                model.pinglun = Integer.valueOf(dto.getPinglun());
                model.collect = dto.isCollect();
                model.dianzan = Integer.valueOf(dto.getDianzan());
                model.imgUrl = dto.getImgUrl();
                model.videoCode = dto.getVideoCode();
                model.title = dto.getTitle();
                model.mesureWidth = (int) (new BigDecimal(dto.getHeight()).divide(new BigDecimal(dto.getWidth()),BigDecimal.ROUND_HALF_UP).floatValue()* HugApplication.phoneWidth);
                Log.i(TAG,"getHeight "+dto.getHeight());
                Log.i(TAG,"getWidth "+dto.getWidth());
                Log.i(TAG,"model "+model.mesureWidth);
                return model;
            }
        });

    }


    public LiveData<PagedList<MM131VideoArticleModel>> getVideoArticle(int offset) {
        Log.i(TAG,"开始发送请求   ");
        loadByOffset(offset);
        DataSource.Factory<Integer, MM131VideoArticleModel> userList = articleDao.getUserList();
        PagedList.Config build = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPageSize(MM131DataSource.PER_PAGE)
                .setPrefetchDistance(3)
                .setInitialLoadSizeHint(MM131DataSource.PER_PAGE * 2)
                .setMaxSize(9999)
                .build();

        LiveData<PagedList<MM131VideoArticleModel>> concertList = new LivePagedListBuilder(userList, build).build();
        return concertList;
    }


    public int getOffsetByEndItem(){
        int count = articleDao.getCount();
        if (count%MM131RequestCenter.DEFAULT_PAGE_SIZE!=0){
            Log.w(TAG,"mm131video的分页页面大小可能不是默认的分页大小");
        }
        Log.i(TAG,"视频 count为 "+count);
        return count/MM131RequestCenter.DEFAULT_PAGE_SIZE;
    }

}
