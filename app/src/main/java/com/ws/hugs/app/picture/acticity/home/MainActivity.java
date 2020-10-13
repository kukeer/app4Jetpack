package com.ws.hugs.app.picture.acticity.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

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
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ws.hugs.R;
import com.ws.hugs.api.RequestManager;
import com.ws.hugs.app.picture.adapter.ImageAdapter;
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
import java.util.concurrent.atomic.AtomicInteger;
//implements GestureDetector.OnGestureListener
public class MainActivity extends AppCompatActivity
        {
    private String TAG = getClass().getSimpleName();

    LifecycleProvider provider = AndroidLifecycle.createLifecycleProvider(this);

    private ArrayList<String> data = new ArrayList<>();
    View topLan;
    TextView title;
    ImageView chart;
    ImageView collect;

//    GestureDetector gestureDetector = new GestureDetector(this);
    VelocityTracker velocityTracker = VelocityTracker.obtain();
    List<Bitmap> list = new ArrayList<Bitmap>();
    String currentMessage = null;
    int time = 0;
    //    ImageView imageView;
    int currentIndex = 0;
    boolean showIndex = false;
    ViewPager2 viewPager;
    ImageAdapter imageAdapter;
    TextView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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


        viewPager.setAdapter(new ImageAdapter(list));
        viewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {


            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int currentItem = viewPager.getCurrentItem();
                int count = viewPager.getAdapter().getItemCount();
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
                    for (AtomicInteger i = new AtomicInteger(0); i.get() < (collect.size()); i.addAndGet(1)) {
                        Call<ResponseBody> bodyCall = RequestManager.getRequestCenter().getPicById(collect.get(i.get()).getPicId() + "");

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
//                                viewPager.getAdapter().
                                viewPager.getAdapter().notifyItemChanged(list.size());
//                                viewPager.getAdapter().notifyDataSetChanged();
                            }

                            @Override
                            public void onCompleted(Call<ResponseBody> call, @Nullable Throwable t) {

                            }
                        });
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }


                }

                @Override
                public void onCompleted(Call<MResponse<MM131Abum>> call, @Nullable Throwable t) {

                }


            });
        }
    }





}

