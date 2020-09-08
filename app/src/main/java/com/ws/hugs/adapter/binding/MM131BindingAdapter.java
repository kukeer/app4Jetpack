package com.ws.hugs.adapter.binding;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ws.hugs.R;
import com.ws.hugs.api.RequestCenter;
import com.ws.hugs.api.RequestManager;
import com.ws.hugs.common.utils.FTPCallback;
import com.ws.hugs.common.utils.FTPManager;
import com.ws.hugs.common.utils.SingleWorker;

import androidx.annotation.UiThread;
import androidx.databinding.BindingAdapter;


public class MM131BindingAdapter {

    private static final String TAG = MM131BindingAdapter.class.getClass().getSimpleName();
    @BindingAdapter("mm131image")

    public static void setImage(ImageView image, String imageUrl){
        Log.i(TAG,"即将下载图片"+imageUrl);
        if (!TextUtils.isEmpty(imageUrl)){

            Glide.with(image).load("http://139.198.191.101:9999/getPicByThemeId?id="+imageUrl).into(image);

//            SingleWorker singleWorker = SingleWorker.getInstance();
//            singleWorker.execute(singleWorker.generateTask(imageUrl, new FTPCallback() {
//
//                @Override
//                public void onResponse(Bitmap bitmap) {
//                    image.setImageBitmap(bitmap);
//                }
//            }));
        }else{
            image.setImageBitmap(BitmapFactory.decodeResource(image.getContext().getResources(), R.mipmap.l1));
        }
    }

}
