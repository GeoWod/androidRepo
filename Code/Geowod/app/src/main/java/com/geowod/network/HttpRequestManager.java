package com.geowod.network;

import android.content.Context;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.geowod.config.Config;
import com.geowod.utils.MyApplication;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestManager {

    private static String devId;
    public static String result;
    //private static HttpResponse httpResponse;
    private static Context mContext;
    private static final int CONNECTION_TIMEOUT_IN_MILLIS = 30000;
    private static final int REQUEST_TIMEOUT_IN_MILLIS = 30000;
    private static final String DEVICE_TYPE = "Android";

    public static void loginRequest(Context context, String email,
                                    String password, Listener<JSONObject> listener,
                                    ErrorListener errorListner) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("emailid", email);
            jsonObject.put("password", password);
            //   jsonObject.put("device_token", getDevice());
            //   jsonObject.put("device_type", DEVICE_TYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mContext = context;
        String url = Config.BASE_URL1 + Config.ACTION_LOGIN;

        processRequestPost(url, jsonObject, listener, errorListner);
    }

    public static void regRequest(Context context, String email,
                                  String firstname, String lastname, String mobilenumber, String locationpermission,
                                  String camerapermission, String notificationpermission,
                                  String contactpermission, String profilepic, String password, Listener<JSONObject> listener,
                                  ErrorListener errorListner) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("firstname", firstname);
            jsonObject.put("lastname", lastname);
            jsonObject.put("mobilenumber", mobilenumber);
            jsonObject.put("locationpermission", locationpermission);
            jsonObject.put("camerapermission", camerapermission);
            jsonObject.put("notificationpermission", notificationpermission);
            jsonObject.put("contactpermission", contactpermission);
            jsonObject.put("profilepic", profilepic);
            jsonObject.put("password", password);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mContext = context;
        String url = Config.BASE_URL1 + Config.ACTION_REGISTRATION;

        processRequestPost(url, jsonObject, listener, errorListner);
    }


    public static void getAllCategories(Context context, String categoryId,
                                        Listener<JSONObject> listener, ErrorListener errorListener) {
        mContext = context;
        String url = Config.BASE_URL + Config.ACTION_GET_ALL_CATEGORIES
                + categoryId;
        processRequestGet(url, listener, errorListener);
    }

    @SuppressWarnings({"unused", "rawtypes"})
    private static void processRequestPost(String url, JSONObject jsonObject,
                                           Listener responselistener, ErrorListener errorListner) {
        processRequest(Method.POST, url, jsonObject, responselistener,
                errorListner);
    }

    @SuppressWarnings("rawtypes")
    private static void processRequestGet(String url,
                                          Listener responselistener, ErrorListener errorListner) {
        processRequest(Method.GET, url, null, responselistener, errorListner);
    }

    @SuppressWarnings({"unused", "rawtypes"})
    private static void processJsonArrayRequestGet(String url,
                                                   JSONObject jsonObject, Listener responselistener,
                                                   ErrorListener errorListner) {
        processJsonArrayRequest(Method.GET, url, jsonObject, responselistener,
                errorListner);
    }

    /* HttpRequest */
    @SuppressWarnings({"unused", "rawtypes"})
    private static void processRequestPut(String url, JSONObject jsonObject,
                                          Listener responselistener, ErrorListener errorListner) {
        processRequest(Method.PUT, url, jsonObject, responselistener,
                errorListner);
    }

    @SuppressWarnings({"unused", "rawtypes"})
    private static void processRequestDelete(String url,
                                             Listener responselistener, ErrorListener errorListner) {
        processRequest(Method.DELETE, url, null, responselistener, errorListner);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void processRequest(int MethodPostGet, String url,
                                      JSONObject jsonObject, Listener responselistener,
                                      ErrorListener errorListner) {

        Log.v("", "url=" + url);
        Log.v("", "payload=" + jsonObject);

        JsonObjectRequest newRequest = new JsonObjectRequest(MethodPostGet,
                url, jsonObject, responselistener, errorListner) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        newRequest.setRetryPolicy(new DefaultRetryPolicy(
                REQUEST_TIMEOUT_IN_MILLIS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getInstance().addToRequestQueue(newRequest);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void processJsonArrayRequest(int MethodPostGet, String url,
                                               JSONObject jsonObject, Listener responselistener,
                                               ErrorListener errorListner) {
        Log.v("", "url=" + url);
        Log.v("", "payload=" + jsonObject);

        JsonArrayRequest newRequest = new JsonArrayRequest(MethodPostGet, url,
                jsonObject, responselistener, errorListner) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        newRequest.setRetryPolicy(new DefaultRetryPolicy(
                REQUEST_TIMEOUT_IN_MILLIS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MyApplication.getInstance().addToRequestQueue(newRequest);
    }
}
