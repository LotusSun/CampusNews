/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.ui.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.lotus.campusnews.R;
import com.lotus.campusnews.ui.activity.ScoreSearchActivity;
import com.lotus.campusnews.ui.activity.VideoDemoActivity;
import com.lotus.campusnews.ui.activity.WebViewActivity;

import java.util.ArrayList;
import java.util.List;


public class DiscoverFragment extends Fragment {
    private int[] imgs = {  R.drawable.img1, R.drawable.img2,
            R.drawable.img3, R.drawable.img4, };
    private Button btn_video_university;
    private Button btn_book_search;
    private Button btn_score_search;
    private ViewFlipper viewFlipper;
    private List<View> mDotList;//存放小点的的集合
    private final static int NUM = 4;//总图片的数量
    private float x, y;//用来记录在ViewFlipper中按下和弹起的位置

    private static final int AUTO = 0x01;
    /**
     * 向左滑动 上一张的标记：PREVIOUS=2
     */
    private static final int PREVIOUS = 0x02;
    /**
     * 向右滑动 下一张的标记：NEXT=3
     */
    private static final int NEXT = 0x03;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AUTO:
                    imgshowNext();
                    sendMes();
                    break;
                case PREVIOUS:
                    mHandler.removeMessages(AUTO);
                    showPre();
                    sendMes();
                    break;
                case NEXT:
                    mHandler.removeMessages(AUTO);
                    imgshowNext();
                    sendMes();
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        initView(view);
        initData();

        return view;

    }

    private void initData() {

        btn_book_search.setOnClickListener(BookSearch);
        btn_video_university.setOnClickListener(VideoUniversity);
        btn_score_search.setOnClickListener(ScoreSearch);
    }

    Button.OnClickListener ScoreSearch = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(view.getContext(), ScoreSearchActivity.class);
            startActivity(intent);

        }
    };

    Button.OnClickListener VideoUniversity = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(view.getContext(), VideoDemoActivity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener BookSearch = new Button.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.setClass(view.getContext(), WebViewActivity.class);
            startActivity(intent);
        }
    };

    private void initView(View view) {
        btn_score_search = (Button) view.findViewById(R.id.score_serach);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.discover_viewflipper);
        btn_book_search = (Button) view.findViewById(R.id.book_search);
        btn_video_university = (Button) view.findViewById(R.id.university_video);
        mDotList = new ArrayList<>();
        mDotList.add(view.findViewById(R.id.dot1));
        mDotList.add(view.findViewById(R.id.dot2));
        mDotList.add(view.findViewById(R.id.dot3));
        mDotList.add(view.findViewById(R.id.dot4));
        setEvent();
        sendMes();
    }

    public void sendMes() {
        Message msgs = new Message();
        msgs.what = AUTO;
        //发送延迟消息，做到轮播的效果
        mHandler.sendMessageDelayed(msgs, 2000);
    }

    private void setEvent() {
        viewFlipper.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        x = event.getX();
                        // Toast.makeText(view.getContext(), "down"+event.getX(), Toast.LENGTH_LONG).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        y = event.getX();
                        if (y > x) {
                            mHandler.sendEmptyMessage(PREVIOUS);
                        } else if (x == y) {
                            // 等于
                        } else {
                            mHandler.sendEmptyMessage(NEXT);
                        }
                        break;
                }

                return true;
            }
        });
    }

    private void imgshowNext() {
        viewFlipper.showNext();
        int current = viewFlipper.getDisplayedChild();
        if (current == 0) {
            mDotList.get(NUM - 1).setBackgroundResource(R.drawable.dot_normal);
        } else {
            mDotList.get(current - 1).setBackgroundResource(R.drawable.dot_normal);
        }
        mDotList.get(current).setBackgroundResource(R.drawable.dot_focused);
    }

    private void showPre() {
        viewFlipper.showPrevious();
        int current = viewFlipper.getDisplayedChild();
        if (current == NUM - 1) {
            mDotList.get(0).setBackgroundResource(R.drawable.dot_normal);
        } else {
            mDotList.get(current + 1).setBackgroundResource(R.drawable.dot_normal);
        }
        mDotList.get(current).setBackgroundResource(R.drawable.dot_focused);
    }
}

