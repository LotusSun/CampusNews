/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lotus.campusnews.R;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import cn.bmob.v3.Bmob;

import static com.lotus.campusnews.utils.Constants.ConstString.BmobAppID;
import static com.lotus.campusnews.utils.Constants.ConstString.QQAppID;


public class StartMainActivity extends AppCompatActivity {


    EditText etUsername;
    EditText etPassWord;
    Button btQQLogin;
    FloatingActionButton fab_login;
    FloatingActionButton.OnClickListener fab_loginListen = new FloatingActionButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            getWindow().setExitTransition(null);
            getWindow().setEnterTransition(null);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(StartMainActivity.this, fab_login, fab_login.getTransitionName());
                startActivity(new Intent(StartMainActivity.this, RegisterAcitivity.class), options.toBundle());
            } else {
                startActivity(new Intent(StartMainActivity.this, RegisterAcitivity.class));
            }
        }
    };
    private Tencent mTencent;
    private LogInListener mListener;
    Button.OnClickListener QQLogin = new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
            mTencent.login(StartMainActivity.this, "all", mListener);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startmain);
        Bmob.initialize(this, BmobAppID);
        mTencent = Tencent.createInstance(QQAppID, this.getApplicationContext());
        mListener = new LogInListener();
        initView();
        btQQLogin.setOnClickListener(QQLogin);
        fab_login.setOnClickListener(fab_loginListen);
    }

    private void initView() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassWord = (EditText) findViewById(R.id.et_password);
        btQQLogin = (Button) findViewById(R.id.QQ_login);
        fab_login = (FloatingActionButton) findViewById(R.id.fab_login);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, mListener);
    }

    private class LogInListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            Toast.makeText(StartMainActivity.this, "QQ授权成功！", Toast.LENGTH_LONG).show();
            Explode explode = new Explode();
            explode.setDuration(500);
            getWindow().setExitTransition(explode);
            getWindow().setEnterTransition(explode);
            @SuppressWarnings("unchecked")
            ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(StartMainActivity.this);
            Intent i2 = new Intent(StartMainActivity.this, MainActivity.class);
            startActivity(i2, oc2.toBundle());
            StartMainActivity.this.finish();
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(StartMainActivity.this, "授权出错！", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(StartMainActivity.this, "授权取消！", Toast.LENGTH_LONG).show();
        }
    }
}
