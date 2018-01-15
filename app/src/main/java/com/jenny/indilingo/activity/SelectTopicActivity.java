package com.jenny.indilingo.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jenny.indilingo.R;
import com.jenny.indilingo.databinding.ActivitySelectTopicBinding;
import com.jenny.indilingo.util.Constants;
import com.jenny.indilingo.viewmodel.SelectTopicViewModel;

public class SelectTopicActivity extends AppCompatActivity {

    public ActivitySelectTopicBinding mActivitySelectTopicBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySelectTopicBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_topic);
        SelectTopicViewModel mSelectTopicViewModel = new SelectTopicViewModel(this);
        mActivitySelectTopicBinding.setSelectTopicViewModel(mSelectTopicViewModel);

        SharedPreferences.Editor editor = getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE).edit();
        editor.putString("name", "Elena");
        editor.putInt("idName", 12);
        editor.apply();
    }

}
