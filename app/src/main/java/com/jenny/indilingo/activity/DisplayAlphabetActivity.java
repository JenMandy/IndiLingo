package com.jenny.indilingo.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jenny.indilingo.R;
import com.jenny.indilingo.databinding.ActivityDisplayAlphabetBinding;
import com.jenny.indilingo.viewmodel.DisplayAlphabetViewModel;
import com.jenny.indilingo.widgets.OnSwipeTouchListener;

public class DisplayAlphabetActivity extends AppCompatActivity {

    public ActivityDisplayAlphabetBinding mActivityDisplayAlphabetBinding;
    public DisplayAlphabetViewModel mDisplayAlphabetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDisplayAlphabetBinding = DataBindingUtil.setContentView(this, R.layout.activity_display_alphabet);
        mDisplayAlphabetViewModel = new DisplayAlphabetViewModel(this);
        mActivityDisplayAlphabetBinding.setDisplayAlphabetViewModel(mDisplayAlphabetViewModel);
        mDisplayAlphabetViewModel.setPosition(getIntent().getIntExtra(getResources().getString(R.string.alphabet_no), 0));

        if (getIntent().getStringExtra(getResources().getString(R.string.alphabet_type)).
                equals(getResources().getString(R.string.vowels))) {
            mActivityDisplayAlphabetBinding.viewBarakhadiTextView.setVisibility(View.GONE);
            mDisplayAlphabetViewModel.setMalayalamAlphabetList(getResources().getStringArray(R.array.malayalam_vowels));
            mDisplayAlphabetViewModel.setHindiAlphabetList(getResources().getStringArray(R.array.hindi_vowels));
            mDisplayAlphabetViewModel.setEnglishAlphabetList(getResources().getStringArray(R.array.english_vowels));
        } else {
            mActivityDisplayAlphabetBinding.viewBarakhadiTextView.setVisibility(View.VISIBLE);
            mDisplayAlphabetViewModel.setMalayalamAlphabetList(getResources().getStringArray(R.array.malayalam_consonants));
            mDisplayAlphabetViewModel.setHindiAlphabetList(getResources().getStringArray(R.array.hindi_consonants));
            mDisplayAlphabetViewModel.setEnglishAlphabetList(getResources().getStringArray(R.array.english_consonants));
        }

        mDisplayAlphabetViewModel.setTextViewMainData();
        mDisplayAlphabetViewModel.setTextViewOneData();
        mDisplayAlphabetViewModel.setTextViewTwoData();

        mActivityDisplayAlphabetBinding.displayAlphabetTextView.setOnTouchListener(new OnSwipeTouchListener(DisplayAlphabetActivity.this) {
            public void onSwipeRight() {
                mDisplayAlphabetViewModel.decrementAlphabet();
            }

            public void onSwipeLeft() {
                mDisplayAlphabetViewModel.incrementAlphabet();
            }
        });
    }
}
