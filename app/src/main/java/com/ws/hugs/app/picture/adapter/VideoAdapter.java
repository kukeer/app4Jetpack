package com.ws.hugs.app.picture.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ws.hugs.R;
import com.ws.hugs.app.picture.data.db.MM131VideoArticleModel;
import com.ws.hugs.app.picture.data.remote.VideoArticleDto;
import com.ws.hugs.databinding.LayoutBindingImpl;

public class VideoAdapter extends PagedListAdapter<VideoArticleDto, Holder> {


    Context context;

    public static  DiffUtil.ItemCallback DIFF_CALLBACK = new DiffUtil.ItemCallback<VideoArticleDto>() {
        @Override
        public boolean areItemsTheSame(@NonNull VideoArticleDto oldItem, @NonNull VideoArticleDto newItem) {
            return oldItem.getVideoCode()==newItem.getVideoCode();
        }

        @Override
        public boolean areContentsTheSame(@NonNull VideoArticleDto oldItem, @NonNull VideoArticleDto newItem) {
            return oldItem.getVideoCode()==newItem.getVideoCode();
        }

    };
    public VideoAdapter(Context context){
        super(DIFF_CALLBACK);
        this.context =context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutBindingImpl inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout, parent, true);
        return new Holder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.databinding.setVideo(getItem(position));
    }
}
class Holder extends RecyclerView.ViewHolder{

    LayoutBindingImpl databinding;
    public Holder(@NonNull LayoutBindingImpl itemView) {
        super(itemView.getRoot());
        databinding = itemView;

    }
}