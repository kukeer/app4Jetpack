package com.ws.hugs.app.picture.acticity.videoplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import com.ws.hugs.R;
import com.ws.hugs.app.picture.adapter.VideoPlayAdapter;
import com.ws.hugs.app.picture.data.db.MM131VideoArticleModel;
import com.ws.hugs.common.utils.DefaultHttpsDataSourceFactory;
import com.ws.hugs.data.viewmodel.MM131VideoArticleViewModel;
import com.ws.hugs.databinding.VideoplayBinding;


public class VideoPlayActivity extends AppCompatActivity {

    private String TAG = VideoPlayActivity.class.getSimpleName();


    ViewPager2 viewPager2;

    VideoplayBinding videoplayBinding;
    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

    // 创建轨道选择器实例
    TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);



    String url = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        Intent intent = getIntent();
        long ik = intent.getExtras().getLong("ik",0L);
        url = "https://www.zzhiot.top:10000/play/getPlayResource/"+ik;

        playerView = findViewById(R.id.exo_player);

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        playerView.setPlayer(simpleExoPlayer);

        DataSource.Factory datasourceFactory = new DefaultDataSourceFactory(this, (TransferListener<? super DataSource>) bandwidthMeter,
                new DefaultHttpsDataSourceFactory(Util.getUserAgent(this, getString(R.string.app_name)),getAssets()));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource m = new ExtractorMediaSource(Uri.parse(url),
                datasourceFactory, extractorsFactory, null, null);
        Log.i(TAG,"准备就绪 "+url);
        simpleExoPlayer.prepare(m);
        simpleExoPlayer.setPlayWhenReady(true);
//        viewPager2 = findViewById(R.id.video_player_recy);
//        MM131VideoArticleViewModel model = new MM131VideoArticleViewModel(getApplication());
//        viewPager2.setAdapter(new VideoPlayAdapter(this));
//
//        model.mm131ArticleViewModelLiveData.observe(this, new Observer<PagedList<MM131VideoArticleModel>>() {
//            @Override
//            public void onChanged(PagedList<MM131VideoArticleModel> mm131VideoArticleModels) {
//                ((VideoPlayAdapter)viewPager2.getAdapter()).submitList(mm131VideoArticleModels);
//            }
//        });
//        final boolean[] needChange = {true};
//        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                Log.i(TAG,"当前的位置是 "+position);
////                ((VideoPlayAdapter)viewPager2.getAdapter()).get
////                PlayerView view = (PlayerView)viewPager2.getChildAt(position-1);
////                view.getPlayer().stop(true);
//                if (!needChange[0]){
//                    Log.i(TAG,"当前的数据总数 "+viewPager2.getAdapter().getItemCount());
//                    PlayerView view1 = (PlayerView)viewPager2.getChildAt(position).findViewById(R.id.exo_player);
//                    view1.getPlayer().setPlayWhenReady(true);
//                }else {
//                    needChange[0] =false;
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            int currentPosition = model.findCurrentPosition(ik);
//                            Log.i(TAG,"当前的数据总数 "+viewPager2.getAdapter().getItemCount());
//                            Log.i(TAG,"当前查找到的位置 "+currentPosition);
//                            if (viewPager2.getAdapter().getItemCount()!=0){
//                                viewPager2.setCurrentItem(currentPosition);
//                            }
//                            PlayerView view1 = (PlayerView)viewPager2.getChildAt(currentPosition).findViewById(R.id.exo_player);
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    view1.getPlayer().setPlayWhenReady(true);
//                                }
//                            });
//                        }
//                    }).start();
//                }

//                if (position>10){
//                    PlayerView removeView = (PlayerView)viewPager2.getChildAt(position-1);
//                    Player player = removeView.getPlayer();
//                    removeView.setPlayer(null);
//                    player.release();
//                    player =null;
//
//                }
//            }
//        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }




    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        playerView.setPlayer(null);
        simpleExoPlayer.release();
        simpleExoPlayer =null;

        super.onStop();


    }




    private class MyGestureListener implements GestureDetector.OnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            Log.i(this.getClass().getSimpleName(),"onDown");
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i(this.getClass().getSimpleName(),"onDown");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i(this.getClass().getSimpleName(),"onSingleTapUp");
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.i(this.getClass().getSimpleName(),"onScroll");
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i(this.getClass().getSimpleName(),"onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.i(this.getClass().getSimpleName(),"onFling x= "+velocityX+" y= "+velocityY);

            return false;
        }
    }
}