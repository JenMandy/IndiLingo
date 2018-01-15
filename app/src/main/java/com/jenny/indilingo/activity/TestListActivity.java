package com.jenny.indilingo.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jenny.indilingo.R;
import com.jenny.indilingo.adapter.TestListAdapter;
import com.jenny.indilingo.databinding.ActivityTestListBinding;
import com.jenny.indilingo.util.Constants;
import com.jenny.indilingo.viewmodel.TestListViewModel;

/**
 * Created by jenny on 12/7/16.
 */

public class TestListActivity extends AppCompatActivity {

    public ActivityTestListBinding mActivityTestListBinding;
    private TestListViewModel mTestListViewModel;
    private TestListAdapter mTestListAdapter;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
        mActivityTestListBinding = DataBindingUtil.setContentView(this, R.layout.activity_test_list);
        mTestListViewModel = new TestListViewModel(this);
        mActivityTestListBinding.setTestListViewModel(mTestListViewModel);
        mTestListAdapter = new TestListAdapter(this, prefs.getInt(Constants.HIGHEST_LEVEL, -1));
        mActivityTestListBinding.testLevelList.setAdapter(mTestListAdapter);
        mActivityTestListBinding.testLevelList.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mActivityTestListBinding.testLevelList.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTestListAdapter = new TestListAdapter(this, prefs.getInt(Constants.HIGHEST_LEVEL, -1));
        mActivityTestListBinding.testLevelList.setAdapter(mTestListAdapter);
        mTestListAdapter.notifyDataSetChanged();
    }
}
