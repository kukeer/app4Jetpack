package com.ws.hugs.common.utils;

import android.graphics.Bitmap;

import androidx.annotation.UiThread;

public interface FTPCallback {
    @UiThread
    void onResponse(Bitmap bitmap);
}
