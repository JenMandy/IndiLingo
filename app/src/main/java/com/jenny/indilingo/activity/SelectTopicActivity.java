package com.jenny.indilingo.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jenny.indilingo.R;
import com.jenny.indilingo.adapter.SelectTopicListAdapter;
import com.jenny.indilingo.databinding.ActivitySelectTopicBinding;
import com.jenny.indilingo.util.Constants;
import com.jenny.indilingo.viewmodel.SelectTopicViewModel;

public class SelectTopicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySelectTopicBinding mActivitySelectTopicBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_topic);
        SelectTopicViewModel mSelectTopicViewModel = new SelectTopicViewModel(this);
        mSelectTopicViewModel.setTopicList(getResources().getStringArray(R.array.topics_list));
        mActivitySelectTopicBinding.setSelectTopicViewModel(mSelectTopicViewModel);

        mActivitySelectTopicBinding.gridSelectTopic.setAdapter(new SelectTopicListAdapter(this, mSelectTopicViewModel));

        SharedPreferences.Editor editor = getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE).edit();
        editor.putString("name", "Dummy Name");
        editor.putInt("idName", 12);
        editor.apply();
    }

}
