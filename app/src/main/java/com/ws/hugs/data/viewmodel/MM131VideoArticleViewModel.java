package com.ws.hugs.data.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.ws.hugs.api.MM131RequestCenter;
import com.ws.hugs.app.picture.data.db.MM131VideoArticleModel;
import com.ws.hugs.app.picture.paging.VideoArticleBoundryCallBack;
import com.ws.hugs.db.HugsDatabase;

import java.util.List;

public class MM131VideoArticleViewModel extends AndroidViewModel {

    private final String TAG = this.getClass().getSimpleName();

    public MM131VideoArticleViewModel(@NonNull Application application) {
        this(application,0L);

    }

    public MM131VideoArticleViewModel(@NonNull Application application,long currentId) {
        super(application);

        HugsDatabase instance = HugsDatabase.getInstance(application);
        mm131ArticleViewModelLiveData = (new LivePagedListBuilder<>(instance.videoArticleDao().getUserList(), MM131RequestCenter.DEFAULT_PAGE_SIZE))
                .setBoundaryCallback(new VideoArticleBoundryCallBack(application)).build();
    }

    public LiveData<PagedList<MM131VideoArticleModel>> mm131ArticleViewModelLiveData;

    public void refreshData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HugsDatabase.getInstance(getApplication()).videoArticleDao().clear();
            }
        }).start();
    }

    public int findCurrentPosition(long id){

        List<MM131VideoArticleModel> userArrayList = HugsDatabase.getInstance(getApplication()).videoArticleDao().getUserArrayList();
        int size = userArrayList.size();
        for(int index =0;index<size;index++){
            if (userArrayList.get(index).videoCode == id) return index;
        }
        Log.w(TAG,"未找到相关id");
        return 0;
    }
}
