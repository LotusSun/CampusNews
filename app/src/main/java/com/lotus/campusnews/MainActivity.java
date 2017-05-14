package com.lotus.campusnews;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;


public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialRegister();
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

}


