package com.geowod.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.location.Location;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application  {

    private static MyApplication singleton;
    public static SharedPreferences sharedPreferences;
    public static Editor editor;
    public static boolean isAppRunning = true;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    public static final String TAG = MyApplication.class.getSimpleName();
    public static final String LOCATION_UPDATED = "Location updated";
    public static final String LOCATION_TIMED_OUT = "Location timed out";
    public static final String EXTRA_LOCATION = "Extra location";
    private static Location loc;
    private static Activity activity;
    private static int type;
    //Typeface
    public static Typeface myriadProRegular;
    public static Typeface myriadProBold;

    public static synchronized MyApplication getInstance() {
        Log.v("MyApplication", "getInstance");
        return singleton;
    }

    public void onCreate() {
        super.onCreate();
        singleton = this;
        Log.v("MyApplication", "onCreate");
        Constant.volleySSLSocket();




//        Mint.initAndStartSession(getApplicationContext(), "8872f76e");

    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
    }




    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        MyApplication.type = type;
    }


    // volley

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }



    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }





    /**
     * Checks whether two providers are the same
     */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    /**
     * Determines whether one Location reading is better than the current
     * Location fix
     *
     * @param location            The new Location that you want to evaluate
     * @param currentBestLocation The current Location fix, to which you want to compare the new
     *                            one
     */

}
