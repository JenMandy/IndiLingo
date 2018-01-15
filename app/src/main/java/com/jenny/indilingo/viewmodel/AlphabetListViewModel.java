package com.jenny.indilingo.viewmodel;

import android.databinding.BaseObservable;

/**
 * Created by jenny on 12/8/16.
 */

public class AlphabetListViewModel extends BaseObservable {

    private String[] malayalamAlphabetList;
    public int position = 0;

    public void setMalayalamAlphabetList(String[] malayalamAlphabets) {
        malayalamAlphabetList = malayalamAlphabets;
    }

    public String[] getMalayalamAlphabetList() {
        return malayalamAlphabetList;
    }
}
