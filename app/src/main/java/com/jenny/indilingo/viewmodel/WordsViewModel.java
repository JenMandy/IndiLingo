package com.jenny.indilingo.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.jenny.indilingo.BR;
import com.jenny.indilingo.R;
import com.jenny.indilingo.activity.WordsActivity;
import com.jenny.indilingo.util.Constants;
import com.jenny.indilingo.util.Util;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jenny on 4/21/17.
 */

public class WordsViewModel extends BaseObservable {

    private Context mContext;

    private int end;
    private int start;
    private String answer;
    public int highestLevel;
    private boolean isNotTest;
    private String questionNo;
    private boolean portionOne;
    private boolean portionTwo;
    private boolean portionThree;
    private int backgroundColor;
    private String[] hindiWords;
    private String secondaryData;
    private String[] englishWords;
    private TextView solutionView;
    private String textViewMainData;
    private String[] malayalamWords;
    private SharedPreferences prefs;
    private boolean languageOneVisibility;

    private int score = 0;
    public int mTestNo = 0;
    private int position = 0;
    private int maxWords = 10;
    private int finalScore = 0;
    private int maxQuestions = 10;

    private String[] optionList = new String[6];
    private ArrayList excludeNumbers = new ArrayList();
    private TextView optionViewArray[] = new TextView[6];
    private CardView optionViewLayoutArray[] = new CardView[6];

    public WordsViewModel(Context context, int testNo) {
        mContext = context;
        mTestNo = testNo;
        prefs = context.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
    }

    public void setMalayalamAlphabetList() {
        isNotTest = !prefs.getBoolean(Constants.CLEARED_LESSON, false);
//        String[] alphabetList = mContext.getResources().getStringArray(R.array.to_malayalam_colours);
        switch (mTestNo) {
            case 0:
                malayalamWords = mContext.getResources().getStringArray(R.array.to_malayalam_colours);
                break;
            /*case 1:
                malayalamWords = Arrays.copyOfRange(alphabetList, 0, 15);
                break;
            case 2:
                malayalamWords = Arrays.copyOfRange(alphabetList, 15, alphabetList.length);
                break;
            case 3:
                malayalamWords = alphabetList;
                break;
            case 4:
                malayalamWords = mContext.getResources().getStringArray(R.array.malayalam_conjoined_alphabets);
                break;*/
            default:
                malayalamWords = mContext.getResources().getStringArray(R.array.to_malayalam_colours);
        }
        end = malayalamWords.length;
        if (malayalamWords.length < maxWords) {
            maxWords = malayalamWords.length;
            maxQuestions = malayalamWords.length;
        }
    }

    public void setHindiAlphabetList() {
//        String[] alphabetList = mContext.getResources().getStringArray(R.array.hindi_consonants);
        switch (mTestNo) {
            case 0:
                hindiWords = mContext.getResources().getStringArray(R.array.to_english_colours);
                break;
            /*case 1:
                hindiWords = Arrays.copyOfRange(alphabetList, 0, 15);
                break;
            case 2:
                hindiWords = Arrays.copyOfRange(alphabetList, 15, alphabetList.length);
                break;
            case 3:
                hindiWords = alphabetList;
                break;
            case 4:
                hindiWords = mContext.getResources().getStringArray(R.array.hindi_conjoined_alphabets);
                break;*/
            default:
                hindiWords = mContext.getResources().getStringArray(R.array.to_malayalam_colours);
        }
    }

    public void setEnglishAlphabetList() {
//        String[] alphabetList = mContext.getResources().getStringArray(R.array.to_english_colours);
        switch (mTestNo) {
            case 0:
                englishWords = mContext.getResources().getStringArray(R.array.to_english_colours);
                break;
            /*case 1:
                englishWords = Arrays.copyOfRange(alphabetList, 0, 15);
                break;
            case 2:
                englishWords = Arrays.copyOfRange(alphabetList, 15, alphabetList.length);
                break;
            case 3:
                englishWords = alphabetList;
                break;
            case 4:
                englishWords = mContext.getResources().getStringArray(R.array.english_conjoined_alphabets);
                break;*/
            default:
                englishWords = mContext.getResources().getStringArray(R.array.to_english_colours);
        }
    }

    public void shuffleAlphabetList() {
        Util.shuffleArray(malayalamWords, hindiWords, englishWords);
    }

    public void setTextViewMainData() {
        setQuestionNo();
        textViewMainData = malayalamWords[position];
        if (isNotTest) {
            optionViewLayoutArray[3].setVisibility(View.VISIBLE);
            optionViewLayoutArray[4].setVisibility(View.VISIBLE);
            optionViewLayoutArray[5].setVisibility(View.VISIBLE);
            optionViewLayoutArray[4].setBackgroundColor(mContext.getResources().getColor(R.color.white));
            optionViewLayoutArray[4].setCardElevation(0);
            optionViewArray[4].setBackgroundColor(mContext.getResources().getColor(R.color.white));
            optionViewArray[3].setText(mContext.getResources().getString(R.string.previous));
            optionViewArray[5].setText(mContext.getResources().getString(R.string.next));
        } else {
            int selectLanguage = new Random().nextInt(2);
            if (selectLanguage == 0) {
                optionList[0] = answer = hindiWords[position];
            } else {
                optionList[0] = answer = malayalamWords[position];
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
                textViewMainData = malayalamWords[position];
            } else {
                textViewMainData = hindiWords[position];
            }
            if (!isNotTest) {
                setOptionsVisible();
                setLanguageOneVisibility(false);
            }
        }
        notifyPropertyChanged(BR.textViewMainData);
    }

