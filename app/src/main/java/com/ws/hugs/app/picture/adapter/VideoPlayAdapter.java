package com.ws.hugs.app.picture.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

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
import com.ws.hugs.app.picture.data.db.MM131VideoArticleModel;
import com.ws.hugs.common.utils.DefaultHttpsDataSourceFactory;
import com.ws.hugs.databinding.VideoplayBinding;

import org.jetbrains.annotations.NotNull;


public class VideoPlayAdapter extends PagedListAdapter<MM131VideoArticleModel, VideoPlayAdapter.VideoHolder> {

    Context context;

    private final String TAG = this.getClass().getSimpleName();

    public static DiffUtil.ItemCallback DIFF_CALLBACK = new DiffUtil.ItemCallback<MM131VideoArticleModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull MM131VideoArticleModel oldItem, @NonNull MM131VideoArticleModel newItem) {
            return oldItem.videoCode == newItem.videoCode;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MM131VideoArticleModel oldItem, @NonNull MM131VideoArticleModel newItem) {
            return oldItem.videoCode == newItem.videoCode;
        }

    };

    public VideoPlayAdapter(@NotNull Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VideoplayBinding videoplayBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.videoplay,parent,false);
//        View inflate = LayoutInflater.from().inflate(R.layout.videoplay, null,false);
        Log.i(TAG,"onCreateViewHolder 的位置是 ???");
        return new VideoHolder(videoplayBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {

        Log.i(TAG,"onBindViewHolder 的位置是 "+position);
        if (!holder.isReady()) {

            MM131VideoArticleModel item = getItem(position);
            holder.prepare("https://www.zzhiot.top:10000/play/getPlayResource/" + item.videoCode);
        }
    }

    class VideoHolder extends RecyclerView.ViewHolder {
        VideoplayBinding videoplayBinding;
        PlayerView playerView;
        SimpleExoPlayer simpleExoPlayer;
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

        // 创建轨道选择器实例
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        public VideoHolder(@NonNull VideoplayBinding itemView) {
            super(itemView.getRoot());
            videoplayBinding = itemView;
            Log.i(TAG,"video databinding内的 playerview "+videoplayBinding.exoPlayer);
            playerView = videoplayBinding.exoPlayer;
        }

        public boolean isReady() {
            return simpleExoPlayer != null;
        }

        public void prepare(String url) {
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
            playerView.setPlayer(simpleExoPlayer);

            DataSource.Factory datasourceFactory = new DefaultDataSourceFactory(context, (TransferListener<? super DataSource>) bandwidthMeter,
                    new DefaultHttpsDataSourceFactory(Util.getUserAgent(context, context.getString(R.string.app_name)), context.getAssets()));
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource m = new ExtractorMediaSource(Uri.parse(url),
                    datasourceFactory, extractorsFactory, null, null);
            Log.i(TAG,"准备就绪 "+url);
            simpleExoPlayer.prepare(m);
        }

        public void play(){
            simpleExoPlayer.setPlayWhenReady(true);
        }

        public void replay(){
            simpleExoPlayer.stop(true);
        }

        public void removePlay() {

            playerView.setPlayer(null);
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

}