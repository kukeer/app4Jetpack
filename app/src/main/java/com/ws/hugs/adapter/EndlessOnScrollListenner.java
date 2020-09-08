package com.ws.hugs.adapter;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EndlessOnScrollListenner extends RecyclerView.OnScrollListener {

    private String TAG = getClass().getSimpleName();

    LinearLayoutManager linearLayoutManager;

    private int totalItemCount;

    private int previousTotal;

    private int visibleitemCount;

    private int firstVisableItem;

    public boolean loading = true;

    private int currentpage=1;

    private int deletIndex=-1;
    public EndlessOnScrollListenner(LinearLayoutManager linearLayoutManager){
        this.linearLayoutManager =linearLayoutManager;
    }


    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //可见的 item数量
        visibleitemCount = recyclerView.getChildCount();
        //总共的数量
        totalItemCount = recyclerView.getAdapter().getItemCount();
        //屏幕上第一个可以看见的item
        firstVisableItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (loading){

            if (totalItemCount> previousTotal){
                loading = false;
                previousTotal = totalItemCount;
            }

        }
        //如果当前未处于加载状态 且 总数量减去屏幕上可见数量小于等于第一个可见的item位置
//        System.out.println("结果为 "+(totalItemCount-visibleitemCount)+"<="+firstVisableItem +" loading "+loading);
        if ((totalItemCount-visibleitemCount<=firstVisableItem)){

//            System.out.println("当前状态为 onLoadMore");
            onLoadMore(currentpage);
            loading = false;
        }
//        if (firstVisableItem==)
    }

    public abstract void onLoadMore(int currentpage);

    public void addDeleteIndex(){

        deletIndex++;
        Log.i(TAG,"当前的删除index为 "+deletIndex);

    }

}
