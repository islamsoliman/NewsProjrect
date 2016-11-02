package com.news.isoliman.news.viewcontrollers;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.news.isoliman.news.R;
import com.news.isoliman.news.helpers.share.FacebookShare;
import com.news.isoliman.news.helpers.share.GooglePlusShare;
import com.news.isoliman.news.helpers.share.LinkedInShare;
import com.news.isoliman.news.helpers.share.TwitterShare;
import com.news.isoliman.news.helpers.share.WhatsAPPshare;


public class ShareDialog {

    String url;
    public ShareDialog(Context mContext, String linkUrl) {
        showRateDialog(mContext);
        this.url = linkUrl;
    }

    @SuppressLint("NewApi")
    private void showRateDialog(final Context mContext) {

        final Dialog dialog = new Dialog(mContext);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.share, null);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(true);


        Button btnFaceBook = (Button) layout.findViewById(R.id.btnFaceBook);

        btnFaceBook.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FacebookShare sharer = new FacebookShare(mContext);
                sharer.setLink(url);
                sharer.share();
            }
        });

        Button btnTwitter = (Button) layout.findViewById(R.id.btnTwitter);

        btnTwitter.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {


                TwitterShare sharer = new TwitterShare(mContext);
                sharer.setLink(url);
                sharer.share();

            }
        });

        Button btnLinkedIn = (Button) layout.findViewById(R.id.btnLinkedIn);

        btnLinkedIn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                LinkedInShare sharer = new LinkedInShare(mContext);
                sharer.setLink(url);
                sharer.share();

            }
        });
        Button btnWhats = (Button) layout.findViewById(R.id.btnWhats);
        btnWhats.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {


                WhatsAPPshare sharer = new WhatsAPPshare(mContext);
                sharer.setLink(url);
                sharer.share();

            }
        });


        Button btnGoogle = (Button) layout.findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                GooglePlusShare sharer = new GooglePlusShare(mContext);
                sharer.setLink(url);
                sharer.share();


            }
        });
        dialog.setContentView(layout);
        dialog.show();


    }


}