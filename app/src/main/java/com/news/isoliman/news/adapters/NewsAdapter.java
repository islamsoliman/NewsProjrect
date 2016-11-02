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
import com.news.isoliman.news.viewcontrollers.FavoriteFragment;
import com.news.isoliman.news.viewcontrollers.ShareDialog;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ISoliman on 10/31/16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private List<ArticleModel> sources;
    private Context context;
    public static final int ITEM_TYPE_NORMAL = 0;
    public static final int ITEM_TYPE_HEADER = 1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView rowNews;
        public ImageView ivNewImage;
        public ImageView ivNewsShare;
        public ImageView ivNewsFavorite;
        public TextView tvNewTitle;
        public TextView tvNewDate;

        public MyViewHolder(View view) {
            super(view);
            rowNews = (CardView) view.findViewById(R.id.rowNews);
            ivNewImage = (ImageView) view.findViewById(R.id.ivNewImage);
            ivNewsShare = (ImageView) view.findViewById(R.id.ivNewsShare);
            ivNewsFavorite = (ImageView) view.findViewById(R.id.ivNewsFavorite);
            tvNewTitle = (TextView) view.findViewById(R.id.tvNewTitle);
            tvNewDate = (TextView) view.findViewById(R.id.tvNewDate);
        }
    }


    public NewsAdapter(List<ArticleModel> sources, Context context) {
        this.sources = sources;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == ITEM_TYPE_NORMAL) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_row, parent, false);

        } else if (viewType == ITEM_TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.news_big_row, parent, false);
        }
        return new MyViewHolder(itemView);

    }

    @Override
    public int getItemViewType(int position) {
        // here your custom logic to choose the view type
        return position == 0 ? ITEM_TYPE_HEADER : ITEM_TYPE_NORMAL;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ArticleModel source = sources.get(position);
        loadData(holder, source);
        IVNewsFavoriteOnClick(holder, source);
        IVNewsShareOnClick(holder, source);
        rowNewsOnClick(holder, source);

    }

    private void loadData(MyViewHolder holder, ArticleModel source) {
        holder.tvNewTitle.setText(source.getTitle());
        holder.tvNewDate.setText(source.getPublishedAt());

        if (source.getTag().equalsIgnoreCase("1")) {
            holder.ivNewsFavorite.setBackgroundResource(R.drawable.favorite);

        } else {
            holder.ivNewsFavorite.setBackgroundResource(R.drawable.favorite2);
        }
        Picasso.with(this.context)
                .load(source.getUrlToImage())
                .error(R.drawable.error)
                .into(holder.ivNewImage);
    }

    private void IVNewsFavoriteOnClick(final MyViewHolder holder, final ArticleModel source) {
        holder.ivNewsFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (source.getTag().equalsIgnoreCase("1")) {
                    holder.ivNewsFavorite.setBackgroundResource(R.drawable.favorite2);
                    holder.ivNewsFavorite.setTag(2);
                    ArticleModel.getInstance().updateNews(source, "2");
                } else {
                    holder.ivNewsFavorite.setBackgroundResource(R.drawable.favorite);
                    holder.ivNewsFavorite.setTag(1);
                    ArticleModel.getInstance().updateNews(source, "1");
                }

                FavoriteFragment.getInstance().refrersh();
            }
        });
    }

    private void IVNewsShareOnClick(MyViewHolder holder, final ArticleModel source) {
        holder.ivNewsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShareDialog(context, source.getUrl());
            }
        });
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

    @Override
    public int getItemCount() {
        return sources.size();
    }

}
