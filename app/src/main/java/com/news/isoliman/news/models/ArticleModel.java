
package com.news.isoliman.news.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.news.isoliman.news.database.NewsDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;


@Table(database = NewsDatabase.class)
public class ArticleModel extends BaseModel implements Parcelable {
    @PrimaryKey(autoincrement = true)
    private int id;
    @Column
    private String author;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String url;
    @Column
    private String urlToImage;
    @Column
    private String publishedAt;
    @Column
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private static ArticleModel instance;

    public static ArticleModel getInstance() {
        if (instance == null) {
            instance = new ArticleModel();
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getUrlToImage() {
        return urlToImage;
    }


    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }


    public String getPublishedAt() {
        return publishedAt;
    }


    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


    public List<ArticleModel> getAllNewsFromDB() {
        List<ArticleModel> models = SQLite.select().from(ArticleModel.class).queryList();
        return models;

    }

    public void updateNews(ArticleModel articleModel, String tag) {
        articleModel.setTag(tag);
        articleModel.update();
    }

    public void saveAllNewsToDB(List<ArticleModel> articleModels) {
        if (getAllNewsFromDB().isEmpty()) {
            for (int i = 0; i < articleModels.size(); i++) {
                articleModels.get(i).save();
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.author);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.url);
        dest.writeString(this.urlToImage);
        dest.writeString(this.publishedAt);
        dest.writeString(this.tag);
    }

    public ArticleModel() {
    }

    protected ArticleModel(Parcel in) {
        this.id = in.readInt();
        this.author = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.url = in.readString();
        this.urlToImage = in.readString();
        this.publishedAt = in.readString();
        this.tag = in.readString();
    }

    public static final Parcelable.Creator<ArticleModel> CREATOR = new Parcelable.Creator<ArticleModel>() {
        @Override
        public ArticleModel createFromParcel(Parcel source) {
            return new ArticleModel(source);
        }

        @Override
        public ArticleModel[] newArray(int size) {
            return new ArticleModel[size];
        }
    };
}
