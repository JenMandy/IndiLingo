package com.jenny.indilingo.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.jenny.indilingo.R;
import com.jenny.indilingo.activity.AlphabetListActivity;
import com.jenny.indilingo.activity.LearnWordsActivity;
import com.jenny.indilingo.activity.TestListActivity;
import com.jenny.indilingo.adapter.LearnWordsAdapter;

/**
 * Created by jenny on 10/6/16.
 */

public class SelectTopicViewModel extends BaseObservable {
    private Context mContext;
    public SelectTopicViewModel(Context context) {
        mContext = context;
    }

    public void goToDisplayVowelsActivity(View view) {
        Intent intent = new Intent(mContext, AlphabetListActivity.class);
        intent.putExtra(mContext.getResources().getString(R.string.alphabet_type),
                mContext.getResources().getString(R.string.vowels));
        mContext.startActivity(intent);
    }

    public void goToDisplayConsonantsActivity(View view) {
        Intent intent = new Intent(mContext, AlphabetListActivity.class);
        intent.putExtra(mContext.getResources().getString(R.string.alphabet_type),
                mContext.getResources().getString(R.string.consonants));
        mContext.startActivity(intent);
    }

    public void goToTestAlphabetsActivity(View view) {
        Intent intent = new Intent(mContext, TestListActivity.class);
        mContext.startActivity(intent);
    }

    public void goToLearnWordsActivity(View view) {
        Intent intent = new Intent(mContext, LearnWordsActivity.class);
        mContext.startActivity(intent);
    }
}
