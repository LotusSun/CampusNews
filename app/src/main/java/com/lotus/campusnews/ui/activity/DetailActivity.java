/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.lotus.campusnews.R;
import com.lotus.campusnews.model.BBCArticles;
import com.lotus.campusnews.utils.Constants;

/**
 * Created by lotus on 2017/5/20.
 */

public class DetailActivity extends AppCompatActivity {
    private BBCArticles selectedArticle;
    private WebView webView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        initView();
        initData();
        controll();
    }

    private void initData() {
        Intent intent = getIntent();
        selectedArticle = (BBCArticles) intent.getSerializableExtra(Constants.ConstString.SELECTED_ARTICLE);
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.detail_web);
        progressBar = (ProgressBar) findViewById(R.id.detail_progress);
    }

    private void controll() {
        webView.loadUrl(selectedArticle.getUrl());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }
}
