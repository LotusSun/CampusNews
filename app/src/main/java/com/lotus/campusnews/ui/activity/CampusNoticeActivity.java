/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lotus.campusnews.R;
import com.lotus.campusnews.adapter.NoticeAdapter;
import com.lotus.campusnews.model.CampusNotice;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lotus on 2017/5/27.
 */

public class CampusNoticeActivity extends AppCompatActivity implements NoticeAdapter.NoticeClickListener {
    private List<CampusNotice> noticeData = new ArrayList<>();
    private RecyclerView noticeRecycler;
    private NoticeAdapter mAdapter;
    private Handler handler;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            String url = "http://yjs.hbut.edu.cn/channels/219.html";
            try {
                Document doc = Jsoup.connect(url).timeout(6000).get();
                Elements div = doc.getElementsByAttributeValue("id", "newslist")
                        .select("li")
                        .select("a");
                for (Element element : div) {
                    CampusNotice campusNotice = new CampusNotice();
                    campusNotice.setNoticeUrl(element.attr("href"));
                    campusNotice.setNoticeContent(element.text());
                    noticeData.add(campusNotice);
                }
                handler.sendMessage(handler.obtainMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_notice);
        initView();
        initData();
    }

    @Override
    public void onClick(int position) {
        CampusNotice selectedNotice = mAdapter.getSelectedNotice(position);
        Intent intent = new Intent(CampusNoticeActivity.this, WebViewActivity.class);
        intent.putExtra("url", selectedNotice.getNoticeUrl());
        startActivity(intent);
    }

    private void initView() {
        noticeRecycler = (RecyclerView) findViewById(R.id.campus_notice_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        noticeRecycler.setHasFixedSize(true);
        noticeRecycler.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        noticeRecycler.setLayoutManager(layoutManager);
    }

    private void initData() {
        new Thread(runnable).start();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mAdapter = new NoticeAdapter(CampusNoticeActivity.this, noticeData);
                noticeRecycler.setAdapter(mAdapter);
            }
        };
    }
}
