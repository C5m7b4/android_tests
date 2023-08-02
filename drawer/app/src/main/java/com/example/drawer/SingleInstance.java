package com.example.drawer;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingleInstance {
    private static SingleInstance instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private SingleInstance(Context context)
    {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized SingleInstance getInstance(Context context)
    {
        if( instance == null)
        {
            instance = new SingleInstance(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue()
    {
        if ( requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {getRequestQueue().add(req);}

}
