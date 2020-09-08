package com.ws.hugs.paging;

import com.ws.hugs.data.db.MM131ArticleModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class MM131DataSourceFactory extends DataSource.Factory<Integer, MM131ArticleModel> {

    private MutableLiveData<MM131DataSource> liveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, MM131ArticleModel> create() {

        MM131DataSource dataSource = new MM131DataSource();
        liveData.postValue(dataSource);
        return dataSource;
    }
}
