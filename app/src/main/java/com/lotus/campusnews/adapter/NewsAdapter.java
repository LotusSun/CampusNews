/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lotus.campusnews.R;
import com.lotus.campusnews.model.BBCArticles;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lotus on 2017/5/19.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {
    private final NewsClickListener mListener;
    private List<BBCArticles> mData = new ArrayList<>();

    public NewsAdapter(List<BBCArticles> mData, NewsClickListener listener) {
        this.mData = mData;
        mListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new Holder(mview);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        BBCArticles articles = mData.get(position);
        holder.title.setText(articles.getTitle());
        holder.descroption.setText(articles.getDescription());
        Glide.with(holder.itemView.getContext())
                .load(articles.getUrlToImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public BBCArticles getSelectedArticle(int position) {
        return mData.get(position);
    }

    public interface NewsClickListener {
        void onClick(int position);
    }

    public class Holder extends ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView title, descroption;

        public Holder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.card_image);
            title = (TextView) itemView.findViewById(R.id.card_title);
            descroption = (TextView) itemView.findViewById(R.id.card_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getLayoutPosition());
        }
    }
}
