package com.jenny.indilingo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jenny.indilingo.R;
import com.jenny.indilingo.databinding.ActivityWordsBinding;
import com.jenny.indilingo.util.Constants;
import com.jenny.indilingo.util.Util;
import com.jenny.indilingo.viewmodel.WordsViewModel;

/**
 * Created by jenny on 4/21/17.
 */

public class WordsActivity extends BaseActivity {

    public ActivityWordsBinding mActivityWordsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityWordsBinding = DataBindingUtil.setContentView(this, R.layout.activity_words);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        WordsViewModel mWordsViewModel = new WordsViewModel(this,
                Integer.parseInt(getIntent().getStringExtra(getResources().getString(R.string.test_no))));
        mActivityWordsBinding.setWordsViewModel(mWordsViewModel);
        Util.setSharedPrefs(this, Constants.LEVEL, mWordsViewModel.mTestNo);
        mWordsViewModel.setPortion(false, 3);
        mWordsViewModel.setLanguageOneVisibility(true);
        mWordsViewModel.setMalayalamAlphabetList();
        mWordsViewModel.setHindiAlphabetList();
        mWordsViewModel.setEnglishAlphabetList();
        mWordsViewModel.shuffleAlphabetList();
        mWordsViewModel.setBackgroundColor();
        mWordsViewModel.setOptionsViewArrayList();
        mWordsViewModel.setTextViewMainData();
        mWordsViewModel.setSecondaryData();
    }
}
