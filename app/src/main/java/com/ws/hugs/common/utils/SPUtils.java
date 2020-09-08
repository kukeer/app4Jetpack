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

    private static SPUtils spUtils;
//    private static ThreadLocal<SharedPreferences> spThread = new ThreadLocal<>();
private static SharedPreferences sp;
    //theme列表 key
    private String ARTICLE_KEY = "ARTICLE_KEY";

    /**
     * 初始化方法 用于在application中调用
     * @param sharedPreferences
     */
    public static void initSp(SharedPreferences sharedPreferences){
        sp= sharedPreferences;
        spUtils = new SPUtils();
    }

    public static SPUtils getInstance(){
        return spUtils;
    }

    public List<MPageResponse<MM131Article>> getArticleCount(){
//        SharedPreferences sharedPreferences = spThread.get();
        String string = sp.getString(ARTICLE_KEY, "");
        if ("".equals(string)) return null;
        List<MPageResponse<MM131Article>> allMM131Article = GsonUtils.getInstance().getAllMM131Article(string);
        return allMM131Article;
    }

    public MPageResponse<MM131Article> getCurrentArticle(){
//        SharedPreferences sharedPreferences = spThread.get();
        if (!sp.contains(ARTICLE_KEY)) return null;
        String string = sp.getString(ARTICLE_KEY, "");
        if ("".equals(string)) {
            return null;
        }
        MPageResponse<MM131Article> allMM131Article = GsonUtils.getInstance().getCurrentMaxIndexArticle(string);
        return allMM131Article;
    }

    public void addAricle(MPageResponse<MM131Article>  articles){
//        SharedPreferences sharedPreferences = spThread.get();
        if (!sp.contains(ARTICLE_KEY)){
            Stack<MPageResponse<MM131Article>> mPageResponses = new Stack<>();
            mPageResponses.add(articles);
            sp.edit().putString(ARTICLE_KEY,GsonUtils.getInstance().trasnlateToString(mPageResponses)).apply();
        }else {
            String string = sp.getString(ARTICLE_KEY, "");
            Stack<MPageResponse<MM131Article>> allMM131Article = GsonUtils.getInstance().getAllMM131Article(string);
            allMM131Article.push(articles);
        }

    }

    public void removeAllData(){
//        SharedPreferences sharedPreferences = spThread.get();
        if (!sp.contains(ARTICLE_KEY)) return;
        String string = sp.getString(ARTICLE_KEY, "");
        if ("".equals(string)) {
            return;
        }

        sp.edit().remove(ARTICLE_KEY).commit();
    }

}
