package com.jenny.indilingo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jenny.indilingo.R;
import com.jenny.indilingo.activity.DisplayBarakadiActivity;
import com.jenny.indilingo.databinding.ItemAlphabetListBinding;
import com.jenny.indilingo.util.Util;

/**
 * Created by jenny on 12/9/16.
 */

public class DisplayBarakhadiAdapter extends BaseAdapter {
    private Context mContext;
    private String[] barakhadiList;

    public DisplayBarakhadiAdapter(Context c) {
        mContext = c;
        barakhadiList = ((DisplayBarakadiActivity) mContext).mDisplayBarakadiViewModel.getBarakadiList();
    }

    @Override
    public int getCount() {
        return barakhadiList.length;
    }

    @Override
    public Object getItem(int position) {
        return barakhadiList == null ? null : barakhadiList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final BarakhadiListViewHolder barakhadiListViewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_alphabet_list, viewGroup, false);
            barakhadiListViewHolder = new BarakhadiListViewHolder(view);
            return Util.createCustomTextView(barakhadiListViewHolder.alphabetBlock, barakhadiList[position]);
        } else {
            return view;
        }
    }

    private class BarakhadiListViewHolder {
        private ItemAlphabetListBinding mItemAlphabetListBinding;
        private TextView alphabetBlock;

        private BarakhadiListViewHolder(View view) {
            mItemAlphabetListBinding = DataBindingUtil.bind(view);
            alphabetBlock = mItemAlphabetListBinding.alphabetBlock;
        }
    }
}
