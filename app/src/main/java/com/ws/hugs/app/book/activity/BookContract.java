package com.ws.hugs.app.book.activity;

import com.ws.hugs.BasePresenter;
import com.ws.hugs.BaseView;
import com.ws.hugs.data.remote.Book;

import java.util.List;

public interface BookContract {

    public interface IBookView {
        void loadBook(List<Book> list);

        void showLoading();

        void removeLoading();
    }
    public interface IBookPresenter extends BasePresenter<IBookView>{
        void loadBookList(boolean loadmore);
        void searchBook(String keyWord,int cate);

        void loadBookFromDB(int offset);


        void clearDBData();
        void clearDBDataByPagIndex();
    }
}
