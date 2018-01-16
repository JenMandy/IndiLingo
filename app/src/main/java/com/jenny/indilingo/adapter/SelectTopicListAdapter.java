package com.jenny.indilingo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jenny.indilingo.R;
import com.jenny.indilingo.databinding.ItemSelectTopicBinding;
import com.jenny.indilingo.viewmodel.SelectTopicViewModel;

/**
 * Created by jenny on 12/8/16.
 */


public class SelectTopicListAdapter extends BaseAdapter {

    private Context mContext;
    private String[] topicList;
    private SelectTopicViewModel mSelectTopicViewModel;

    public SelectTopicListAdapter(Context c, SelectTopicViewModel selectTopicViewModel) {
        mContext = c;
        mSelectTopicViewModel = selectTopicViewModel;
        topicList = selectTopicViewModel.getTopicList();
    }

    private class SelectTopicViewHolder {
        private ItemSelectTopicBinding mItemSelectTopicBinding;
        private TextView topicName;

        private SelectTopicViewHolder(View view) {
            mItemSelectTopicBinding = DataBindingUtil.bind(view);
            topicName = mItemSelectTopicBinding.topicName;
        }
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final SelectTopicViewHolder alphabetListViewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_select_topic, viewGroup, false);
            alphabetListViewHolder = new SelectTopicViewHolder(view);
            alphabetListViewHolder.topicName.setText(topicList[position]);
            alphabetListViewHolder.topicName.setTextSize(16);
            if (mContext.getString(R.string.test_alphabets).equalsIgnoreCase(topicList[position])) {
                alphabetListViewHolder.topicName.setMaxLines(2);
            }
            alphabetListViewHolder.topicName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSelectTopicViewModel.onButtonClick(mContext, topicList[position]);
                }
            });
        }
        return view;
    }

    @Override
    public int getCount() {
        return topicList.length;
    }

    @Override
    public Object getItem(int position) {
        return topicList == null ? null : topicList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
