package com.ws.hugs.app.picture.paging;

import android.app.Application;

import androidx.paging.PagedList;

import com.ws.hugs.api.RequestManager;
import com.ws.hugs.db.mm131.tb.MM131ArticleModel;
import com.ws.hugs.db.HugsDatabase;
import com.ws.hugs.db.mm131.ArticleDao;
import com.ws.hugs.repositry.MM131Repository;

import org.jetbrains.annotations.NotNull;

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
