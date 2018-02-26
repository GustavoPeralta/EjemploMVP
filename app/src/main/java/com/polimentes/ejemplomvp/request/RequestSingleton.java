package com.polimentes.ejemplomvp.request;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Grupo BECM 8 on 23/02/2018.
 */

public class RequestSingleton {
    private static Context context;
    private static RequestSingleton singleton;

    private RequestQueue requestQueue;
    private RequestQueue analyticsRequestQueue;

    private RequestSingleton(final Context context) {
        RequestSingleton.context = context;
        requestQueue = getRequestQueue();
        analyticsRequestQueue = getAnalyticsRequestQueue();
    }

    public static synchronized RequestSingleton getInstance(Context context) {
        if (singleton == null) {
            singleton = new RequestSingleton(context);
        }
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public RequestQueue getAnalyticsRequestQueue() {
        if (analyticsRequestQueue == null) {
            analyticsRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return analyticsRequestQueue;
    }
}
