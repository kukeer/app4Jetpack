package com.ws.hugs.app.picture.acticity.main.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.ws.hugs.R;
import com.ws.hugs.app.picture.paging.MyAdapter;
import com.ws.hugs.app.picture.data.db.MM131ArticleModel;
import com.ws.hugs.data.viewmodel.MM131ArticleViewModel;
//import com.ws.hugs.paging.MyAdapter;


public class ImageViewerFragment extends Fragment {


    private String TAG = getClass().getSimpleName();

    RecyclerView recyclerView;

    SmartRefreshLayout smartRefreshLayout;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        ImageViewerAdpaterBinding dataBinding = DataBindingUtil.bind(inflater.inflate(R.layout.image_viewer_adpater, container, false));
        View inflate = inflater.inflate(R.layout.image_viewer_adpater, container, false);

        if (recyclerView ==null){
            }
        recyclerView = inflate.findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(true);
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        MyAdapter myAdapter = new MyAdapter(getContext());

        recyclerView.setAdapter(myAdapter);
//        recyclerView.set
        MM131ArticleViewModel viewModel = new ViewModelProvider(this).get(MM131ArticleViewModel.class);
        viewModel.mm131ArticleViewModelLiveData.observe(getActivity(), new Observer<PagedList<MM131ArticleModel>>() {
            @Override
            public void onChanged(PagedList<MM131ArticleModel> mm131ArticleModels) {
                Log.i(TAG,"数据发生了变化");
                ((MyAdapter)recyclerView.getAdapter()).submitList(mm131ArticleModels);
            }
        });

        return inflate;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }





    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG,"onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }

}

