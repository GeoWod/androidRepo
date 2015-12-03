package com.geowod.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.geowod.R;
import com.geowod.config.Config;
import com.geowod.network.HttpRequestManager;
import com.geowod.utils.MyApplication;
import com.geowod.widget.MyProgressDialog;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sony on 10-10-2015.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private Button button;
    private EditText passEditText,emaileditText;
    private TextView fbText,signupbutton;
    private CallbackManager callbackManager;
    private MyProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        button= (Button) findViewById(R.id.button);
        signupbutton= (TextView) findViewById(R.id.signupbutton);
        passEditText = (EditText) findViewById(R.id.passEditText);
        emaileditText = (EditText) findViewById(R.id.emaileditText);
        fbText=(TextView)findViewById(R.id.fbText);
        button.setOnClickListener(this);
        fbText.setOnClickListener(this);
        signupbutton.setOnClickListener(this);

    }

    public void login(String email,String password)
    {

            loginRequest(email,password);
      //  this.finish();
      //  startActivity(new Intent(LoginActivity.this,DrawerActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button:
              //  login(emaileditText.getText().toString(),passEditText.getText().toString());
                startActivity(new Intent(LoginActivity.this,MenuActivity.class));
                break;

            case R.id.fbText:

                FacebookSdk.sdkInitialize(getApplicationContext());
                callbackManager = CallbackManager.Factory.create();
                checkFacebookLogin();
              //  LoginManager.getInstance().logOut();
                break;

            case R.id.signupbutton:

                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);

                break;

             default:
                 Log.d("Something went wrong", "onClick ");
        }
    }

    //facebook login++++++++++++++++++++++++++++++

    private void checkFacebookLogin() {

        ArrayList<String> list = new ArrayList<String>();
        list.add("email");
        LoginManager.getInstance().logInWithReadPermissions(this, list);

        /*mProgressDialog = MyProgressDialog.show(LoginActivity.this, "",
                "", true, false, null);*/

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(final LoginResult result) {
                 Toast.makeText(LoginActivity.this, "Login Succesfull", Toast.LENGTH_LONG).show();


                GraphRequest request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject json, GraphResponse response) {
                        if (response.getError() != null) {
                            System.out.println("ERROR");
                            Log.d("errorTag", "onCompleted ");
                        } else {
                            System.out.println("Success");
                            try {

                                String jsonresult = String.valueOf(json);
                                System.out.println("JSON Result" + jsonresult);

                                JSONObject job = new JSONObject(jsonresult);
                                String email = job.getString("email");
                                String name = job.getString("name");
                                String id = job.getString("id");
                                String photoUrl = "http://graph.facebook.com/" + id + "/picture?type=large";

                                Log.i("", "name111" + name);
                                Log.i("", "socialId1111" + id);
                                Log.i("", "email111" + email);

                                finish();
                           //     startActivity(new Intent(LoginActivity.this, DrawerActivity.class));

                                /*IoUtils.storeFbDataToPreferance(ActivityLogin.this, id, email, photoUrl, name);

                                IoUtils.hideSoftKeyboard(loginButton, ActivityLogin.this);

                                String timestamp = IoUtils.ts;
                                String key = "InteriorApp";
                                String temp = email+timestamp+key;
                                String md5 = IoUtils.getMd5For(temp);

                                checkUserAccount(email, id, md5, timestamp);*/


                                //  socialAuthApiCall(email, name, id, photoUrl, "facebook", "A", regid, IoUtils.key, IoUtils.ts);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender, birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Log.d("oncancel", "onCancel ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("onerror", "onError ");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // twitterButton.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void loginRequest(String username,String password) {
        mProgressDialog = MyProgressDialog.show(LoginActivity.this, "",
                "", true, false, null);
        final String TAG = Config.ACTION_LOGIN;
        HttpRequestManager.loginRequest(LoginActivity.this,
                username, password, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        MyApplication.getInstance().getRequestQueue()
                                .getCache().clear();
                        mProgressDialog.dismiss();

                        finish();
                        //  startActivity(new Intent(LoginActivity.this, DrawerActivity.class));


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgressDialog.dismiss();
                        MyApplication.getInstance().getRequestQueue()
                                .getCache().clear();
                        final int statusCode = (error != null && error.networkResponse != null) ? error.networkResponse.statusCode
                                : 0;
                        final String errorMessage = (error != null && error.networkResponse != null) ? new String(
                                error.networkResponse.data) : "";
                        Log.d(TAG, "statusCode: " + statusCode);
                        Log.d(TAG, "errorMessage: " + errorMessage);
                    }
                });
        }

}
