package com.ws.hugs.data.viewmodel;

import android.app.Application;

import com.ws.hugs.data.remote.MM131Article;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ArticleVM extends AndroidViewModel {

    public final String TAG = getClass().getSimpleName();

    private  MutableLiveData<List<MM131Article>> data;

    public ArticleVM(@NonNull Application application) {
        super(application);
    }
    public MutableLiveData getData(){
        if (data == null){
            data = new MutableLiveData<List<MM131Article>>();
        }
        return data;
    }

}
