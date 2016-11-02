package com.news.isoliman.news.viewmodels;

import android.content.Context;

import com.google.gson.Gson;
import com.news.isoliman.news.models.ArticleModel;
import com.news.isoliman.news.models.NewsBaseModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ISoliman on 10/31/16.
 */

public class NewsViewModel {

    private static NewsViewModel instance;
    static Context context;
    List<ArticleModel> favoriteNews;

    public static NewsViewModel getInstance(Context context1) {
        if (instance == null) {
            instance = new NewsViewModel();
            context = context1;
        }
        return instance;
    }




    public List<ArticleModel> getFavoriteNews() {
        favoriteNews = new ArrayList<>();
        List<ArticleModel> news = ArticleModel.getInstance().getAllNewsFromDB();
        for (int i = 0; i < news.size(); i++) {
            if (news.get(i).getTag().equalsIgnoreCase("2")) {
                favoriteNews.add(news.get(i));
            }
        }
        return favoriteNews;
    }




    public NewsBaseModel getNewsFromLocalJson() {
        NewsBaseModel news = new Gson().fromJson(loadJSONFromAsset(), NewsBaseModel.class);
        ArticleModel.getInstance().saveAllNewsToDB(news.getArticles());
        return news;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {

            InputStream is = context.getAssets().open("data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}
