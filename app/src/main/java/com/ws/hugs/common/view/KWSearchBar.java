package com.ws.hugs.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class KWSearchBar extends View {

    Paint paint;

    public KWSearchBar(@NonNull Context context) {
        super(context);
    }

    public KWSearchBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public KWSearchBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public KWSearchBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setStrokeWidth(0.0f);
//        paint.
    }
}
