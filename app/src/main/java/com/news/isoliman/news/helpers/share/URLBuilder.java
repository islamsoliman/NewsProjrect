package com.news.isoliman.news.helpers.share;

/*
 * Copyright 2014 Wiebe Elsinga
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Class that shares a URL using an Intent to a social channel, using either the native Facebook app or the default browser,
 * depending what is installed on the device.
 *
 * @author W.Elsinga
 */
public abstract class URLBuilder {

    public static final String TYPE = "text/plain";
    private String mLink;

    private Context mContext;

    /**
     * Constructor using a context
     *
     * @param context Context
     */
    public URLBuilder(Context context) {
        mContext = context;
    }

    /**
     * Link to share
     *
     * @param link link to be shared
     */
    public void setLink(String link) {
        mLink = link;
    }

    /**
     * Share the {@code link}
     * <p/>
     * If a social native app has been installed on the device (lets say the Facebook app),
     * the {@code link} will be shared using that native app.
     * <p/>
     * If no social native app has been installed, the link will be shared by way of the default browser.
     */
    public void share() {

        try {
            mContext.getPackageManager().getPackageInfo(getPackage(), 0);

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType(TYPE);
            shareIntent.setPackage(getPackage());
            shareIntent.putExtra(Intent.EXTRA_TEXT, mLink );

            mContext.startActivity(shareIntent);
        } catch (Exception e) {
            if(getURL()=="com.whatsapp"){
                Toast.makeText(mContext, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                        .show();
            }else {
                String linkShare = null;
                try {
                    linkShare = URLEncoder.encode(mLink, "UTF-8");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                Intent openURL = new Intent(Intent.ACTION_VIEW, Uri.parse(getURL() + linkShare));
                mContext.startActivity(openURL);
            }
        }
    }

    /**
     * Get the package name of the native app to look for.
     *
     * @return package name of the native app.
     */
    protected abstract String getPackage();

    /**
     * Get the URL that needs to be shared.
     *
     * @return URL that needs to be shared
     */
    protected abstract String getURL();

}
