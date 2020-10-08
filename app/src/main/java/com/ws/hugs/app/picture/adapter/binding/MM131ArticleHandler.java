package com.ws.hugs.app.picture.adapter.binding;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;

import com.ws.hugs.HugApplication;

import java.math.BigDecimal;

public class MM131ArticleHandler {

    @BindingAdapter("mm131height")
    public static void setLayoutHeight(View view, int height){
        Log.i("MM131ArticleHandler","  mm131height "+height);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        layoutParams.height = height;
    }

}
