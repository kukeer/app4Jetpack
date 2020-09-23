package com.ws.hugs.app.picture.data.viewmodel;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ObservableField;

import com.ws.hugs.R;
import com.ws.hugs.app.picture.acticity.main.fragment.ImageViewerFragment;
import com.ws.hugs.app.picture.data.model.MM131ChoiceModel;

public class MM131ChoiceViewModel {

    private ObservableField<MM131ChoiceModel> choiceModelObservableField;

    AppCompatActivity activity;
    public MM131ChoiceViewModel(){
        MM131ChoiceModel mm131ChoiceModel = new MM131ChoiceModel();
        choiceModelObservableField = new ObservableField<MM131ChoiceModel>();
        choiceModelObservableField.set(mm131ChoiceModel);
    }


    public void chooseFragment(){
        MM131ChoiceModel mm131ChoiceModel = choiceModelObservableField.get();
        mm131ChoiceModel.setRight(!mm131ChoiceModel.isRight());
//        Intent intent;
//        if (mm131ChoiceModel.isRight()==false){
//            activity.getSupportFragmentManager().beginTransaction().replace(R.id.,new ImageViewerFragment()).commitNowAllowingStateLoss();
//            return;
//        }
//        if (mm131ChoiceModel.isRight()==true){
//            return;
//        }
    }

}
