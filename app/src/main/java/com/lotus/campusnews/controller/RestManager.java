/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.controller;

import com.lotus.campusnews.service.NewsService;
import com.lotus.campusnews.utils.Constants;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lotus on 2017/5/19.
 */

public class RestManager {
    private NewsService mNewsService;

    private Retrofit retrofit;

    public NewsService getNewsService() {
        if (mNewsService == null) {
             retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mNewsService = retrofit.create(NewsService.class);
        }
        return mNewsService;
    }

}
