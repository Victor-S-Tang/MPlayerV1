package com.example.sheng.mplayerv1.adapter;


import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sheng.mplayerv1.R;
import com.example.sheng.mplayerv1.domain.NumUrlBean;

/**
 * Created by st on 2017/12/18.
 */

public class NumUrlAdapter extends BaseQuickAdapter<NumUrlBean,BaseViewHolder> {
private Context context;
    public NumUrlAdapter(Context context) {
        super(R.layout.item_play_numbers);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NumUrlBean items) {
         helper.setText(R.id.tv_play_numbers,items.getTitle());
    }
}
