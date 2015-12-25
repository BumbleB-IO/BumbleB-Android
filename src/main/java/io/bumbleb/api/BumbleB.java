package io.bumbleb.api;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Response;

import java.util.HashMap;

/**
 * Created by shaib on 12/23/2015.
 */
public class BumbleB {
    private static final String API_KEY = "pl3tyKWdljSBr";

    public static String createTrendingRequestUrl(int limit, int offset){
        Uri.Builder builder = createBaseRequestUrl();
        builder.appendPath("trending");
        addLimitAndOffset(limit, offset, builder);
        return builder.build().toString();
    }

    public static String createSearchRequestUrl(String query, int limit, int offset){
        Uri.Builder builder = createBaseRequestUrl();
        builder.appendPath("search");
        builder.appendQueryParameter("q", query);
        addLimitAndOffset(limit, offset, builder);
        return builder.build().toString();
    }
    public static String createSoundByIdRequestUrl(String id){
        Uri.Builder builder = createBaseRequestUrl();
        builder.appendPath(id);
        return builder.build().toString();
    }

    public static void runRequest(String url, Context context, Response.Listener<BumbleBApiResponse> positiveResponseListener, Response.ErrorListener errorResponseListener){

        HashMap<String, String> requestHeaders = new HashMap<>();

        GsonRequest<BumbleBApiResponse> request = new GsonRequest(url, BumbleBApiResponse.class, requestHeaders, positiveResponseListener, errorResponseListener);

        // Access the RequestQueue through your singleton class.
        BumbleBVolleySingleton.getInstance(context).addToRequestQueue(request);
    }

    private static Uri.Builder createBaseRequestUrl(){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("api.bumbleb.io")
                .appendPath("v1")
                .appendPath("sounds")
                .appendQueryParameter("api_key", API_KEY);

        return builder;
    }

    private static void addLimitAndOffset(int limit, int offset, Uri.Builder builder) {
        if(limit > 0) {
            builder.appendQueryParameter("limit", Integer.toString(limit));
        }
        if(offset > 0) {
            builder.appendQueryParameter("offset", Integer.toString(limit));
        }
    }
}
