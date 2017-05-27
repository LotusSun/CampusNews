/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lotus.campusnews.R;
import com.lotus.campusnews.model.ScoreBean;

import java.util.List;


public class ScoreAdapter extends BaseAdapter {
    private List<ScoreBean> mData;
    private Context mContext;

    public ScoreAdapter(List<ScoreBean> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;

    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.score_item, viewGroup, false);
        TextView tv_score = (TextView) convertView.findViewById(R.id.tv_score_title);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_score_content);

        return convertView;
    }
}
