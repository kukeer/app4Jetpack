package com.ws.hugs.app.picture.adapter;

import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ws.hugs.data.model.BannerImage;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

public class ImageAdapter extends BannerAdapter<BannerImage, ImageAdapter.BannerViewHolder> {

    private String TAG = getClass().getSimpleName();

    public ImageAdapter(List<BannerImage> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
    }



    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"newUrl onCreateHolder");
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, BannerImage data, int position, int size) {
        StringBuilder stringBuilder = new StringBuilder(data.getImgUrl());
        int i = stringBuilder.lastIndexOf("/");
        stringBuilder.replace(i,i,"/");
        String replace = stringBuilder.toString().replace(".jpg", ".html");

//        GlideUrl newUrl= new GlideUrl(stringBuilder.toString(), new LazyHeaders.Builder()
//                .addHeader("referer",replace)
//                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36").build());
//
//        Log.i(TAG,"newUrl "+newUrl.toStringUrl()+" "+newUrl.getHeaders().get("refer"));
//        Glide.with(holder.imageView)
//                .load(newUrl)
//                .error(R.mipmap.search)
//                .into(holder.imageView);
//        holder.imageView.setImageBitmap(BitmapFactory.decodeResource(holder.imageView.getResources(),data.getResId()));
//        holder.imageView.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG,"点击了轮播图 "+data.getUrl());
//            }
//        });

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
