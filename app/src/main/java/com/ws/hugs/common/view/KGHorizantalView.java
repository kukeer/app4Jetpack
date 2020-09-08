package com.ws.hugs.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.ws.hugs.R;
import com.ws.hugs.data.model.Article;

import java.util.ArrayList;
import java.util.List;

public class KGHorizantalView extends ViewGroup {

    List<Article> list = new ArrayList();


    float marginTop;
    public KGHorizantalView(Context context) {
        this(context,null);
    }

    public KGHorizantalView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public KGHorizantalView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public KGHorizantalView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImagesViewGroup);
        float dimension = typedArray.getDimension(R.styleable.ImagesViewGroup_ivg_marginTop, 12);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        marginTop = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dimension,displayMetrics);
        typedArray.recycle();



        for(int index = 1;index<12;index++){
            Article article = new Article(null, "标题" + index, index);
            addView(article);
            list.add(article);
        }
    }


    private void addView(Article article){


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
