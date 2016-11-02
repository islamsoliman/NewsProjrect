package com.news.isoliman.news.viewcontrollers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.isoliman.news.App;
import com.news.isoliman.news.R;
import com.news.isoliman.news.models.ArticleModel;
import com.squareup.picasso.Picasso;

/**
 * Created by ISoliman on 11/2/16.
 */

public class DetailActivity extends Activity {

    private ImageView ivNewsImage;
    private TextView tvNewsTitle;
    private TextView tvNewsDesc;
    private ArticleModel articleModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        if (getIntent().hasExtra(App.ARTICLE)) {
            articleModel = getIntent().getParcelableExtra(App.ARTICLE);
        }
        ivNewsImage = (ImageView) findViewById(R.id.ivNewsImage);
        tvNewsTitle = (TextView) findViewById(R.id.tvNewsTitle);
        tvNewsDesc = (TextView) findViewById(R.id.tvNewsDesc);
        loadItem();
    }

    private void loadItem() {
        tvNewsTitle.setText(articleModel.getTitle());
        tvNewsDesc.setText(articleModel.getDescription());
        Picasso.with(this)
                .load(articleModel.getUrlToImage())
                .noFade()
                .noPlaceholder()
                .into(ivNewsImage);
    }


}
