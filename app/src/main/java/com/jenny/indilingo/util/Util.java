package com.jenny.indilingo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jenny on 10/4/16.
 */

public class Util {
    private static SharedPreferences.Editor editor;

    // Implementing Fisher–Yates shuffle
    public static String[] shuffleArray(String[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    // Implementing Fisher–Yates shuffle
    public static void shuffleArray(String[] ar, String[] a2, String[] a3) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;

            a = a2[index];
            a2[index] = a2[i];
            a2[i] = a;

            a = a3[index];
            a3[index] = a3[i];
            a3[i] = a;
        }
    }

    // Generating random numbers excluding those in exclude
    public static int getRandomWithExclusion(int start, int end, ArrayList exclude) {
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
    }

    public static View createCustomTextView(View view, String alphabet) {
        TextView tv = (TextView) view;
        tv.setText(alphabet);
        return tv;
    }

    public static int convertDpToPixel(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }

    public static void setSharedPrefs(Context context,String id, int testNo) {
        editor = context.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE).edit();
//        SharedPreferences prefs = context.getSharedPreferences(Constants.USER_DATA, MODE_PRIVATE);
//        highestLevel = prefs.getInt(Constants.HIGHEST_LEVEL, -1);
        editor.putInt(id, testNo);
        editor.commit();
    }

    public static SharedPreferences.Editor getEditor() {
        return editor;
    }
}
