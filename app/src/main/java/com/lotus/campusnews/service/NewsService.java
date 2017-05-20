/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.service;

import com.lotus.campusnews.model.NewsBase;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lotus on 2017/5/19.
 */

public interface NewsService {
    @GET("/v1/articles?source=bbc-news&apiKey=f66b25ce719147038f0be573aa80a327")
    Call<NewsBase> getAllNews();
}
