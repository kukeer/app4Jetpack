package com.ws.hugs.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CheckBox extends View implements View.OnClickListener {

    Paint paint = new Paint();
    OnCheckedListener onCheckedListener;
    boolean isSelect = false;
    int size;


    public CheckBox(Context context) {
        super(context);
    }

    public CheckBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CheckBox(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnCheckedListener(OnCheckedListener onCheckedListener) {
        this.onCheckedListener = onCheckedListener;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(12);
        paint.setColor(Color.RED);
        if (isSelect){
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
        }else {
            paint.setStyle(Paint.Style.STROKE);
        }
        Path path = new Path();
        path.addArc(0,0,200,200,0,180);
        path.arcTo(200,0,400,200,0,180,true);
        canvas.drawPath(path,paint);
    }

    @Override
    public void onClick(View v) {
        isSelect = !isSelect;
        if (isSelect){
            onCheckedListener.onChange();
        }
    }
    public interface OnCheckedListener{
        void onChange();
    }
}
