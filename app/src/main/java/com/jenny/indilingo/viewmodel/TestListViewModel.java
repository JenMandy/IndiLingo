package com.jenny.indilingo.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;

import com.jenny.indilingo.R;
import com.jenny.indilingo.activity.TestAlphabetsActivity;

/**
 * Created by jenny on 12/7/16.
 */

public class TestListViewModel extends BaseObservable {
    public Context mContext;
    private String[] levelList;

    public TestListViewModel(Context context) {
        mContext = context;
    }

    public void setLevelList(String[] levelList) {
        this.levelList = levelList;
    }

    public String[] getLevelList() {
        return levelList;
    }
/*
    public void goToTestActivity(int testNo) {
        Intent intent = new Intent(mContext, TestAlphabetsActivity.class);
        intent.putExtra(mContext.getResources().getString(R.string.test_no), testNo);
        mContext.startActivity(intent);
    }*/
}
