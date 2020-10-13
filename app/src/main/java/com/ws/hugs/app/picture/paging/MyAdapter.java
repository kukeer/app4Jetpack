package com.ws.hugs.app.picture.paging;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.ws.hugs.db.mm131.tb.MM131ArticleModel;
import com.ws.hugs.data.event.EventMessage;
import com.ws.hugs.data.remote.MM131Article;
import com.ws.hugs.databinding.PicArticleBinding;

import org.greenrobot.eventbus.EventBus;


public class MyAdapter extends PagedListAdapter<MM131ArticleModel, MyViewHolder> {

    Context context;

    private String TAG = getClass().getSimpleName();

    public MyAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    public static DiffUtil.ItemCallback DIFF_CALLBACK = new DiffUtil.ItemCallback<MM131ArticleModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull MM131ArticleModel oldItem, @NonNull MM131ArticleModel newItem) {
            return oldItem.titleCode==newItem.titleCode;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MM131ArticleModel oldItem, @NonNull MM131ArticleModel newItem) {
            return oldItem.titleCode==newItem.titleCode;
        }

    };


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PicArticleBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.pic_article, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i(TAG,"onBindViewHolder "+position +" sum: "+getItemCount());
        MM131ArticleModel titleItem = getItem(position);
        Gson gson = new Gson();

        Log.i(TAG,"onBindViewHolder 启动"+titleItem);
        if (titleItem  == null){
            return;
        }
        MM131Article mm131Article = gson.fromJson(gson.toJson(titleItem), MM131Article.class);
        holder.articleBinding.setArticle(mm131Article);
        holder.articleBinding.setAdapter(MyAdapter.this);

        Log.i(TAG,"itemView width"+ holder.itemView.getWidth()+  " height "+holder.itemView.getHeight());

    }



    public void onclik1(MM131Article article) {
        Log.i(TAG, "点击了 " + article.getTitle());
        EventBus.getDefault().postSticky(new EventMessage(100, article.getTitleCode()+ ""));
//
        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("zzhsign://"));
        intent.putExtra("title_code",article.getTitle());
        context.startActivity(intent);
    }

}

class MyViewHolder extends RecyclerView.ViewHolder {

    PicArticleBinding articleBinding;

    public MyViewHolder(@NonNull PicArticleBinding itemView) {
        super(itemView.getRoot());
        Log.i("MyViewHolder", "MyViewHolder 初始化");
        articleBinding = itemView;
    }
}