package com.jenny.indilingo.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jenny.indilingo.R;
import com.jenny.indilingo.activity.WordsActivity;
import com.jenny.indilingo.databinding.ItemSelectTopicBinding;

/**
 * Created by jenny on 12/9/16.
 */

public class LearnWordsAdapter extends BaseAdapter {

    private Context mContext;
    private int mHighestLevel;
    private String[] levelList = {
            "Level One"
    };

    public LearnWordsAdapter(Context c, int highestLevel) {
        mContext = c;
        mHighestLevel = highestLevel;
    }

    public class TestListViewHolder extends RecyclerView.ViewHolder {
        private ItemSelectTopicBinding mSelectTopicBinding;

        private TestListViewHolder(View view) {
            super(view);
            mSelectTopicBinding = DataBindingUtil.bind(view);
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final TestListViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_select_topic, viewGroup, false);
            holder = new TestListViewHolder(view);
            holder.mSelectTopicBinding.topicName.setButtonText(levelList[position]);
            holder.mSelectTopicBinding.topicName.setTag(position);

            if (position <= (mHighestLevel + 1)) {
                holder.mSelectTopicBinding.topicName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent;
                        intent = new Intent(mContext, WordsActivity.class);
                        intent.putExtra(mContext.getResources().getString(R.string.test_no), holder.mSelectTopicBinding.topicName.getTag().toString());
                        mContext.startActivity(intent);
                    }
                });
            } else {
                holder.mSelectTopicBinding.topicName.setEnabled(false);
            }
        }
        return view;
    }

    @Override
    public int getCount() {
        return levelList.length;
    }

    @Override
    public Object getItem(int i) {
        return levelList[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
