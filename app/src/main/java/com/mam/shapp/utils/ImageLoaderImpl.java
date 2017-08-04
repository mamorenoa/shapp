package com.mam.shapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class ImageLoaderImpl implements ImageLoader {

    private Picasso picassoInstance;

    public ImageLoaderImpl(Context context) {
        this.picassoInstance = Picasso.with(context);
    }

    public void loadImage(String url, ImageView imageView, int placeholder, boolean caching) {
        if (caching) {
            if (placeholder > -1) {
                this.picassoInstance.load(url).placeholder(placeholder).into(imageView);
            } else {
                this.picassoInstance.load(url).into(imageView);
            }
        } else {
            if (placeholder > -1) {
                this.picassoInstance.load(url).memoryPolicy(MemoryPolicy.NO_CACHE).placeholder(placeholder).into(imageView);
            } else {
                this.picassoInstance.load(url).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
            }
        }
    }
}
