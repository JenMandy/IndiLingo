package com.jenny.indilingo.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenny.indilingo.R;
import com.jenny.indilingo.databinding.ActivityTestAlphabetsBinding;
import com.jenny.indilingo.util.Constants;
import com.jenny.indilingo.util.Util;
import com.jenny.indilingo.viewmodel.TestAlphabetsViewModel;

public class TestAlphabetsActivity extends AppCompatActivity {

    public static final String USER_DATA = "userData";
    public ActivityTestAlphabetsBinding mActivityTestAlphabetsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityTestAlphabetsBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_alphabets);
        TestAlphabetsViewModel mTestAlphabetsViewModel = new TestAlphabetsViewModel(this,
                Integer.parseInt(getIntent().getStringExtra(getResources().getString(R.string.test_no))));
        mActivityTestAlphabetsBinding.setTestAlphabetsViewModel(mTestAlphabetsViewModel);
        Util.setSharedPrefs(this, Constants.LEVEL, mTestAlphabetsViewModel.mTestNo);
        mTestAlphabetsViewModel.setPortion(false, 3);
        mTestAlphabetsViewModel.setMalayalamAlphabetList();
        mTestAlphabetsViewModel.setHindiAlphabetList();
        mTestAlphabetsViewModel.setEnglishAlphabetList();
        mTestAlphabetsViewModel.shuffleAlphabetList();
        mTestAlphabetsViewModel.setOptionsViewArrayList();
        mTestAlphabetsViewModel.setTextViewMainData(0);

        SharedPreferences prefs = getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
        int name = prefs.getInt(Constants.LEVEL, 89);//"No name defined" is the default value.
        int highestLevel = prefs.getInt(Constants.HIGHEST_LEVEL, -1); //0 is the default value.
    }
}