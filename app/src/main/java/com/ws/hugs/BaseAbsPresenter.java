package com.ws.hugs;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BaseAbsPresenter<T>{

    Reference<T> tReference;


    public void attachView(T view) {
        tReference = new WeakReference<T>(view);
    }

    public T getView() {
        return tReference.get();
    }

    public boolean isViewAttached() {
        return tReference!=null && tReference.get()!=null;
    }

    public void detachView() {
        if (tReference == null) return;

        tReference.clear();
        tReference = null;
    }
}
