package com.ws.hugs;

import java.lang.ref.Reference;

public interface BasePresenter<T> {

    public void attachView(T v);

    public T getView();

    public boolean isViewAttached();

    public void detachView();

}
