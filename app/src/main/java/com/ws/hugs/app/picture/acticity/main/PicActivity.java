package com.ws.hugs.app.picture.acticity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.qmuiteam.qmui.widget.tab.QMUITabSegment;
import com.ws.hugs.R;


public class PicActivity extends AppCompatActivity {


    QMUITabSegment tabSegment;
    ViewPager2 viewPager2;
//    List<VideoViewModel> list = new ArrayList<VideoViewModel>();
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