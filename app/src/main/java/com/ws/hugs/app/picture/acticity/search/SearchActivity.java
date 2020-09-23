package com.ws.hugs.app.picture.acticity.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ws.hugs.R;
import com.ws.hugs.data.model.Article;
import com.ws.hugs.data.viewmodel.SearchInfoViewModel;

public class SearchActivity extends AppCompatActivity {


    private SearchInfoViewModel searchInfoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInfoViewModel = new ViewModelProvider(this).get(SearchInfoViewModel.class);
        final Observer<Article> articleObserver =new Observer<Article>(){

            @Override
            public void onChanged(Article article) {
                //在这里修改UI

            }
        };

    }


}
