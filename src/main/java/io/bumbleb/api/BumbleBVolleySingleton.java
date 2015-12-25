package io.bumbleb.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class BumbleBVolleySingleton {
    private static BumbleBVolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private BumbleBVolleySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();


        mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(mCtx));
    }

    public static synchronized BumbleBVolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new BumbleBVolleySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
