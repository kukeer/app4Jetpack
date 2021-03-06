package com.ws.hugs.common.utils;

import com.google.gson.Gson;
import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.data.remote.response.MPageResponse;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class GsonUtils {

    private static ThreadLocal<Gson> threadLocal  = new ThreadLocal<>();
    private static GsonUtils gsonUtils =null;


    public static GsonUtils getInstance(){
        if (gsonUtils ==null){
            gsonUtils = new GsonUtils();
            threadLocal.set(new Gson());
        }
        return gsonUtils;
    }

    public String trasnlateToString(Object obj){
        return threadLocal.get().toJson(obj);
    }

    public Stack<MPageResponse<MM131Article>> getAllMM131Article(String data){
        Stack mPageResponse = threadLocal.get().fromJson(data, Stack.class);
        return mPageResponse;
    }


    public MPageResponse<MM131Article> getCurrentMaxIndexArticle(String data){
        Stack<MPageResponse<MM131Article>> mPageResponse = threadLocal.get().fromJson(data, Stack.class);
        if (mPageResponse.isEmpty()){
            return null;
        }
        MPageResponse<MM131Article> mm131ArticleMPageResponse = mPageResponse.pop();

        return mm131ArticleMPageResponse;
    }


}
