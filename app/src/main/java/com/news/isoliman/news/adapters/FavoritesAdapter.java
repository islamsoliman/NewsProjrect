package com.news.isoliman.news.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.isoliman.news.App;
import com.news.isoliman.news.R;
import com.news.isoliman.news.models.ArticleModel;
import com.news.isoliman.news.viewcontrollers.DetailActivity;
import com.news.isoliman.news.viewcontrollers.ShareDialog;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ISoliman on 10/31/16.
 */
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyViewHolder> {
    private List<ArticleModel> sources;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivNewImage;
        public ImageView ivNewsShare;
        public ImageView ivNewsFavorite;
        public CardView rowNews;
        public TextView tvNewTitle;
        public TextView tvNewDate;

        public MyViewHolder(View view) {
            super(view);
            rowNews = (CardView) view.findViewById(R.id.rowNews);
            ivNewImage = (ImageView) view.findViewById(R.id.ivNewImage);
            ivNewsShare = (ImageView) view.findViewById(R.id.ivNewsShare);
            tvNewTitle = (TextView) view.findViewById(R.id.tvNewTitle);
            tvNewDate = (TextView) view.findViewById(R.id.tvNewDate);
            ivNewsFavorite = (ImageView) view.findViewById(R.id.ivNewsFavorite);


        }
    }


    public FavoritesAdapter(List<ArticleModel> sources, Context context) {
        this.sources = sources;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_row, parent, false);
        return new MyViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ArticleModel source = sources.get(position);
        loadData(holder, source);
        rowNewsOnClick(holder, source);
        IVNewsShareOnClick(holder, source);

    }

    private void rowNewsOnClick(MyViewHolder holder, final ArticleModel source) {
        holder.rowNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(App.ARTICLE, source);
                context.startActivity(intent);

            }
        });
    }

    private void loadData(MyViewHolder holder, final ArticleModel source) {
        holder.ivNewsFavorite.setVisibility(View.GONE);
        holder.tvNewTitle.setText(source.getTitle());
        holder.tvNewDate.setText(source.getPublishedAt());

        Picasso.with(this.context)
                .load(source.getUrlToImage())
                .error(R.drawable.error)
                .into(holder.ivNewImage);
    }

    private void IVNewsShareOnClick(MyViewHolder holder, final ArticleModel source) {
        holder.ivNewsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareDialog(context, source.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

}
