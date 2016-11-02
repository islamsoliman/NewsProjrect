package com.news.isoliman.news.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by ISoliman on 11/1/16.
 */
@Database(name = NewsDatabase.NAME, version = NewsDatabase.VERSION)
public class NewsDatabase {

    public static final String NAME = "News";

    public static final int VERSION = 1;

}
