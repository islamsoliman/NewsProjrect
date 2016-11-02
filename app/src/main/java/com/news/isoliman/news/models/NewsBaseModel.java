
package com.news.isoliman.news.models;

import java.util.ArrayList;
import java.util.List;

public class NewsBaseModel {

    private String status;
    private String source;
    private String sortBy;
    private List<ArticleModel> articles = new ArrayList<ArticleModel>();


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

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }

}
