package com.jenny.indilingo.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by jenny on 12/7/16.
 */

public class TestWordsListViewModel extends BaseObservable {
    public Context mContext;

    public TestWordsListViewModel(Context context) {
        mContext = context;
    }
/*
    public void goToTestActivity(int testNo) {
        Intent intent = new Intent(mContext, TestAlphabetsActivity.class);
        intent.putExtra(mContext.getResources().getString(R.string.test_no), testNo);
        mContext.startActivity(intent);
    }*/
}
