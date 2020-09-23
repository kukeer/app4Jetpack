package com.ws.hugs.app.picture.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PageKeyedDataSource;

import com.ws.hugs.HugApplication;
import com.ws.hugs.api.MM131RequestCenter;
import com.ws.hugs.api.RequestManager;
import com.ws.hugs.app.picture.data.db.MM131ArticleModel;
import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.data.remote.response.MPageResponse;
import com.xcheng.retrofit.Call;
import com.xcheng.retrofit.HttpError;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class MM131DataSource extends PageKeyedDataSource<Integer, MM131ArticleModel> {

    public static final int FIRST_PAGE=1;
    public static final int PER_PAGE=8;
    public static String CATEGORY = "性感美女";

    public final String TAG = this.getClass().getSimpleName();

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MM131ArticleModel> callback) {
        MM131RequestCenter MM131RequestCenter = RequestManager.getRequestCenter();
        Call<MPageResponse<MM131Article>> listArticle = MM131RequestCenter.getListArticle(CATEGORY, FIRST_PAGE);


        listArticle.enqueue(new MM131ArticleCallback() {

            @Override
            public void onError(Call<MPageResponse<MM131Article>> call, HttpError error) {
                Log.i(TAG, "失败接收BaseIPage" + error.getMessage());
//                loading.set(false);
            }

            @Override
            public void onSuccess(Call<MPageResponse<MM131Article>> call, MPageResponse<MM131Article> response) {
                List<MM131Article> netList = response.getData();

                List<MM131ArticleModel> localList = netList.stream().map(e -> {
                    MM131ArticleModel mm131ArticleModel = new MM131ArticleModel();
                    mm131ArticleModel.base64Image = e.getBase64Image();
                    mm131ArticleModel.height = e.getHeight();
                    mm131ArticleModel.size = Integer.parseInt(e.getSize().replaceAll("张", ""));
                    mm131ArticleModel.title = e.getTitle();
                    mm131ArticleModel.titleCode = e.getTitleCode();
                    mm131ArticleModel.width = e.getWidth();

                    float minwidth = new BigDecimal(e.getHeight()).divide(new BigDecimal(e.getWidth()),BigDecimal.ROUND_HALF_UP).floatValue()* HugApplication.phoneWidth;
                    mm131ArticleModel.minHeight = (int)minwidth+1;
                    mm131ArticleModel.ftpPath = e.getFtpPath();
                    return mm131ArticleModel;
                }).collect(Collectors.toList());
                callback.onResult(localList,null,FIRST_PAGE+1);

            }

            @Override
            public void onCompleted(Call<MPageResponse<MM131Article>> call, @Nullable Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MM131ArticleModel> callback) {
        //应该作校验工作之类的 暂时无用
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MM131ArticleModel> callback) {
        loadData(params.key,CATEGORY,callback);
//        params.
    }


    private void loadData(int page,String cate, LoadCallback<Integer, MM131ArticleModel> callback){
        RequestManager.getRequestCenter()
                .getListArticle(cate,page)
                .enqueue(new MM131ArticleCallback() {


                    @NonNull
                    @Override
                    public MPageResponse<MM131Article> transform(Call<MPageResponse<MM131Article>> call, MPageResponse<MM131Article> response) {
                        return response;
                    }

                    @Override
                    public void onError(Call<MPageResponse<MM131Article>> call, HttpError error) {

                    }

                    @Override
                    public void onSuccess(Call<MPageResponse<MM131Article>> call, MPageResponse<MM131Article> response) {
                        List<MM131Article> netList = response.getData();

                        List<MM131ArticleModel> localList = netList.stream().map(e -> {
                            MM131ArticleModel mm131ArticleModel = new MM131ArticleModel();
                            mm131ArticleModel.base64Image = e.getBase64Image();
                            mm131ArticleModel.height = e.getHeight();
                            mm131ArticleModel.size = Integer.parseInt(e.getSize().replaceAll("张", ""));
                            mm131ArticleModel.title = e.getTitle();
                            mm131ArticleModel.titleCode = e.getTitleCode();
                            mm131ArticleModel.width = e.getWidth();
                            float minwidth = new BigDecimal(e.getHeight()).divide(new BigDecimal(e.getWidth()),BigDecimal.ROUND_HALF_UP).floatValue()* HugApplication.phoneWidth;
                            mm131ArticleModel.minHeight = (int)minwidth+1;

                            mm131ArticleModel.ftpPath = e.getFtpPath();
                            return mm131ArticleModel;
                        }).collect(Collectors.toList());
                        callback.onResult(localList,localList==null||localList.size()==0?page:page+1);

                    }

                    @Override
                    public void onCompleted(Call<MPageResponse<MM131Article>> call, @Nullable Throwable t) {

                    }


                });
    }
}
