package com.jenny.indilingo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jenny.indilingo.R;
import com.jenny.indilingo.activity.AlphabetListActivity;
import com.jenny.indilingo.databinding.ItemAlphabetListBinding;
import com.jenny.indilingo.util.Util;

/**
 * Created by jenny on 12/8/16.
 */


public class AlphabetListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] alphabetList;

    public AlphabetListAdapter(Context c) {
        mContext = c;
        alphabetList = ((AlphabetListActivity) mContext).mAlphabetListViewModel.getMalayalamAlphabetList();
    }

    @Override
    public int getCount() {
        return alphabetList.length;
    }

    @Override
    public Object getItem(int position) {
        return alphabetList == null ? null : alphabetList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final AlphabetListViewHolder alphabetListViewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_alphabet_list, viewGroup, false);
            alphabetListViewHolder = new AlphabetListViewHolder(view);
            return Util.createCustomTextView(alphabetListViewHolder.alphabetBlock, alphabetList[position]);
        } else {
            return view;
        }
    }

    private class AlphabetListViewHolder {
        private ItemAlphabetListBinding mItemAlphabetListBinding;
        private TextView alphabetBlock;

        private AlphabetListViewHolder(View view) {
            mItemAlphabetListBinding = DataBindingUtil.bind(view);
            alphabetBlock = mItemAlphabetListBinding.alphabetBlock;
        }
    }
}
