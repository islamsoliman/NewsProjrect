package com.news.isoliman.news.viewcontrollers;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.news.isoliman.news.R;
import com.news.isoliman.news.adapters.NewsAdapter;
import com.news.isoliman.news.models.ArticleModel;
import com.news.isoliman.news.viewmodels.NewsViewModel;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NewsFragment extends Fragment {
    private static NewsFragment instance;

    public static NewsFragment getInstance() {
        if (instance == null) {
            instance = new NewsFragment();
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
        NewsAdapter newsAdapter = new NewsAdapter(ArticleModel.getInstance().getAllNewsFromDB(), getActivity());
        rvNews.setAdapter(newsAdapter);

        return view;
    }


}
