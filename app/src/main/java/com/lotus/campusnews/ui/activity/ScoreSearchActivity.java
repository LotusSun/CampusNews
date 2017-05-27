/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.ui.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lotus.campusnews.R;
import com.lotus.campusnews.model.ScoreBean;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class ScoreSearchActivity extends AppCompatActivity {


    public String score_username;
    ListView list_score;
    Button btn_score_search;
    EditText et_Username;
    Button.OnClickListener btnScoreSearch = new Button.OnClickListener() {


        @Override
        public void onClick(final View view) {
            initData();
            Toast.makeText(view.getContext(), score_username, Toast.LENGTH_LONG).show();
            BmobQuery<ScoreBean> beanBmobQuery = new BmobQuery<>();
            beanBmobQuery.addWhereEqualTo("name", score_username);
            beanBmobQuery.findObjects(new FindListener<ScoreBean>() {

                @Override
                public void done(List<ScoreBean> list, BmobException e) {

                    for (ScoreBean user : list) {
                        if (e == null) {
                         /*   String userEnglish = user.getEnglish();
                            String userMath = user.getMath();
                            String userPolical = user.getPolical();
                            String userProfession = user.getProfession();
                          */


                        } else {
                            Toast.makeText(view.getContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_search);
        initView();

    }

    private void initView() {
        btn_score_search = (Button) findViewById(R.id.btn_score_search);
        et_Username = (EditText) findViewById(R.id.et_score_username);

        btn_score_search.setOnClickListener(btnScoreSearch);
        list_score = (ListView) findViewById(R.id.score_list);
    }

    private void initData() {
        score_username = (et_Username).getText().toString();


    }
}
