package com.ws.hugs.data.viewmodel;

import com.ws.hugs.data.model.Article;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchInfoViewModel extends ViewModel {

    private MutableLiveData<Article> articleList;

    public MutableLiveData<Article> getCurrentArticle(){
        if (articleList ==null){
            articleList = new MutableLiveData<Article>();
        }
        return articleList;
    }




}
