package com.ws.hugs.app.picture.acticity.main.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.ws.hugs.R;
import com.ws.hugs.app.picture.adapter.VideoAdapter;
import com.ws.hugs.app.picture.data.db.MM131VideoArticleModel;
import com.ws.hugs.app.picture.paging.MyAdapter;
import com.ws.hugs.data.viewmodel.MM131VideoArticleViewModel;

public class VideoFragment extends Fragment {


    RecyclerView recyclerView;

    SmartRefreshLayout refreshLayout;
    public static VideoFragment newInstance() {
        return new VideoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View inflate = inflater.inflate(R.layout.video_list_fragment, container, false);
        recyclerView = inflate.findViewById(R.id.video_recy);
        refreshLayout = inflate.findViewById(R.id.smart_refresh);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        recyclerView.setAdapter(new VideoAdapter(getContext()));
        MM131VideoArticleViewModel model = new MM131VideoArticleViewModel(getActivity().getApplication());

        model.mm131ArticleViewModelLiveData.observe(getActivity(), new Observer<PagedList<MM131VideoArticleModel>>() {
            @Override
            public void onChanged(PagedList<MM131VideoArticleModel> mm131VideoArticleModels) {
                recyclerView.getAdapter().notifyDataSetChanged();
                ((VideoAdapter)recyclerView.getAdapter()).submitList(mm131VideoArticleModels);
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                model.refreshData();
            }
        });
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}