package com.ws.hugs.data.viewmodel;

import android.app.Application;

import com.ws.hugs.api.MM131RequestCenter;
import com.ws.hugs.app.picture.paging.ArticleBoundryCallBack;
import com.ws.hugs.app.picture.paging.MM131DataSource;
import com.ws.hugs.app.picture.paging.MM131DataSourceFactory;
//import com.ws.hugs.paging.ArticleBoundryCallBack;
//import com.ws.hugs.paging.MM131DataSource;
//import com.ws.hugs.paging.MM131DataSourceFactory;
import com.ws.hugs.app.picture.data.db.MM131ArticleModel;
import com.ws.hugs.db.HugsDatabase;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

public class MM131ArticleViewModel extends AndroidViewModel {

    public LiveData<PagedList<MM131ArticleModel>> mm131ArticleViewModelLiveData;

    public MM131ArticleViewModel(Application application) {
        super(application);
//        直接加载网络数据
//        PagedList.Config build = (new PagedList.Config.Builder())
//                .setEnablePlaceholders(true)
//                .setPageSize(8)
//                .setPrefetchDistance(3)
//                .setInitialLoadSizeHint(MM131DataSource.PER_PAGE * 2)
//                .setMaxSize(9999)
//                .build();
//        mm131ArticleViewModelLiveData = (new LivePagedListBuilder<>(new MM131DataSourceFactory(),build)).build();


        //网络+本地
        HugsDatabase instance = HugsDatabase.getInstance(application);
        mm131ArticleViewModelLiveData = (new LivePagedListBuilder<>(instance.articleDao().getUserList(), MM131RequestCenter.DEFAULT_PAGE_SIZE))
                .setBoundaryCallback(new ArticleBoundryCallBack(application)).build();

    }

    public void refreshData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HugsDatabase.getInstance(getApplication()).articleDao().clear();
            }
        }).start();
    }
}
