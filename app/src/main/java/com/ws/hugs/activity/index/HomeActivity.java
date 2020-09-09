package com.ws.hugs.activity.index;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ws.hugs.R;
import com.ws.hugs.adapter.ImageAdapter;
import com.ws.hugs.common.utils.PhoneInfo;
import com.ws.hugs.data.model.BannerImage;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {

//    List<BannerImage> bannerImageList = new ArrayList<>();
//    Banner banner;
    private void addTestData(int size){

        if(size<0 || size >30) return;
        Random random = new Random(20);

//        bannerImageList.add(new BannerImage("https://img1.mmmw.net/pic/5563/"+1+".jpg", UUID.randomUUID().toString(), R.mipmap.l1));
//        bannerImageList.add(new BannerImage("https://img1.mmmw.net/pic/5563/"+2+".jpg", UUID.randomUUID().toString(), R.mipmap.l2));
//        bannerImageList.add(new BannerImage("https://img1.mmmw.net/pic/5563/"+3+".jpg", UUID.randomUUID().toString(), R.mipmap.l3));
//        bannerImageList.add(new BannerImage("https://img1.mmmw.net/pic/5563/"+4+".jpg", UUID.randomUUID().toString(), R.mipmap.l4));
//        bannerImageList.add(new BannerImage("https://img1.mmmw.net/pic/5563/"+5+".jpg", UUID.randomUUID().toString(), R.mipmap.l5));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        getSupportActionBar().hide();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        banner.destroy();
    }
}
