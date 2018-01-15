package com.jenny.indilingo.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jenny.indilingo.R;
import com.jenny.indilingo.adapter.LearnWordsAdapter;
import com.jenny.indilingo.databinding.ActivityLearnWordsListBinding;
import com.jenny.indilingo.util.Constants;
import com.jenny.indilingo.viewmodel.TestWordsListViewModel;

/**
 * Created by jenny on 12/7/16.
 */

public class LearnWordsActivity extends AppCompatActivity {

    public ActivityLearnWordsListBinding activityLearnWordsListBinding;
    private TestWordsListViewModel mTestWordsListViewModel;
    private LearnWordsAdapter mLearnWordsAdapter;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
        activityLearnWordsListBinding = DataBindingUtil.setContentView(this, R.layout.activity_learn_words_list);
        mTestWordsListViewModel = new TestWordsListViewModel(this);
        activityLearnWordsListBinding.setTestWordsListViewModel(mTestWordsListViewModel);
        mLearnWordsAdapter = new LearnWordsAdapter(this, prefs.getInt(Constants.HIGHEST_LEVEL, -1));
        activityLearnWordsListBinding.wordsLevelList.setAdapter(mLearnWordsAdapter);
        activityLearnWordsListBinding.wordsLevelList.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityLearnWordsListBinding.wordsLevelList.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLearnWordsAdapter = new LearnWordsAdapter(this, prefs.getInt(Constants.HIGHEST_LEVEL, -1));
        activityLearnWordsListBinding.wordsLevelList.setAdapter(mLearnWordsAdapter);
        mLearnWordsAdapter.notifyDataSetChanged();
    }
}
