package com.ws.hugs.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ws.hugs.R;
import com.ws.hugs.activity.home.MainActivity;
import com.ws.hugs.data.event.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

//    private static BlankFragment2 blankFragment2;


    private TextView  textView;

    private ImageView imageView;
    public static BlankFragment2 newInstance(){
//        if (blankFragment2 == null){
//            synchronized (BlankFragment2.class){
//                if (blankFragment2 == null){
//                    blankFragment2 =
//                }
//            }
//        }
        return new BlankFragment2();
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment2() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment BlankFragment2.
//     */
    // TODO: Rename and change types and number of parameters
//    public static BlankFragment2 newInstance(String param1, String param2) {
//        BlankFragment2 fragment = new BlankFragment2();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        imageView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_blank2,null).findViewById(R.id.frag2_img);
        Glide.with(getActivity())
                .load("https://www.baidu.com/s?rsv_idx=2&tn=baiduhome_pg&wd=Identity&usm=3&ie=utf-8&rsv_cq=&rsv_dl=0_right_recommends_merge_21102&euri=3f02af937eb045b09c3ec890229d1b27")
                .into(imageView);
        Glide.with(getActivity())
                .load("https://www.baidu.com/s?rsv_idx=2&tn=baiduhome_pg&wd=Identity&usm=3&ie=utf-8&rsv_cq=&rsv_dl=0_right_recommends_merge_21102&euri=3f02af937eb045b09c3ec890229d1b27")
                .into(imageView);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe()
    public void onReceiveMessage(EventMessage e){
        System.out.println("fragment2接受到消息 "+e);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(e.getMessage());
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        textView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_blank2,null).findViewById(R.id.fragment2txt);
        textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);

                startActivity(intent);
            }
        });
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }
}
