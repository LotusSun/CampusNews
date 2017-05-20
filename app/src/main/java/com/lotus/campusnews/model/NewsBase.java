/*
 * Copyright (c) 2017. Lotus Sun. All Rights Reserved.
 */

package com.lotus.campusnews.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lotus on 2017/5/20.
 */

public class NewsBase implements Serializable {
    @Expose
    private String status;
    @Expose
    private String source;
    @Expose
    private String sortBy;
    @Expose
    private List<BBCArticles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<BBCArticles> getArticles() {
        return articles;
    }

    public void setArticles(List<BBCArticles> articles) {
        this.articles = articles;
    }
}
