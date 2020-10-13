package com.ws.hugs.app.picture.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ws.hugs.R;
import com.ws.hugs.app.picture.acticity.videoplayer.VideoPlayActivity;
import com.ws.hugs.db.mm131.tb.MM131VideoArticleModel;
import com.ws.hugs.app.picture.data.remote.VideoArticleDto;
import com.ws.hugs.databinding.LayoutBinding;

public class VideoAdapter extends PagedListAdapter<MM131VideoArticleModel, Holder> {


    Context context;

    public static  DiffUtil.ItemCallback DIFF_CALLBACK = new DiffUtil.ItemCallback<MM131VideoArticleModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull MM131VideoArticleModel oldItem, @NonNull MM131VideoArticleModel newItem) {
            return oldItem.videoCode==newItem.videoCode;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MM131VideoArticleModel oldItem, @NonNull MM131VideoArticleModel newItem) {
            return oldItem.videoCode==newItem.videoCode;
        }

    };
    public VideoAdapter(Context context){
        super(DIFF_CALLBACK);
        this.context =context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout, parent, false);

        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Gson gson = new Gson();
        holder.databinding.setVideo(gson.fromJson(gson.toJson(getItem(position)),VideoArticleDto.class));
        holder.databinding.setListener(this);
    }


    public void click2(VideoArticleDto dto){
        Log.i("VIDEO","dto "+dto);
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra("ik",dto.getVideoCode());
        context.startActivity(intent);
    }
}
class Holder extends RecyclerView.ViewHolder{

    LayoutBinding databinding;
    public Holder(@NonNull LayoutBinding itemView) {
        super(itemView.getRoot());
        databinding = itemView;

    }
}