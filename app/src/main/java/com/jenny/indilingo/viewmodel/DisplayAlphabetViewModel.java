package com.jenny.indilingo.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.jenny.indilingo.BR;
import com.jenny.indilingo.R;

import com.jenny.indilingo.activity.DisplayBarakadiActivity;

/**
 * Created by jenny on 10/4/16.
 */

public class DisplayAlphabetViewModel extends BaseObservable {
    private Context mContext;
    private String[] malayalamAlphabetList;
    private String[] hindiAlphabetList;
    private String[] englishAlphabetList;
    private String textViewMainData;
    private String textViewOneData;
    private String textViewTwoData;
    public int position;

    public DisplayAlphabetViewModel(Context context) {
        mContext = context;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setMalayalamAlphabetList(String[] malayalamAlphabets) {
        malayalamAlphabetList = malayalamAlphabets;
    }

    public void setHindiAlphabetList(String[] hindiAlphabets) {
        hindiAlphabetList = hindiAlphabets;
    }

    public void setEnglishAlphabetList(String[] englishAlphabets) {
        englishAlphabetList = englishAlphabets;
    }

    @Bindable
    public String getTextViewMainData() {
        return textViewMainData;
    }

    @Bindable
    public String getTextViewOneData() {
        return textViewOneData;
    }

    @Bindable
    public String getTextViewTwoData() {
        return textViewTwoData;
    }

    public void setTextViewMainData() {
        textViewMainData = malayalamAlphabetList[position];
        notifyPropertyChanged(BR.textViewMainData);
    }

    public void setTextViewOneData() {
        textViewOneData = hindiAlphabetList[position];
        notifyPropertyChanged(BR.textViewOneData);
    }

    public void setTextViewTwoData() {
        textViewTwoData = englishAlphabetList[position];
        notifyPropertyChanged(BR.textViewTwoData);
    }

    public void incrementAlphabet() {
        if (position < malayalamAlphabetList.length - 1) {
            position++;
        } else {
            position = 0;
        }
        setTextViewMainData();
        setTextViewOneData();
        setTextViewTwoData();
    }

    public void decrementAlphabet() {
        if (position > 0) {
            position--;
        } else {
            position = malayalamAlphabetList.length - 1;
        }
        setTextViewMainData();
        setTextViewOneData();
        setTextViewTwoData();
    }

    public void goToBarakhadiScreen(View view) {
        Intent intent = new Intent(mContext, DisplayBarakadiActivity.class);
        intent.putExtra(mContext.getResources().getString(R.string.alphabet), textViewMainData);
        mContext.startActivity(intent);
    }
}
