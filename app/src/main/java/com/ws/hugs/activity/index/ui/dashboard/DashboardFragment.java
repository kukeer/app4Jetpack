package com.ws.hugs.activity.index.ui.dashboard;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ws.hugs.R;
import com.ws.hugs.app.picture.acticity.search.SearchActivity;
import com.ws.hugs.common.view.SimpleSearchBar;
import com.ws.hugs.service.MyService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


public class DashboardFragment extends Fragment {


    int currentIndex = -1;
    TextView currentTextView;
    HorizontalScrollView horizontalScrollView;
    private ArrayList<String> data = new ArrayList<>();
    LinearLayout layout;



    SimpleSearchBar editText;
    private String cities[] = new String[]{"London", "Bangkok", "Paris", "Dubai", "Istanbul", "New York",
            "Singapore", "Kuala Lumpur", "Hong Kong", "Tokyo", "Barcelona",
            "Vienna", "Los Angeles", "Prague", "Rome", "Seoul", "Mumbai", "Jakarta",
            "Berlin", "Beijing", "Moscow", "Taipei", "Dublin", "Vancouver"};
    //    private DashboardViewModel dashboardViewModel;
    private String TAG = getClass().getSimpleName();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        EventBus.getDefault().register(this);
        Intent intent = new Intent(getContext(), MyService.class);
        if (!isWorked(MyService.class.getName())) {
            getActivity().startService(new Intent(getContext(), MyService.class));
        }

        Collections.addAll(data, cities);


        horizontalScrollView = root.findViewById(R.id.scroller_h);
        layout = root.findViewById(R.id.linearLayout);


        editText = root.findViewById(R.id.msearch_bar);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });

        horizontalScrollView.setSmoothScrollingEnabled(false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(20, 10, 20, 10);

        for (AtomicInteger i = new AtomicInteger(0); i.get() < data.size(); i.addAndGet(1)) {
            TextView textView = new TextView(getContext());
            textView.setText(data.get(i.get()));
            textView.setTextColor(Color.WHITE);
            textView.setLayoutParams(layoutParams);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TAG", "当前view是 + " + textView.getText());

                    if (currentIndex != -1) {
                        showSmallText(currentTextView);
                        showFocusText(textView);
                    }
                    currentTextView = textView;
                    currentIndex = i.get();
                }
            });
            layout.addView(textView);
            layout.invalidate();

        }
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    private void showSmallText(TextView view) {
        view.setTextSize(13.0f);
        view.setTextColor(Color.WHITE);
    }

    private void showFocusText(TextView view) {
        view.setTextSize(18.0f);
        view.setTextColor(Color.YELLOW);
    }

    public boolean isWorked(String className) {
        ActivityManager myManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(30);
        for (int i = 0; i < runningService.size(); i++) {
            Log.d(TAG, "service name is " + runningService.get(i).service.getClassName().toString());
            if (runningService.get(i).service.getClassName().toString().equals(className)) {
                return true;
            }
        }
        return false;
    }
}
