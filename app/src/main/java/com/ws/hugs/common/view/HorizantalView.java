package com.ws.hugs.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import androidx.core.view.ViewCompat;

public class HorizantalView extends ViewGroup {


    private int lastIntercceptX;
    private int lastIntercceptY;
    private int lastX;
    private int lastY;
    int childWidth = 0;
    private VelocityTracker tracker;
    private Scroller scroller;
    private int currentIndex = 0;
    public HorizantalView(Context context) {
        this(context,null);
    }

    public HorizantalView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizantalView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public HorizantalView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        scroller = new Scroller(getContext());
        tracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();


        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x-lastIntercceptX;
                int deltaY = y-lastIntercceptY;

                if (Math.abs(deltaX) - Math.abs(deltaY) > 0){
                    intercept = true;
                }

                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - currentIndex*childWidth;
                if (Math.abs(distance)>childWidth/2){
                    if (distance>0){
                        currentIndex++;
                    }else{
                        currentIndex--;
                    }
                }else {
                    tracker.computeCurrentVelocity(1000);
                    float xV = tracker.getXVelocity();

                    if (Math.abs(xV)>50){
                        if (xV>0){
                            currentIndex--;
                        }else{
                            currentIndex++;
                        }
                    }
                }
                currentIndex = currentIndex<0?0:currentIndex>getChildCount()-1?getChildCount()-1:currentIndex;

                smoothScrollTo(currentIndex*childWidth,0);
                tracker.clear();
                break;
        }

        lastX = x;
        lastY = y;
        lastIntercceptY =y;
        lastIntercceptX =x;
        return intercept;
    }

    private void smoothScrollTo(int destX, int destY){
        scroller.startScroll(getScrollX(),getScrollY(),destX-getScrollX(),destY-getScrollY(),1000);
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        int childCount = getChildCount();
        int left = 0;

        ViewCompat.getMinimumHeight(this);
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                childWidth = width;
                child.layout(left, 0, left + width, child.getMeasuredHeight());
                left += width;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST
                && heightMode == MeasureSpec.AT_MOST) {

            View childAt = getChildAt(0);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();

            setMeasuredDimension(measuredWidth * getChildCount(), measuredHeight * getChildCount());

        } else if (widthMode == MeasureSpec.AT_MOST) {
            int childWidth = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(childWidth * getChildCount(), heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            int childHeight = getChildAt(0).getMeasuredWidth();
            setMeasuredDimension(widthSize, childHeight * getChildCount());
        }


    }
}
