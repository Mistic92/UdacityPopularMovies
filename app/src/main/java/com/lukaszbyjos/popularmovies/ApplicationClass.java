package com.lukaszbyjos.popularmovies;

import android.app.Application;

import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by lbyjos on 2015-12-07 on 11:25.
 */
public class ApplicationClass extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ViewTarget.setTagId(R.id.glide_request);
    }
}
