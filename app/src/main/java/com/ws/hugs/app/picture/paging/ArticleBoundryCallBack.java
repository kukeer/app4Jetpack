package com.ws.hugs.paging;

import android.app.Application;
import android.util.Log;

import com.ws.hugs.HugApplication;
import com.ws.hugs.api.RequestCenter;
import com.ws.hugs.api.RequestManager;
import com.ws.hugs.data.db.MM131ArticleModel;
import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.data.remote.response.MPageResponse;
import com.ws.hugs.db.HugsDatabase;
import com.ws.hugs.db.mm131.ArticleDao;
import com.ws.hugs.repositry.MM131Repository;
import com.xcheng.retrofit.Call;
import com.xcheng.retrofit.HttpError;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import androidx.annotation.Nullable;
import androidx.paging.PagedList;

public class ArticleBoundryCallBack extends PagedList.BoundaryCallback<MM131ArticleModel> {

    private String TAG = this.getClass().getSimpleName();

    private Application application;
    MM131Repository mm131Repository;

    public ArticleBoundryCallBack(Application application){
        this.application = application;
        ArticleDao articleDao = HugsDatabase.getInstance(application)
                .articleDao();
        mm131Repository= new MM131Repository(articleDao, RequestManager.getRequestCenter());

    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        mm131Repository.getArticle(null,0,true);
    }

    @Override
    public void onItemAtEndLoaded(@NotNull MM131ArticleModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        mm131Repository.getArticle(null,itemAtEnd.titleCode,false);
    }

}
