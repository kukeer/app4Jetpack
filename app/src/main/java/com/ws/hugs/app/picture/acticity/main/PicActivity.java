package com.ws.hugs.app.picture.acticity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ws.hugs.R;
import com.ws.hugs.app.picture.acticity.main.fragment.ImageViewerFragment;
import com.ws.hugs.app.picture.acticity.main.fragment.VideoFragment;


public class PicActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();
    MaterialButtonToggleGroup group;
    Button imgButton;
    Button videoButton;
    boolean isImage = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pic);

        initView();
        setOnclickEvent();
    }

    private void setOnclickEvent() {


    }

    private void initView() {
        group = findViewById(R.id.button_group);
        imgButton = findViewById(R.id.img_btn);
        videoButton = findViewById(R.id.video_btn);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImage) return;
                isImage = !isImage;
                getSupportFragmentManager().beginTransaction().replace(R.id.change_fragment,new ImageViewerFragment()).commitNowAllowingStateLoss();
            }
        });

        videoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isImage) return;
                isImage = !isImage;
                getSupportFragmentManager().beginTransaction().replace(R.id.change_fragment,VideoFragment.newInstance()).commitNowAllowingStateLoss();
            }
        });
        group.check(R.id.img_btn);
    }

}