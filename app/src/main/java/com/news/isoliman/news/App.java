package com.news.isoliman.news;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by ISoliman on 11/1/16.
 */

public class App extends Application {
    public final static String ARTICLE = "ARTICLE";

    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

    @Override
    public void onTerminate() {
        FlowManager.destroy();
        super.onTerminate();
    }

}
