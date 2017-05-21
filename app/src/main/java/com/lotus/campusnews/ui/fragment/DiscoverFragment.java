/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ViewFlipper;

import com.lotus.campusnews.R;

/**
 * Created by lotus on 2017/5/18.
 */

public class DiscoverFragment extends Fragment {
    private ViewFlipper viewFlipper;
    private GridView gridView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewFlipper = (ViewFlipper) view.findViewById(R.id.discover_viewflipper);
        gridView = (GridView) view.findViewById(R.id.discover_grid);
    }
}
