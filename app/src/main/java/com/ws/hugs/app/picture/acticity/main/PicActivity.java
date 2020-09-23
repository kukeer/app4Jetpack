package com.ws.hugs.app.picture.acticity.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.tab.QMUITabSegment;
import com.ws.hugs.R;
import com.ws.hugs.app.picture.acticity.main.fragment.VideoViewModel;
import com.ws.hugs.app.picture.data.db.MM131ArticleModel;
import com.ws.hugs.app.picture.data.db.MM131VideoArticleModel;
import com.ws.hugs.app.picture.data.remote.VideoArticleDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PicActivity extends AppCompatActivity {


    QMUITabSegment tabSegment;
    ViewPager2 viewPager2;
    List<VideoViewModel> list = new ArrayList<VideoViewModel>();
//    private QDItemDescription mQDItemDescription;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pic);

        initView();
    }

    private void initView() {
        viewPager2 = findViewById(R.id.mm131_viewpage2);
        tabSegment = findViewById(R.id.tabSegment);
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
    }

}