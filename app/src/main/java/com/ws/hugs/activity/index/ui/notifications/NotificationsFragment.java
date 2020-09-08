package com.ws.hugs.activity.index.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;
import com.ws.hugs.R;
import com.ws.hugs.activity.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class NotificationsFragment extends Fragment {

//    private NotificationsViewModel notificationsViewModel;

    QMUIRadiusImageView userImg;
    TextView userName;

    QMUIGroupListView groupListView;
    QMUIGroupListView groupListView1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        notificationsViewModel =
//                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        initView(root);
        return root;
    }

    private void initView(View root) {

        userImg =root.findViewById(R.id.user_img);
        userImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        groupListView = root.findViewById(R.id.group_list);
        groupListView1 = root.findViewById(R.id.group_list1);
        QMUICommonListItemView itemWithSwitch = groupListView.createItemView("夜间模式");
        itemWithSwitch.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        itemWithSwitch.getSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(getActivity(), "checked = " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });




        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof QMUICommonListItemView) {
                    CharSequence text = ((QMUICommonListItemView) v).getText();
                    Toast.makeText(getActivity(), text + " is Clicked", Toast.LENGTH_SHORT).show();
                    if (((QMUICommonListItemView) v).getAccessoryType() == QMUICommonListItemView.ACCESSORY_TYPE_SWITCH) {
                        ((QMUICommonListItemView) v).getSwitch().toggle();
                    }
                }
            }
        };
        QMUIGroupListView.newSection(getContext())
                .addItemView(generateCommonItem("充值",R.mipmap.gift,groupListView),onClickListener)
                .addItemView(generateCommonItem("兑换酷听券",R.mipmap.gift,groupListView),onClickListener)
                .addItemView(generateCommonItem("兑换码",R.mipmap.gift,groupListView),onClickListener)
                .setOnlyShowStartEndSeparator(true)
                .addTo(groupListView);
        QMUIGroupListView.newSection(getContext())
                .addItemView(generateCommonItem("我的订单",R.mipmap.gift,groupListView1),onClickListener)
                .addItemView(generateCommonItem("活动广场",R.mipmap.gift,groupListView1),onClickListener)
                .addItemView(itemWithSwitch,onClickListener)
                .addItemView(generateCommonItem("设置",R.mipmap.gift,groupListView1),onClickListener)
                .addItemView(generateCommonItem("帮助与反馈",R.mipmap.gift,groupListView1),onClickListener)
                .addTo(groupListView1);
    }

    public QMUICommonListItemView generateCommonItem(String name,int imgRId,QMUIGroupListView qmuiGroupListView){
        QMUICommonListItemView normalItem = qmuiGroupListView.createItemView(
                ContextCompat.getDrawable(getContext(), imgRId),
                name,
                null,
                QMUICommonListItemView.HORIZONTAL,
                QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        normalItem.setOrientation(QMUICommonListItemView.VERTICAL);

        int paddingVer = QMUIDisplayHelper.dp2px(getContext(), 12);
        normalItem.setPadding(normalItem.getPaddingLeft(), paddingVer,
                normalItem.getPaddingRight(), paddingVer);
        return normalItem;
    }
}
