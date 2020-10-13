package com.ws.hugs.activity.index.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ws.hugs.BookListFragment;
import com.ws.hugs.R;
import com.ws.hugs.app.book.activity.BookListActivity;
import com.ws.hugs.app.picture.acticity.main.PicActivity;
import com.ws.hugs.app.picture.acticity.videoplayer.VideoPlayActivity;
import com.ws.hugs.databinding.FragmentHomeBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class HomeFragment extends Fragment {
    private final static String TAG = "MyGesture";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        FragmentHomeBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, true);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        inflate.setListener(this);
//        inflate.mm131Routing.setOnClickListener(this::setOnclick);
        root.findViewById(R.id.mm131_routing).setOnClickListener(this::setOnclick);

        return root;
    }


    public void setOnclick(View view){
        switch (view.getId()){
            case R.id.mm131_routing:
                gotoActivity(PicActivity.class);
                break;
            case R.id.book_routing:
                gotoActivity(BookListActivity.class);
                break;
            default:
                gotoActivity(PicActivity.class);
                break;
        }
    }

    private void gotoActivity(Class dest){
        Intent intent = new Intent(getActivity(),dest);
        getActivity().startActivity(intent);
    }

}
