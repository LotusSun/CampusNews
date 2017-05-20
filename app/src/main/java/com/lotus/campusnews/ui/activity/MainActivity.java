
/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lotus.campusnews.R;
import com.lotus.campusnews.ui.fragment.NewsFragment;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    private Fragment newsFrag;
    private Fragment discoverFrag;
    private Fragment usersFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialRegister();
        setNavigationSelectListener();
        setTabSelect(0);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void initialRegister() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        toolbar.setTitle(getResources().getString(R.string.campus_news));
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.default_white));
    }

    private void setNavigationSelectListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_news:
                        setTabSelect(0);
                        break;
                    case R.id.navigation_discover:
                        setTabSelect(1);
                        break;
                    case R.id.navigation_user:
                        setTabSelect(2);
                        break;
                }
                return false;
            }
        });
    }

    private void setTabSelect(int index) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                if (newsFrag == null) {
                    newsFrag = new NewsFragment();
                    transaction.add(R.id.main_frame, newsFrag);
                } else {
                    transaction.show(newsFrag);
                }
                break;
            case 1:
                if (discoverFrag == null) {
                    discoverFrag = new NewsFragment();
                    transaction.add(R.id.main_frame, discoverFrag);
                } else {
                    transaction.show(discoverFrag);
                }
                break;
            case 2:
                if (usersFrag == null) {
                    usersFrag = new NewsFragment();
                    transaction.add(R.id.main_frame, usersFrag);
                } else {
                    transaction.show(usersFrag);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (newsFrag != null) {
            transaction.hide(newsFrag);
        }
        if (discoverFrag != null) {
            transaction.hide(discoverFrag);
        }
        if (usersFrag != null) {
            transaction.hide(usersFrag);
        }
    }
}


