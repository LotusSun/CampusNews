/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lotus.campusnews.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoDemoActivity extends AppCompatActivity {
    ProgressDialog progressDialog = null;
    private VideoView mVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(getApplicationContext());
        setContentView(R.layout.vitamioview_layout);
        playfunction();
        myOnclick();

    }

    public void playfunction() {
        String path = "http://gslb.miaopai.com/stream/oxX3t3Vm5XPHKUeTS-zbXA__.mp4";
        mVideo = (VideoView) findViewById(R.id.surface_view);
        mVideo.setVideoPath(path);
        mVideo.setMediaController(new MediaController(this));
        mVideo.requestFocus();
        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
            }
        });
    }

    private void myOnclick() {
        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
            }
        });
        mVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "视频播放完成了", Toast.LENGTH_SHORT).show();
                finish();//退出播放器
            }
        });
        mVideo.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "视频播放出错了", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mVideo.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        if (mVideo.isPlaying()) {
                            mVideo.pause();
                        }
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mVideo.start();
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        break;
                }
                return true;
            }
        });
        mVideo.setBufferSize(1024);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressDialog = new ProgressDialog(VideoDemoActivity.this);
        progressDialog.setMessage("正在加载视频中");
        progressDialog.setCancelable(true);
        // progressDialog.show();
    }
}
