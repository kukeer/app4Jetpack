package com.ws.hugs.common.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ws.hugs.R;

import androidx.annotation.Nullable;

public class SimpleSearchBar extends View{

    Context context;

    ImageView imageView;

    Paint paint;


    public SimpleSearchBar(Context context) {
        super(context);
        this.context = context;
    }

    public SimpleSearchBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SimpleSearchBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public SimpleSearchBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
//        canvas.drawRoundRect(0,50,300,50,1000,1000,paint);
        canvas.save();

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.search);
        canvas.scale(0.455f,0.45f,10+bitmap.getWidth()/2,0);
        canvas.drawBitmap(bitmap,0,35,paint);
        paint.setColor(Color.GRAY);
        paint.setTextSize(60);
        canvas.restore();
        canvas.drawText("请输入...",120,70,paint);

////        path.moveTo(100,100);
//        path.addArc(100,0,300,100,0,180);
//        path.arcTo(300,0,500,100,0,180,false);
//        path.lineTo(300,200);
//        path.close();
//        canvas.drawPath(path,paint);

    }

}
