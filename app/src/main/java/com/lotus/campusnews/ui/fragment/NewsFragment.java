/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lotus.campusnews.R;
import com.lotus.campusnews.adapter.NewsAdapter;
import com.lotus.campusnews.controller.RestManager;
import com.lotus.campusnews.model.BBCArticles;
import com.lotus.campusnews.model.NewsBase;
import com.lotus.campusnews.ui.activity.DetailActivity;
import com.lotus.campusnews.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by lotus on 2017/5/18.
 */

public class NewsFragment extends Fragment implements NewsAdapter.NewsClickListener {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RestManager mManager;
    private List<BBCArticles> mData;
    private NewsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment, container, false);
        init(view);
        initData();
        controllView();
        return view;
    }

    @Override
    public void onClick(int position) {
        BBCArticles selectedArticle = mAdapter.getSelectedArticle(position);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.ConstString.SELECTED_ARTICLE, selectedArticle);
        startActivity(intent);
    }

    private void controllView() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
    }

    private void initData() {
        mManager = new RestManager();
        Call<NewsBase> newsService = mManager.getNewsService().getAllNews();
        newsService.enqueue(new Callback<NewsBase>() {
            @Override
            public void onResponse(Call<NewsBase> call, Response<NewsBase> response) {
                swipeRefreshLayout.setRefreshing(false);
                mData = response.body().getArticles();
                mAdapter = new NewsAdapter(mData, NewsFragment.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<NewsBase> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.list_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(layoutManager);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setRefreshing(true);
    }
}
