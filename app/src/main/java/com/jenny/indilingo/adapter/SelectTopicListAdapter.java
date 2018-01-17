package com.jenny.indilingo.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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

        private SelectTopicViewHolder(View view) {
            mItemSelectTopicBinding = DataBindingUtil.bind(view);
        }
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        final SelectTopicViewHolder selectTopicViewHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_select_topic, viewGroup, false);
            selectTopicViewHolder = new SelectTopicViewHolder(view);
            selectTopicViewHolder.mItemSelectTopicBinding.topicName.setButtonText(topicList[position]);
            selectTopicViewHolder.mItemSelectTopicBinding.topicName.setButtonTextSize(16);
            selectTopicViewHolder.mItemSelectTopicBinding.topicName.setOnClickListener(new View.OnClickListener() {
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
