package com.ws.hugs.app.book.activity;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.RoomOpenHelper;

import com.ws.hugs.api.BookRequestCenter;
import com.ws.hugs.api.RequestManager;
import com.ws.hugs.common.utils.GsonUtils;
import com.ws.hugs.common.utils.SPUtils;
import com.ws.hugs.data.remote.Book;
import com.ws.hugs.data.remote.response.MResponse;
import com.ws.hugs.data.remote.response.Page;
import com.ws.hugs.db.HugsDatabase;
import com.ws.hugs.db.book.BookDao;
import com.ws.hugs.db.book.tb.BookModel;
import com.xcheng.retrofit.Call;
import com.xcheng.retrofit.Callback;
import com.xcheng.retrofit.HttpError;
import com.xcheng.retrofit.RetrofitFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookPresenter implements BookContract.IBookPresenter{


    private BookContract.IBookView bookView;

    private final BookDao bookDao;

    private final SingleDataManager singleDownloadManager = new SingleDataManager();


    public BookPresenter(BookContract.IBookView bookView) {
        this.bookView = bookView;
        bookDao = HugsDatabase.getInstance((Context)bookView).bookDao();
    }

    @Override
    public void loadBookList(boolean loadmore) {
        singleDownloadManager.download(loadmore);
    }

    @Override
    public void searchBook(String keyWord, int cate) {

    }

    @Override
    public void loadBookFromDB(int offset) {

    }

    @Override
    public void clearDBData() {

    }

    @Override
    public void clearDBDataByPagIndex() {

    }



    @Override
    public void attachView(BookContract.IBookView v) {
        this.bookView =v;
    }

    @Override
    public BookContract.IBookView getView() {
        return bookView;
    }

    @Override
    public boolean isViewAttached() {
        return false;
    }

    @Override
    public void detachView() {
        bookView =null;
    }

    private class SingleDataManager{

        AtomicBoolean IS_LOADING = new AtomicBoolean(false);

        int minIndex=0;
        int maxIndex=0;

        SPUtils spUtils = SPUtils.getInstance();

        public SingleDataManager(){

            minIndex = spUtils.getBookDataMaxIndex();
            maxIndex = spUtils.getBookDataMinIndex();
        }


        public boolean download(boolean loadMore) {

            if (IS_LOADING.get()){
                return false;
            }

            if (minIndex==0 && !loadMore){
                //可能是重新加载
                return false;
            }
            IS_LOADING.set(true);

            bookView.showLoading();

            Call<MResponse<Page<Book>>> bookList = RetrofitFactory.create(BookRequestCenter.class).getBookList(maxIndex);
            bookList.enqueue(new Callback<MResponse<Page<Book>>>() {
                @Override
                public void onStart(Call<MResponse<Page<Book>>> call) {

                }

                @NonNull
                @Override
                public HttpError parseThrowable(Call<MResponse<Page<Book>>> call, Throwable t) {
                    return null;
                }

                @NonNull
                @Override
                public MResponse<Page<Book>> transform(Call<MResponse<Page<Book>>> call, MResponse<Page<Book>> pageMResponse) {
                    return pageMResponse;
                }

                @Override
                public void onError(Call<MResponse<Page<Book>>> call, HttpError error) {

                }

                @Override
                public void onSuccess(Call<MResponse<Page<Book>>> call, MResponse<Page<Book>> pageMResponse) {
                    List<Book> records = pageMResponse.getT().getRecords();

                    bookView.loadBook(records);

                    List list = GsonUtils.getInstance().jsonToList(records, BookModel[].class);
                    bookDao.insertArticles(list);
                }

                @Override
                public void onCompleted(Call<MResponse<Page<Book>>> call, @Nullable Throwable t) {
                    if (loadMore) spUtils.saveBookMaxDataIndex(++maxIndex);

                    if (!loadMore) spUtils.saveBookMinDataIndex(--minIndex);

                    IS_LOADING.set(false);
                    bookView.removeLoading();
                }
            });
            return true;
        }

        public void deleteData(){

            if (minIndex == maxIndex) return;
            spUtils.saveBookMinDataIndex(++minIndex);
        }

        public void clear(){
            minIndex = 0;
            maxIndex = 0;
            spUtils.saveBookDataIndex(minIndex,maxIndex);

            bookDao.clear();
        }
    }
}
