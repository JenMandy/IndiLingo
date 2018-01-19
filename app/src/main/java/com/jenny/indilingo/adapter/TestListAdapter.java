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
import com.jenny.indilingo.activity.TestAlphabetsActivity;
import com.jenny.indilingo.databinding.ItemSelectTopicBinding;
import com.jenny.indilingo.viewmodel.TestListViewModel;

/**
 * Created by jenny on 12/9/16.
 */

public class TestListAdapter extends BaseAdapter {

    private Context mContext;
    private int mHighestLevel;
    private String[] levelList;

    public TestListAdapter(Context c, int highestLevel, TestListViewModel testListViewModel) {
        mContext = c;
        mHighestLevel = highestLevel;
        levelList = testListViewModel.getLevelList();
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
                if (position < mHighestLevel + 1) {
                    holder.mSelectTopicBinding.topicName.setButtonBackground(
                            mContext.getResources().getColor(R.color.green_E0),
                            mContext.getResources().getColor(R.color.green_36),
                            mContext.getResources().getColor(R.color.disable)
                    );
                } else {
                    holder.mSelectTopicBinding.topicName.setButtonBackground(
                            mContext.getResources().getColor(R.color.blue_E0),
                            mContext.getResources().getColor(R.color.blue_36),
                            mContext.getResources().getColor(R.color.disable)
                    );
                }

                holder.mSelectTopicBinding.topicName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent;
                        intent = new Intent(mContext, TestAlphabetsActivity.class);
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

    public class TestListViewHolder extends RecyclerView.ViewHolder {
        private ItemSelectTopicBinding mSelectTopicBinding;

        private TestListViewHolder(View view) {
            super(view);
            mSelectTopicBinding = DataBindingUtil.bind(view);
        }
    }
}
