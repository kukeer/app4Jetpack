package com.ws.hugs.app.picture.adapter.binding;

import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.ws.hugs.R;


public class MM131BindingAdapter {

    private static final String TAG = MM131BindingAdapter.class.getClass().getSimpleName();

    @BindingAdapter("mm131image")
    public static void setImage(ImageView image, String imageUrl){
        Log.i(TAG,"即将下载图片"+imageUrl);
        if (!TextUtils.isEmpty(imageUrl)){


            Log.i(TAG,"正在设置图片 "+"http://www.zzhiot.top:9999/getPicByThemeId?id="+imageUrl);
            if (imageUrl.equals("0")){
                Glide.with(image).load(R.mipmap.l2).into(image);
            }else{
                Glide.with(image).load("http://www.zzhiot.top:9999/getPicByThemeId?id="+imageUrl).timeout(1000*15).error(R.mipmap.l2).into(image);
            }

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
    @BindingAdapter("commonImage")
    public static void setCommonImage(ImageView image, String imageUrl){
        Log.i(TAG,"即将下载图片"+imageUrl);
        Glide.with(image).load(imageUrl).timeout(1000*15).error(R.mipmap.l2).into(image);
    }
    @BindingAdapter("mm131pic")
    public static void setImage1(ImageView image, String imageUrl){
        Log.i(TAG,"即将下载图片"+imageUrl);
        if (!TextUtils.isEmpty(imageUrl)){


            Log.i(TAG,"正在设置图片 "+"http://www.zzhiot.top:9999/getPicById?id="+imageUrl);

            Glide.with(image).load("http://www.zzhiot.top:9999/getPicById?id="+imageUrl).error(R.mipmap.l2).into(image);

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
