package com.lukaszbyjos.popularmovies.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import hugo.weaving.DebugLog;

/**
 * Created by Lukasz on 18.03.2016.
 */
public class DataBinder {
    private DataBinder() {
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }

    @BindingAdapter("formatYear")
    @DebugLog
    public static void formatYear(TextView textView, String dateToFormat) {
        if (dateToFormat != null) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateToFormat, DateTimeFormat.forPattern("YYYY-MM-dd"));
            final String dateToShow = localDateTime.toString("YYYY");
            textView.setText(dateToShow);
        }
    }

    @BindingAdapter("formatRating")
    @DebugLog
    public static void formatRating(TextView textView, float currentRating) {
        if (currentRating != 0.0) {
            textView.setText(currentRating + "/10");
        }
    }
}
