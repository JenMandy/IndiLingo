package com.jenny.indilingo.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenny.indilingo.R;
import com.jenny.indilingo.activity.TestAlphabetsActivity;
import com.jenny.indilingo.activity.WordsActivity;
import com.jenny.indilingo.databinding.ItemTestListBinding;

/**
 * Created by jenny on 12/9/16.
 */

public class LearnWordsAdapter extends RecyclerView.Adapter<LearnWordsAdapter.TestListViewHolder> {

    private Context mContext;
    private int mHighestLevel;
    private String[] levelList = {
            "Level One"
    };

    public LearnWordsAdapter(Context c, int highestLevel) {
        mContext = c;
        mHighestLevel = highestLevel;
    }

    @Override
    public TestListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_list, parent, false);
        TestListViewHolder vh = new TestListViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final TestListViewHolder holder, final int position) {
        holder.mItemTestListBinding.testItem.setText(levelList[position]);
        holder.mItemTestListBinding.testItem.setTag(position);

        if (position <= (mHighestLevel + 1)) {
            holder.mItemTestListBinding.testItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    intent = new Intent(mContext, WordsActivity.class);
                    intent.putExtra(mContext.getResources().getString(R.string.test_no), holder.mItemTestListBinding.testItem.getTag().toString());
                    mContext.startActivity(intent);
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.mItemTestListBinding.testItem.setBackgroundColor(mContext.getColor(R.color.disable_grey));
            } else {
                holder.mItemTestListBinding.testItem.setBackgroundColor(mContext.getResources().getColor(R.color.disable_grey));
            }
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return levelList.length;
    }

    public class TestListViewHolder extends RecyclerView.ViewHolder {
        private ItemTestListBinding mItemTestListBinding;

        private TestListViewHolder(View view) {
            super(view);
            mItemTestListBinding = DataBindingUtil.bind(view);
        }
    }
}
