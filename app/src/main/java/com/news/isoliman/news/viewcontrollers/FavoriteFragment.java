package com.news.isoliman.news.viewcontrollers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.isoliman.news.R;
import com.news.isoliman.news.adapters.FavoritesAdapter;
import com.news.isoliman.news.viewmodels.NewsViewModel;

public class FavoriteFragment extends Fragment {
    private static FavoriteFragment instance;

    public static FavoriteFragment getInstance() {
        if (instance == null) {
            instance = new FavoriteFragment();
        }
        return instance;
    }

    private RecyclerView rvNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news, container, false);
        NewsViewModel.getInstance(getActivity()).getNewsFromLocalJson().getStatus();

        rvNews = (RecyclerView) view.findViewById(R.id.rvNews);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvNews.setLayoutManager(mLayoutManager);
        rvNews.setItemAnimator(new DefaultItemAnimator());
        FavoritesAdapter newsAdapter = new FavoritesAdapter(NewsViewModel.getInstance(getActivity()).getFavoriteNews(), getActivity());
        rvNews.setAdapter(newsAdapter);
        return view;
    }

    public void refrersh() {
        FavoritesAdapter newsAdapter = new FavoritesAdapter(NewsViewModel.getInstance(getActivity()).getFavoriteNews(), getActivity());
        rvNews.swapAdapter(newsAdapter, false);

    }
}
