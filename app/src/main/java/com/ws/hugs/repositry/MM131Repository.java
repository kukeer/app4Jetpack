package com.ws.hugs.repositry;

import android.util.Log;

import com.ws.hugs.HugApplication;
import com.ws.hugs.api.RequestCenter;
import com.ws.hugs.api.RequestManager;
import com.ws.hugs.data.db.MM131ArticleModel;
import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.data.remote.response.MPageResponse;
import com.ws.hugs.db.mm131.ArticleDao;
import com.ws.hugs.paging.MM131ArticleCallback;
import com.ws.hugs.paging.MM131DataSource;
import com.xcheng.retrofit.Call;
import com.xcheng.retrofit.HttpError;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import static com.ws.hugs.paging.MM131DataSource.CATEGORY;

public class MM131Repository {

    private String TAG = this.getClass().getSimpleName();

    private ArticleDao articleDao;

    private RequestCenter requestCenter;

    public MM131Repository(ArticleDao articleDao, RequestCenter requestCenter) {
        this.articleDao = articleDao;
        this.requestCenter = requestCenter;
    }

    public LiveData<PagedList<MM131ArticleModel>> getArticle(String cate, long current,boolean firstLoad) {
        if (firstLoad){
            refresh(cate, (int)current);
        }else {
            refreshById(cate,current);
        }
        DataSource.Factory<Integer, MM131ArticleModel> userList = articleDao.getUserList();
        PagedList.Config build = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setPageSize(MM131DataSource.PER_PAGE)
                .setPrefetchDistance(3)
                .setInitialLoadSizeHint(MM131DataSource.PER_PAGE * 2)
                .setMaxSize(9999)
                .build();

        LiveData<PagedList<MM131ArticleModel>> concertList = new LivePagedListBuilder(userList, build).build();
        return concertList;
    }

    private void refresh(String cate, int current) {

        requestCenter.getListArticle(cate, current).enqueue(new MM131ArticleCallback() {
            @Override
            public void onError(Call<MPageResponse<MM131Article>> call, HttpError error) {

            }

            @Override
            public void onSuccess(Call<MPageResponse<MM131Article>> call, MPageResponse<MM131Article> response) {

                List<MM131ArticleModel> localList = response.getData().stream().map(e -> {
                    MM131ArticleModel mm131ArticleModel = new MM131ArticleModel();
                    mm131ArticleModel.base64Image = e.getBase64Image();
                    mm131ArticleModel.height = e.getHeight();
                    mm131ArticleModel.size = Integer.parseInt(e.getSize().replaceAll("张", ""));
                    mm131ArticleModel.title = e.getTitle();
                    mm131ArticleModel.titleCode = e.getTitleCode();
                    mm131ArticleModel.width = e.getWidth();
                    mm131ArticleModel.ftpPath = e.getFtpPath();

                   try{
                       float minwidth = new BigDecimal(e.getHeight()).divide(new BigDecimal(e.getWidth())).floatValue() * HugApplication.phoneWidth;
                       mm131ArticleModel.minHeight = (int) minwidth + 1;
                       mm131ArticleModel.date = new Date().getTime();
                   }catch (ArithmeticException e2){
                       Log.i(TAG,"e.getHeight()"+e.getHeight()+" e.getWidth() "+e.getWidth());
                   }
                    return mm131ArticleModel;
                }).collect(Collectors.toList());
                Log.i(TAG,"接受到远程数据，初始化并存储数据库");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        articleDao.insertArticles(localList);
                    }
                }).start();
            }

            @Override
            public void onCompleted(Call<MPageResponse<MM131Article>> call, @Nullable Throwable t) {

            }
        });
    }

    private void refreshById(String cate, long id) {

        requestCenter.getListArticleById(id).enqueue(new MM131ArticleCallback() {
            @Override
            public void onError(Call<MPageResponse<MM131Article>> call, HttpError error) {

            }

            @Override
            public void onSuccess(Call<MPageResponse<MM131Article>> call, MPageResponse<MM131Article> response) {

                List<MM131ArticleModel> localList = response.getData().stream().map(e -> {
                    MM131ArticleModel mm131ArticleModel = new MM131ArticleModel();
                    mm131ArticleModel.base64Image = e.getBase64Image();
                    mm131ArticleModel.height = e.getHeight();
                    mm131ArticleModel.size = Integer.parseInt(e.getSize().replaceAll("张", ""));
                    mm131ArticleModel.title = e.getTitle();
                    mm131ArticleModel.titleCode = e.getTitleCode();
                    mm131ArticleModel.width = e.getWidth();
                    mm131ArticleModel.ftpPath = e.getFtpPath();
                    try{
                        float minwidth = new BigDecimal(e.getHeight()).divide(new BigDecimal(e.getWidth())).floatValue() * HugApplication.phoneWidth;
                        mm131ArticleModel.minHeight = (int) minwidth + 1;
                        mm131ArticleModel.date = new Date().getTime();
                    }catch (Exception ee){
                        Log.i(TAG,"kkkkk "+e.getWidth()+"===="+e.getHeight());
                    }
                    return mm131ArticleModel;
                }).collect(Collectors.toList());
                Log.i(TAG,"接受到远程数据，初始化并存储数据库");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        articleDao.insertArticles(localList);
                    }
                }).start();
            }

            @Override
            public void onCompleted(Call<MPageResponse<MM131Article>> call, @Nullable Throwable t) {

            }
        });
    }
}


