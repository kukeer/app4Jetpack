package com.ws.hugs.app.picture.paging;

import android.app.Application;

import androidx.paging.PagedList;

import com.ws.hugs.api.RequestManager;
import com.ws.hugs.app.picture.data.db.MM131ArticleModel;
import com.ws.hugs.app.picture.data.db.MM131VideoArticleModel;
import com.ws.hugs.app.picture.data.remote.VideoArticleDto;
import com.ws.hugs.db.HugsDatabase;
import com.ws.hugs.db.mm131.ArticleDao;
import com.ws.hugs.db.mm131.VideoArticleDao;
import com.ws.hugs.repositry.MM131Repository;
import com.ws.hugs.repositry.MM131VideoRepositry;

import org.jetbrains.annotations.NotNull;

public class VideoArticleBoundryCallBack extends PagedList.BoundaryCallback<MM131VideoArticleModel>  {

    private String TAG = this.getClass().getSimpleName();

    private Application application;
    MM131VideoRepositry mm131Repository;
    public VideoArticleBoundryCallBack(Application application){
        this.application = application;
        VideoArticleDao articleDao = HugsDatabase.getInstance(application)
                .videoArticleDao();
        mm131Repository= new MM131VideoRepositry(articleDao, RequestManager.getRequestCenter());
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        mm131Repository.getVideoArticle(0);
    }

    @Override
    public void onItemAtEndLoaded(@NotNull MM131VideoArticleModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int offset = mm131Repository.getOffsetByEndItem();
                mm131Repository.getVideoArticle(offset);
            }
        }).start();

    }
}
