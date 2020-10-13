package com.ws.hugs.app.book.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.ws.hugs.BaseMVPActivity;
import com.ws.hugs.BasePresenter;
import com.ws.hugs.R;
import com.ws.hugs.api.BookRequestCenter;
import com.ws.hugs.api.RequestManager;
import com.ws.hugs.app.book.adapter.BookListAdapter;
import com.ws.hugs.common.utils.SPUtils;
import com.ws.hugs.data.remote.Book;
import com.ws.hugs.data.remote.response.MResponse;
import com.ws.hugs.data.remote.response.Page;
import com.xcheng.retrofit.Call;
import com.xcheng.retrofit.Callback;
import com.xcheng.retrofit.HttpError;
import com.xcheng.retrofit.RetrofitFactory;

import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Retrofit;

public class BookListActivity extends BaseMVPActivity implements BookContract.IBookView {

    RecyclerView recyclerView;

    BookPresenter bookPresenter;

    SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        init();
    }

    @Override
    protected BasePresenter createPreSenter() {
        return new BookPresenter(this);
    }

    private void init() {
        recyclerView = findViewById(R.id.book_recy);
        smartRefreshLayout = findViewById(R.id.smart_refresh);




        recyclerView.setAdapter(new BookListAdapter(this));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    private void initView(){}

    private void initEvent(){

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });

    }
    @Override
    public void loadBook(List<Book> list) {
        BookListAdapter adapter = (BookListAdapter)recyclerView.getAdapter();
        int itemCount = adapter.getItemCount();
        adapter.bookList.addAll(list);
    }

    @Override
    public void showLoading() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                int itemCount = adapter.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (itemCount - lastVisibleItemPosition<10){
                    bookPresenter.loadBookList(true);
                }

                if (itemCount>90){
                    bookPresenter.clearDBDataByPagIndex();
                }
                if (firstVisibleItemPosition == 0){

                }
            }
        });
    }

    @Override
    public void removeLoading() {

    }




}