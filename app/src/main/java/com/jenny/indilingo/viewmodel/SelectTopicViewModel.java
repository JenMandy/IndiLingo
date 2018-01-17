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
    private String[] topicList;
    private String[] enabledColor;
    private String[] disabledColor;
    private String[] pressedColor;
    public SelectTopicViewModel(Context context) {
        mContext = context;
    }

    public void goToDisplayVowelsActivity() {
        Intent intent = new Intent(mContext, AlphabetListActivity.class);
        intent.putExtra(mContext.getResources().getString(R.string.alphabet_type),
                mContext.getResources().getString(R.string.vowels));
        mContext.startActivity(intent);
    }

    public void goToDisplayConsonantsActivity() {
        Intent intent = new Intent(mContext, AlphabetListActivity.class);
        intent.putExtra(mContext.getResources().getString(R.string.alphabet_type),
                mContext.getResources().getString(R.string.consonants));
        mContext.startActivity(intent);
    }

    public void goToTestAlphabetsActivity() {
        Intent intent = new Intent(mContext, TestListActivity.class);
        mContext.startActivity(intent);
    }

    public void goToLearnWordsActivity() {
        Intent intent = new Intent(mContext, LearnWordsActivity.class);
        mContext.startActivity(intent);
    }

    public void onButtonClick(Context context, String buttonName) {
        final String[] topicList = context.getResources().getStringArray(R.array.topics_list);
        if (topicList[0].equalsIgnoreCase(buttonName)) {
            goToDisplayVowelsActivity();
        } else if (topicList[1].equalsIgnoreCase(buttonName)) {
            goToDisplayConsonantsActivity();
        } else if (topicList[2].equalsIgnoreCase(buttonName)) {
            goToTestAlphabetsActivity();
        } else if (topicList[3].equalsIgnoreCase(buttonName)) {
            goToLearnWordsActivity();
        }
    }

    public void setTopicList(String[] topicList) {
        this.topicList = topicList;
    }

    public String[] getTopicList() {
        return topicList;
    }

    public void setButtonColor(String[] enabledColor, String[] disabledColor, String[] pressedColor) {
        this.enabledColor = enabledColor;
        this.disabledColor = disabledColor;
        this.pressedColor = pressedColor;
    }

    public String[] getButtonEnabledColor() {
        return enabledColor;
    }

    public String[] getButtonDisabledColor() {
        return disabledColor;
    }

    public String[] getButtonPressedColor() {
        return pressedColor;
    }
}
