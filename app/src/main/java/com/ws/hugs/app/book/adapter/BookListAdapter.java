package com.ws.hugs.app.book.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ws.hugs.R;
import com.ws.hugs.data.remote.Book;
import com.ws.hugs.databinding.Layout2Binding;

import java.util.ArrayList;
import java.util.List;


public class BookListAdapter extends RecyclerView.Adapter<BookHolder> {

    Context context;


    public List<Book> bookList = new ArrayList<>();



    public BookListAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Layout2Binding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout2, parent, false);

        return new BookHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.binding.setBook(bookList.get(position));
        holder.binding.setAdapter(this);
    }

    @Override
    public int getItemCount() {
        return bookList==null?0:bookList.size();
    }
//    public void startChange
}
class BookHolder extends RecyclerView.ViewHolder{

    Layout2Binding binding;
    public BookHolder(@NonNull Layout2Binding itemView) {
        super(itemView.getRoot());
        this.binding = itemView;
    }
}