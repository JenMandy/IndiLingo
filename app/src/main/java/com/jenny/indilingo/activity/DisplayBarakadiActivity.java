package com.jenny.indilingo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jenny.indilingo.R;
import com.jenny.indilingo.adapter.DisplayBarakhadiAdapter;
import com.jenny.indilingo.databinding.ActivityDisplayBarakadiBinding;
import com.jenny.indilingo.viewmodel.DisplayBarakadiViewModel;

/**
 * Created by jenny on 12/7/16.
 */

public class DisplayBarakadiActivity extends AppCompatActivity {
    public ActivityDisplayBarakadiBinding mActivityDisplayBarakadiBinding;
    public DisplayBarakadiViewModel mDisplayBarakadiViewModel;
    public String[] barakhadiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDisplayBarakadiBinding = DataBindingUtil.setContentView(this, R.layout.activity_display_barakadi);
        mDisplayBarakadiViewModel = new DisplayBarakadiViewModel(this);
        mDisplayBarakadiViewModel.setAlphabetLists(getIntent().getStringExtra(getResources().getString(R.string.alphabet)));
        mDisplayBarakadiViewModel.setBarakadiList();
        mDisplayBarakadiViewModel.setBarakadiBreakupList();

        barakhadiList = mDisplayBarakadiViewModel.getBarakadiList();
        mActivityDisplayBarakadiBinding.barakhadiList.setAdapter(new DisplayBarakhadiAdapter(this));
    }
}
