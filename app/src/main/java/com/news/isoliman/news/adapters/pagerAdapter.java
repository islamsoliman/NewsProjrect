package com.news.isoliman.news.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.news.isoliman.news.viewcontrollers.FavoriteFragment;
import com.news.isoliman.news.viewcontrollers.NewsFragment;

/**
 * Created by ISoliman on 10/31/16.
 */


public class pagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public pagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                NewsFragment tab1 = NewsFragment.getInstance();
                return tab1;
            case 1:
                FavoriteFragment tab2 = FavoriteFragment.getInstance();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

