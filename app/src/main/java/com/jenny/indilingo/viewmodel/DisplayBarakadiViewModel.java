package com.jenny.indilingo.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.jenny.indilingo.R;

/**
 * Created by jenny on 12/7/16.
 */

public class DisplayBarakadiViewModel extends BaseObservable {
    private Context mContext;
    private String[] barakadiList;
    private String[] vowelList;
    private String[] vowelSymbolList;

    public int position = 0;
    private String originalAlphabet;

    public DisplayBarakadiViewModel(Context context) {
        mContext = context;
    }

    public void setAlphabetLists(String alphabet) {
        originalAlphabet = alphabet;
        vowelList = mContext.getResources().getStringArray(R.array.malayalam_vowels);
        vowelSymbolList = mContext.getResources().getStringArray(R.array.malayalam_vowels_symbols);
    }

    public void setBarakadiList() {
        int i = 0;
        barakadiList = new String[vowelList.length];
        while (i < vowelList.length) {
            barakadiList[i] = originalAlphabet + vowelSymbolList[i];
            i++;
        }
    }

    public String[] getBarakadiList() {
        return barakadiList;
    }

    public void setBarakadiBreakupList() {
        int i = 0;
        String[] barakadiBreakupList;
        barakadiBreakupList = new String[vowelList.length];
        while (i < vowelList.length) {
            barakadiBreakupList[i] = mContext.getResources().getString(R.string.barakadi_breakup, originalAlphabet, vowelList[i], originalAlphabet + vowelSymbolList[i]);
            i++;
        }
    }
}
