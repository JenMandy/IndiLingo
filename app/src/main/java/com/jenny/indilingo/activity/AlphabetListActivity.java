package com.jenny.indilingo.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.jenny.indilingo.R;
import com.jenny.indilingo.adapter.AlphabetListAdapter;
import com.jenny.indilingo.databinding.ActivityAlphabetListBinding;
import com.jenny.indilingo.viewmodel.AlphabetListViewModel;

/**
 * Created by jenny on 12/8/16.
 */

public class AlphabetListActivity extends AppCompatActivity {

    public ActivityAlphabetListBinding mActivityAlphabetListBinding;
    public AlphabetListViewModel mAlphabetListViewModel;
    public String[] alphabetList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAlphabetListBinding = DataBindingUtil.setContentView(this, R.layout.activity_alphabet_list);
        mAlphabetListViewModel = new AlphabetListViewModel();
        if (getIntent().getStringExtra(getResources().getString(R.string.alphabet_type)).
                equals(getResources().getString(R.string.vowels))) {
            mAlphabetListViewModel.setMalayalamAlphabetList(getResources().getStringArray(R.array.malayalam_vowels));
        } else {
            mAlphabetListViewModel.setMalayalamAlphabetList(getResources().getStringArray(R.array.malayalam_consonants));
        }

        alphabetList = mAlphabetListViewModel.getMalayalamAlphabetList();
        mActivityAlphabetListBinding.alphabetList.setAdapter(new AlphabetListAdapter(this));

        mActivityAlphabetListBinding.alphabetList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(AlphabetListActivity.this, DisplayAlphabetActivity.class);
                intent.putExtra(getResources().getString(R.string.alphabet_type),
                        getIntent().getStringExtra(getResources().getString(R.string.alphabet_type)));
                intent.putExtra(getResources().getString(R.string.alphabet_no),
                        position);
                startActivity(intent);
            }
        });
    }

}
