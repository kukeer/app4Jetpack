package com.ws.hugs.app.picture.acticity.videoplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayerFactory;
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
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Util;
import com.ws.hugs.R;
import com.ws.hugs.common.utils.DefaultHttpsDataSourceFactory;



public class VideoPlayActivity extends AppCompatActivity {


    View parent;

    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
    TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

    GestureDetector detector;

    // 创建轨道选择器实例
    TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        playerView = findViewById(R.id.exo_player);
        parent = findViewById(R.id.video_parent_view);
        detector = new GestureDetector(this,new MyGestureListener());
        playerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                detector.onTouchEvent(event);
                return false;
            }
        });
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
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this,trackSelector);
        playerView.setPlayer(simpleExoPlayer);

        DataSource.Factory datasourceFactory = new DefaultDataSourceFactory(this, (TransferListener<? super DataSource>) bandwidthMeter,
                new DefaultHttpsDataSourceFactory(Util.getUserAgent(this,getString(R.string.app_name)), getAssets()));
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();


        MediaSource m = new ExtractorMediaSource(Uri.parse("https://www.zzhiot.top:10000/play/getPlayResource/1307079769002577922"),
                datasourceFactory, extractorsFactory, null, null);

        simpleExoPlayer.prepare(m);

        simpleExoPlayer.setPlayWhenReady(true);

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