    private void getOptionOneData(int optionNo, int selectLanguage) {
        int pos = Util.getRandomWithExclusion(start, end, excludeNumbers);
        excludeNumbers.add(pos);
        if (selectLanguage == 0) {
            optionList[optionNo] = hindiWords[pos];
        } else {
            optionList[optionNo] = malayalamWords[pos];
        }
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

    public void setSecondaryData() {
        secondaryData = englishWords[position];
        notifyPropertyChanged(BR.secondaryData);
    }

    @Bindable
    public String getSecondaryData() {
        return secondaryData;
    }

    public void setBackgroundColor() {
        if (isNotTest) {
            backgroundColor = mContext.getResources().getColor(mContext.getResources().getIdentifier(englishWords[position], "color", mContext.getPackageName()));
        } else {
            backgroundColor = mContext.getResources().getColor(R.color.blue_36);
        }
        notifyPropertyChanged(BR.backgroundColor);
    }

    @Bindable
    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Bindable
    public String getTextViewMainData() {
        return textViewMainData;
    }

    private void setQuestionNo() {
        questionNo = position + 1 + " / " + maxWords;
        notifyPropertyChanged(BR.questionNo);
    }

    @Bindable
    public String getQuestionNo() {
        return questionNo;
    }

    /*public String getOptionSixText(String text) {
        if (position < maxWords) {
            return "Next";
        }
        return "";
    }*/

    public void onNextClick(View view, boolean increment) {
        if (increment) {
            if (isNotTest) {
                incrementAlphabet();
            } else {
                checkAnswer(view);
            }
        } else {
            if (isNotTest) {
                decrementAlphabet();
            } else {
                checkAnswer(view);
            }
        }
    }

    private void incrementAlphabet() {
        if ((position < (maxWords - 1)) && isNotTest) {
            position++;
            setSecondaryData();
            setLanguageOneVisibility(true);
            setTextViewMainData();
            setBackgroundColor();
        } else if (position == maxWords - 1 && isNotTest) {
            isNotTest = false;
            Util.getEditor().putBoolean(Constants.CLEARED_LESSON, true);
            Util.getEditor().commit();
            position = 0;
            shuffleAlphabetList();
            setOptionsVisible();
            setLanguageOneVisibility(false);
            setTextViewMainData();
            setBackgroundColor();
        } else if (position < maxWords - 1 && !isNotTest) {
            position++;
//            shuffleAlphabetList();
//            setOptionsVisible();
//            setLanguageOneVisibility(false);
            setTextViewMainData();
//            setBackgroundColor();
        }
    }

    private void decrementAlphabet() {
        if (position > 0) {
            position--;
            setSecondaryData();
        }
        setTextViewMainData();
        setBackgroundColor();
    }

    public void setOptionsVisible() {
        optionViewLayoutArray[0].setVisibility(View.VISIBLE);
        optionViewLayoutArray[1].setVisibility(View.VISIBLE);
        optionViewLayoutArray[2].setVisibility(View.VISIBLE);
        optionViewLayoutArray[3].setVisibility(View.VISIBLE);
        optionViewLayoutArray[4].setVisibility(View.VISIBLE);
        optionViewLayoutArray[5].setVisibility(View.VISIBLE);
        optionViewArray[4].setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.alphabet_card_border));
        optionViewLayoutArray[4].setCardElevation(5);
    }

    public void setLanguageOneVisibility(boolean visibility) {
        languageOneVisibility = visibility;
        notifyPropertyChanged(BR.languageOneVisibility);
    }

    @Bindable
    public boolean getLanguageOneVisibility() {
        return languageOneVisibility;
    }

    public void setOptionsViewArrayList() {
        optionViewArray[0] = ((WordsActivity) mContext).mActivityWordsBinding.optionOne;
        optionViewArray[1] = ((WordsActivity) mContext).mActivityWordsBinding.optionTwo;
        optionViewArray[2] = ((WordsActivity) mContext).mActivityWordsBinding.optionThree;
        optionViewArray[3] = ((WordsActivity) mContext).mActivityWordsBinding.optionFour;
        optionViewArray[4] = ((WordsActivity) mContext).mActivityWordsBinding.optionFive;
        optionViewArray[5] = ((WordsActivity) mContext).mActivityWordsBinding.optionSix;

        optionViewLayoutArray[0] = ((WordsActivity) mContext).mActivityWordsBinding.optionOneLayout;
        optionViewLayoutArray[1] = ((WordsActivity) mContext).mActivityWordsBinding.optionTwoLayout;
        optionViewLayoutArray[2] = ((WordsActivity) mContext).mActivityWordsBinding.optionThreeLayout;
        optionViewLayoutArray[3] = ((WordsActivity) mContext).mActivityWordsBinding.optionFourLayout;
        optionViewLayoutArray[4] = ((WordsActivity) mContext).mActivityWordsBinding.optionFiveLayout;
        optionViewLayoutArray[5] = ((WordsActivity) mContext).mActivityWordsBinding.optionSixLayout;
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
                    ((WordsActivity) mContext).mActivityWordsBinding.overlay.setVisibility(View.VISIBLE);
                    setFinalScore();
                    if (mTestNo > highestLevel && score >= (maxQuestions - 3)) {
                        Util.getEditor().putInt(Constants.HIGHEST_LEVEL, mTestNo);
                        Util.getEditor().commit();
                    }
                    Util.getEditor().commit();
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ((WordsActivity) mContext).finish();
                        }
                    }, 700);
                }
            }
        }, 500);
    }

    private void resetView(View view) {
        for (TextView textView :
                optionViewArray) {
            textView.setEnabled(true);
        }

        view.setBackgroundResource(R.drawable.alphabet_card_border);
        solutionView.setBackgroundResource(R.drawable.alphabet_card_border);
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
}
