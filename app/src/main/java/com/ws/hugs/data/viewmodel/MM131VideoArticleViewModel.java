package com.ws.hugs.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ws.hugs.api.MM131RequestCenter;
import com.ws.hugs.app.picture.data.db.MM131VideoArticleModel;
import com.ws.hugs.app.picture.paging.VideoArticleBoundryCallBack;
import com.ws.hugs.db.HugsDatabase;

public class MM131VideoArticleViewModel extends AndroidViewModel {
    public MM131VideoArticleViewModel(@NonNull Application application) {
        super(application);

        HugsDatabase instance = HugsDatabase.getInstance(application);
        mm131ArticleViewModelLiveData = (new LivePagedListBuilder<>(instance.videoArticleDao().getUserList(), MM131RequestCenter.DEFAULT_PAGE_SIZE))
                .setBoundaryCallback(new VideoArticleBoundryCallBack(application)).build();
    }
    public LiveData<PagedList<MM131VideoArticleModel>> mm131ArticleViewModelLiveData;

    public void refreshData(){
        HugsDatabase.getInstance(getApplication()).videoArticleDao().clear();
    }

}
