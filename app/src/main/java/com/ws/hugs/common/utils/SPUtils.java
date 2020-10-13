package com.ws.hugs.common.utils;

import android.content.SharedPreferences;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.data.remote.response.MPageResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SPUtils {

    private static SPUtils spUtils = new SPUtils();
//    private static ThreadLocal<SharedPreferences> spThread = new ThreadLocal<>();
    private SharedPreferences sp;
    //theme列表 key
    private String ARTICLE_KEY = "ARTICLE_KEY";



    /**
     * 初始化方法 用于在application中调用
     * @param sharedPreferences
     */
    public void initSp(SharedPreferences sharedPreferences){
        sp= sharedPreferences;
    }

    public static SPUtils getInstance(){
        return spUtils;
    }


    public static final String BOOK_MIN_OFFSET = "BOOK_MIN_OFFSET";
    public static final String BOOK_MAX_OFFSET = "BOOK_MAX_OFFSET";


    public void saveBookDataIndex(int minIndex,int maxIndex){
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(BOOK_MIN_OFFSET,minIndex);
        edit.putInt(BOOK_MAX_OFFSET,maxIndex);
        edit.commit();
    }

    public void saveBookMinDataIndex(int minIndex){
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(BOOK_MIN_OFFSET,minIndex);
        edit.commit();
    }
    public void saveBookMaxDataIndex(int maxIndex){
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(BOOK_MAX_OFFSET,maxIndex);
        edit.commit();
    }
    public int getBookDataMinIndex(){
        return sp.getInt(BOOK_MIN_OFFSET,0);
    }
    public int getBookDataMaxIndex(){
        return sp.getInt(BOOK_MAX_OFFSET,0);
    }
}
