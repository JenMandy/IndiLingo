package com.jenny.indilingo.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.jenny.indilingo.BR;
import com.jenny.indilingo.R;
import com.jenny.indilingo.activity.TestAlphabetsActivity;
import com.jenny.indilingo.util.Constants;
import com.jenny.indilingo.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by jenny on 10/6/16.
 */

public class TestAlphabetsViewModel extends BaseObservable {
    private Context mContext;

    private String[] hindiAlphabetList;
    private String[] englishAlphabetList;
    private String[] malayalamAlphabetList;
    private String[] optionList = new String[6];
    private TextView solutionView;
    private TextView optionViewArray[] = new TextView[6];
    private ArrayList excludeNumbers = new ArrayList();
//    public SharedPreferences.Editor editor;

    private boolean portionOne;
    private boolean portionTwo;
    private boolean portionThree;
    private int end;
    private int score = 0;
    private int start = 0;
    public int mTestNo = 0;
    private int position = 0;
    private int finalScore = 0;
    private int maxQuestions = 10;
    public int highestLevel;
    private String answer;
    private String questionNo;
    private String textViewMainData;

    public TestAlphabetsViewModel(Context context, int testNo) {
        mContext = context;
        mTestNo = testNo;
    }

    public void setMalayalamAlphabetList() {
        String[] alphabetList = mContext.getResources().getStringArray(R.array.malayalam_consonants);
        switch (mTestNo) {
            case 0:
                malayalamAlphabetList = mContext.getResources().getStringArray(R.array.malayalam_vowels);
                break;
            case 1:
                malayalamAlphabetList = Arrays.copyOfRange(alphabetList, 0, 15);
                break;
            case 2:
                malayalamAlphabetList = Arrays.copyOfRange(alphabetList, 15, alphabetList.length);
                break;
            case 3:
                malayalamAlphabetList = alphabetList;
                break;
            case 4:
                malayalamAlphabetList = mContext.getResources().getStringArray(R.array.malayalam_conjoined_alphabets);
                break;
            default:
                malayalamAlphabetList = alphabetList;
        }
        end = malayalamAlphabetList.length;
        if (malayalamAlphabetList.length < maxQuestions) {
            maxQuestions = malayalamAlphabetList.length;
        }
    }

    public void setHindiAlphabetList() {
        String[] alphabetList = mContext.getResources().getStringArray(R.array.hindi_consonants);
        switch (mTestNo) {
            case 0:
                hindiAlphabetList = mContext.getResources().getStringArray(R.array.hindi_vowels);
                break;
            case 1:
                hindiAlphabetList = Arrays.copyOfRange(alphabetList, 0, 15);
                break;
            case 2:
                hindiAlphabetList = Arrays.copyOfRange(alphabetList, 15, alphabetList.length);
                break;
            case 3:
                hindiAlphabetList = alphabetList;
                break;
            case 4:
                hindiAlphabetList = mContext.getResources().getStringArray(R.array.hindi_conjoined_alphabets);
                break;
            default:
                hindiAlphabetList = alphabetList;
        }
    }

    public void setEnglishAlphabetList() {
        String[] alphabetList = mContext.getResources().getStringArray(R.array.english_consonants);
        switch (mTestNo) {
            case 0:
                englishAlphabetList = mContext.getResources().getStringArray(R.array.english_vowels);
                break;
            case 1:
                englishAlphabetList = Arrays.copyOfRange(alphabetList, 0, 15);
                break;
            case 2:
                englishAlphabetList = Arrays.copyOfRange(alphabetList, 15, alphabetList.length);
                break;
            case 3:
                englishAlphabetList = alphabetList;
                break;
            case 4:
                englishAlphabetList = mContext.getResources().getStringArray(R.array.english_conjoined_alphabets);
                break;
            default:
                englishAlphabetList = alphabetList;
        }
    }

    public void shuffleAlphabetList() {
        Util.shuffleArray(malayalamAlphabetList, hindiAlphabetList, englishAlphabetList);
    }

    public void setOptionsViewArrayList() {
        optionViewArray[0] = ((TestAlphabetsActivity) mContext).mActivityTestAlphabetsBinding.optionOne;
        optionViewArray[1] = ((TestAlphabetsActivity) mContext).mActivityTestAlphabetsBinding.optionTwo;
        optionViewArray[2] = ((TestAlphabetsActivity) mContext).mActivityTestAlphabetsBinding.optionThree;
        optionViewArray[3] = ((TestAlphabetsActivity) mContext).mActivityTestAlphabetsBinding.optionFour;
        optionViewArray[4] = ((TestAlphabetsActivity) mContext).mActivityTestAlphabetsBinding.optionFive;
        optionViewArray[5] = ((TestAlphabetsActivity) mContext).mActivityTestAlphabetsBinding.optionSix;
    }

    public void setTextViewMainData(int position) {
        setQuestionNo();
        int selectLanguage = new Random().nextInt(2);
        if (selectLanguage == 0) {
            optionList[0] = answer = hindiAlphabetList[position];
        } else {
            optionList[0] = answer = malayalamAlphabetList[position];
        }
        excludeNumbers.add(position);
        getOptionOneData(1, selectLanguage);
        getOptionOneData(2, selectLanguage);
        getOptionOneData(3, selectLanguage);
        getOptionOneData(4, selectLanguage);
        getOptionOneData(5, selectLanguage);
        setOptionList();
        excludeNumbers.clear();
        if (selectLanguage == 0) {
            textViewMainData = malayalamAlphabetList[position];
        } else {
            textViewMainData = hindiAlphabetList[position];
        }
        notifyPropertyChanged(BR.textViewMainData);
    }

