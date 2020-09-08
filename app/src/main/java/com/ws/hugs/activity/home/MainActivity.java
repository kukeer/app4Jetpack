package com.ws.hugs.activity.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.ResponseBody;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.ws.hugs.R;
import com.ws.hugs.api.RequestManager;
import com.ws.hugs.data.event.EventMessage;
import com.ws.hugs.data.remote.MM131Abum;
import com.ws.hugs.data.remote.MM131Picture;
import com.ws.hugs.data.remote.response.MResponse;
import com.xcheng.retrofit.AndroidLifecycle;
import com.xcheng.retrofit.Call;
import com.xcheng.retrofit.Callback;
import com.xcheng.retrofit.HttpError;
import com.xcheng.retrofit.LifecycleProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener {
    private String TAG = getClass().getSimpleName();

    LifecycleProvider provider = AndroidLifecycle.createLifecycleProvider(this);

    private ArrayList<String> data = new ArrayList<>();
    View topLan;
    TextView title;
    ImageView chart;
    ImageView collect;

    GestureDetector gestureDetector = new GestureDetector(this);
    VelocityTracker velocityTracker = VelocityTracker.obtain();
    List<Bitmap> list = new ArrayList<Bitmap>();
    String currentMessage = null;
    int time = 0;
    //    ImageView imageView;
    int currentIndex = 0;
    boolean showIndex = false;
    QMUIViewPager viewPager;
    QMUIPagerAdapter qmuiPagerAdapter;
    TextView back;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        velocityTracker.addMovement(event);
        int xV = (int) velocityTracker.getXVelocity();
        int xY = (int) velocityTracker.getXVelocity();

        Scroller s = new Scroller(getBaseContext());
        Log.d(TAG, "xV " + xV + " xY " + xY);
        View view;
        ViewGroup viewg;
        boolean b = gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setTheme(R.style.SplashTheme);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        viewPager = findViewById(R.id.mm131_viewpage);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "点击退出gai activity");
                finishAndRemoveTask();
            }
        });

        qmuiPagerAdapter = new QMUIPagerAdapter() {


            @NonNull
            @Override
            protected Object hydrate(@NonNull ViewGroup container, int position) {
                ImageView imageView = new ImageView(container.getContext());
//                imageView.set

                return imageView;
            }

            @Override
            protected void populate(@NonNull ViewGroup container, @NonNull Object item, int position) {
                ImageView imageView = (ImageView) item;
                Glide.with(container.getContext()).load(list.get(position)).into(imageView);
//                Log.i(TAG,"imageView 开始加载 "+"http://139.198.191.101:9999/getPicById?id="+list.get(position).getPicId());
//                Glide.with(container.getContext()).load("http://139.198.191.101:9999/getPicById?id="+list.get(position).getPicId());


                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i(TAG, "点击了 ");
                        if (showIndex) {
//            v.gone();
                            topLan.setVisibility(View.GONE);
                            showIndex = false;
                        } else {
                            topLan.setVisibility(View.VISIBLE);
//                            currentIndex = getItemPosition(item);
                            title.setText("第 " + currentIndex + " 张");
                            showIndex = true;
                        }
                    }
                });
                container.addView(imageView);

            }

            @Override
            protected void destroy(@NonNull ViewGroup container, int position, @NonNull Object object) {
                ImageView imageView = (ImageView) object;

                container.removeView(imageView);
            }


            @Override
            public int getCount() {
                return list.size();
            }


            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }


        };

        viewPager.setAdapter(qmuiPagerAdapter);
        viewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {


            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int currentItem = viewPager.getCurrentItem();
                int count = viewPager.getAdapter().getCount();
                if (currentItem != currentIndex) {
                    currentIndex = currentItem;
                    title.setText(("第 " + currentIndex + " 张"));
                }

                Log.i(TAG, "当前的 current为 " + currentItem + " 当前的总数为 " + count);

            }

        });
//        qmuiPagerAdapter
        boolean canUseHardware = Build.VERSION.SDK_INT >= 21;

        topLan = findViewById(R.id.top_lan);
        title = findViewById(R.id.pic_index);
        topLan.setVisibility(View.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        velocityTracker.clear();
        velocityTracker.recycle();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        viewPager.getAdapter().notifyDataSetChanged();
    }

    @Subscribe(sticky = true)
    public void test(EventMessage eventMessage) {
        System.out.println("activity主线程接受到消息 " + eventMessage);
        if (eventMessage.getType() == 100) {
            Log.i(TAG, "即将发送请求  " + eventMessage);
            if (currentMessage == null || !currentMessage.equals(eventMessage.getMessage())) {
                time = 0;
                currentMessage = eventMessage.getMessage();
            }
            String message = eventMessage.getMessage();

            Call<MResponse<MM131Abum>> imageList = RequestManager.getRequestCenter().getImageList(message, 0);

            imageList.enqueue(new Callback<MResponse<MM131Abum>>() {
                @Override
                public void onStart(Call<MResponse<MM131Abum>> call) {

                }

                @NonNull
                @Override
                public HttpError parseThrowable(Call<MResponse<MM131Abum>> call, Throwable t) {
                    return null;
                }

                @NonNull
                @Override
                public MResponse<MM131Abum> transform(Call<MResponse<MM131Abum>> call, MResponse<MM131Abum> mm131AbumMResponse) {
                    return mm131AbumMResponse;
                }

                @Override
                public void onError(Call<MResponse<MM131Abum>> call, HttpError error) {

                }

                @Override
                public void onSuccess(Call<MResponse<MM131Abum>> call, MResponse<MM131Abum> mm131AbumMResponse) {
                    MM131Abum t = mm131AbumMResponse.getT();
                    List<MM131Picture> collect = t.getList();
                    for (int i = 0; i < collect.size(); i++) {
                        Call<ResponseBody> bodyCall = RequestManager.getRequestCenter().getPicById(collect.get(i).getPicId() + "");
                        bodyCall.enqueue(new Callback<ResponseBody>(){

                            @Override
                            public void onStart(Call<ResponseBody> call) {

                            }

                            @NonNull
                            @Override
                            public HttpError parseThrowable(Call<ResponseBody> call, Throwable t) {
                                return new HttpError(t.getMessage());
                            }

                            @NonNull
                            @Override
                            public ResponseBody transform(Call<ResponseBody> call, ResponseBody responseBody) {
                                return responseBody;
                            }

                            @Override
                            public void onError(Call<ResponseBody> call, HttpError error) {

                            }

                            @Override
                            public void onSuccess(Call<ResponseBody> call, ResponseBody response) {
                                InputStream inputStream = response.byteStream();
                                list.add(BitmapFactory.decodeStream(inputStream));
                                viewPager.getAdapter().notifyDataSetChanged();
                            }

                            @Override
                            public void onCompleted(Call<ResponseBody> call, @Nullable Throwable t) {

                            }
                        });
                    }

                }

                @Override
                public void onCompleted(Call<MResponse<MM131Abum>> call, @Nullable Throwable t) {

                }


            });
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }



}

