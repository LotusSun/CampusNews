/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lotus.campusnews.R;
import com.lotus.campusnews.model.CampusNotice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lotus on 2017/5/28.
 */

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.Holder> {
    private final NoticeClickListener mListener;
    private List<CampusNotice> mData = new ArrayList<>();

    public NoticeAdapter(NoticeClickListener mListener, List<CampusNotice> mData) {
        this.mListener = mListener;
        this.mData = mData;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.campus_notice_item, viewGroup, false);
        return new Holder(mview);
    }

    @Override
    public void onBindViewHolder(Holder holder, int i) {
        CampusNotice campusNotice = mData.get(i);
        holder.title.setText(campusNotice.getNoticeContent());
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public CampusNotice getSelectedNotice(int position) {
        return mData.get(position);
    }


    public interface NoticeClickListener {
        void onClick(int position);
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView title;

        public Holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.campus_notice_item_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getLayoutPosition());
        }
    }
}