    @Bindable
    public String getTextViewMainData() {
        return textViewMainData;
    }

    private void getOptionOneData(int optionNo, int selectLanguage) {
        int pos = Util.getRandomWithExclusion(start, end, excludeNumbers);
        excludeNumbers.add(pos);
        if (selectLanguage == 0) {
            optionList[optionNo] = hindiAlphabetList[pos];
        } else {
            optionList[optionNo] = malayalamAlphabetList[pos];
        }
    }

    private void incrementAlphabet() {
        if (position < maxQuestions - 1) {
            position++;
        }
        setTextViewMainData(position);
    }

    private void resetView(View view) {
        for (TextView textView :
                optionViewArray) {
            textView.setEnabled(true);
        }

        view.setBackgroundResource(R.drawable.alphabet_card_border);
        solutionView.setBackgroundResource(R.drawable.alphabet_card_border);
    }

    private void setOptionList() {
        optionList = Util.shuffleArray(optionList);
        for (int i = 0; i < optionViewArray.length; i++) {
            if (optionList[i].equals(answer)) {
                solutionView = optionViewArray[i];
            }
            optionViewArray[i].setText(optionList[i]);
        }
    }

    private void setOptionClickedUI() {
        for (TextView textView :
                optionViewArray) {
            textView.setEnabled(false);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (TextView textView :
                    optionViewArray) {
                textView.setTextColor(mContext.getColor(R.color.black));
            }
        } else {
            for (TextView textView :
                    optionViewArray) {
                textView.setTextColor(mContext.getResources().getColor(R.color.black));
            }
        }
    }

    public void checkAnswer(final View view) {
        setOptionClickedUI();
        final int portionNumber = position + 1 - score;
        if (((TextView) view).getText().toString().equals(answer)) {
            setScore();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setBackgroundColor(mContext.getColor(R.color.green));
            } else {
                view.setBackgroundColor(mContext.getResources().getColor(R.color.green));
            }
        } else {
            setPortion(true, portionNumber);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setBackgroundColor(mContext.getColor(R.color.red));
                solutionView.setBackgroundColor(mContext.getColor(R.color.green));
            } else {
                view.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                solutionView.setBackgroundColor(mContext.getResources().getColor(R.color.green));
            }
        }
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                resetView(view);
                if (position < maxQuestions - 1 && portionNumber < 3) {
                    incrementAlphabet();
                } else {
                    ((TestAlphabetsActivity) mContext).mActivityTestAlphabetsBinding.overlay.setVisibility(View.VISIBLE);
                    setFinalScore();
                    if (mTestNo >= highestLevel && score >= (maxQuestions - 3)) {
                        Util.getEditor().putInt(Constants.HIGHEST_LEVEL, mTestNo);
                        Util.getEditor().commit();
                    }
                    Util.getEditor().commit();
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((TestAlphabetsActivity) mContext).finish();
                        }
                    }, 700);
                }
            }
        }, 500);
    }

    private void setScore() {
        ++score;
        notifyPropertyChanged(BR.score);
    }

    @Bindable
    public String getScore() {
        return "Score:" + score;
    }

    private void setFinalScore() {
        finalScore = score;
        notifyPropertyChanged(BR.finalScore);
    }

    @Bindable
    public String getFinalScore() {
        return "Final score: " + finalScore + " / " + maxQuestions;
    }

    private void setQuestionNo() {
        questionNo = position + 1 + " / " + maxQuestions;
        notifyPropertyChanged(BR.questionNo);
    }

    @Bindable
    public String getQuestionNo() {
        return questionNo;
    }

    public void setPortion(boolean color, int number) {
        switch (number) {
            case 1:
                portionOne = color;
                notifyPropertyChanged(BR.portionOne);
                break;
            case 2:
                portionOne = color;
                portionTwo = color;
                notifyPropertyChanged(BR.portionOne);
                notifyPropertyChanged(BR.portionTwo);
                break;
            case 3:
                portionOne = color;
                portionTwo = color;
                portionThree = color;
                notifyPropertyChanged(BR.portionOne);
                notifyPropertyChanged(BR.portionTwo);
                notifyPropertyChanged(BR.portionThree);
                break;
        }
    }

    @Bindable
    public boolean getPortionOne() {
        return portionOne;
    }

    @Bindable
    public boolean getPortionTwo() {
        return portionTwo;
    }

    @Bindable
    public boolean getPortionThree() {
        return portionThree;
    }

    /*// Generating random numbers excluding those in exclude
    private int getRandomWithExclusion(int start, int end, ArrayList exclude) {
        int randomNum;
        Random rand = new Random();
        boolean continueLoop;
        do {
            continueLoop = false;
            // nextInt excludes the top value
            randomNum = rand.nextInt((end - start)) + start;
            for (int i = 0; i < exclude.size(); i++) {
                if (randomNum == Integer.parseInt(exclude.get(i) + "")) {
                    continueLoop = true;
                }
            }
        } while (continueLoop);
        return randomNum;
    }*/
}
