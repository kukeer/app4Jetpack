package com.ws.hugs.adapter.binding;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;

public class MM131ArticleHandler {

    @BindingAdapter("mm131height")
    public static void setLayoutHeight(View view, int height){
//        view.setMinimumHeight(height);
        Log.i("MM131ArticleHandler","  mm131height "+height);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
    }

}
