package com.geowod.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geowod.R;
import com.geowod.config.Config;
import com.geowod.crop.CropImage;
import com.geowod.network.HttpRequestManager;
import com.geowod.utils.Constant;
import com.geowod.utils.MyApplication;
import com.geowod.widget.MyProgressDialog;
import org.json.JSONObject;

/**
 * Created by Praphulla on 11/8/2015.
 */
public class RegistrationActivity extends RegistratioBaseActivity implements View.OnClickListener {

    private Button signupbutton;
    private EditText firstNameEdittext, lastNameEdittext, emailEdittext, passwordEdittext,mobileEdittext;
    private MyProgressDialog mProgressDialog;
    private ImageView prfileImageView;
    private String userImage = "";
    private Bitmap imagebitmap = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        initView();
        setOnClickListner();

    }

    private void setOnClickListner() {

        signupbutton.setOnClickListener(this);
        prfileImageView.setOnClickListener(this);

    }

    private void initView() {

        signupbutton = (Button) findViewById(R.id.signupbutton);
        firstNameEdittext = (EditText) findViewById(R.id.firstNameEdittext);
        lastNameEdittext = (EditText) findViewById(R.id.lastNameEdittext);
        emailEdittext = (EditText) findViewById(R.id.emailEdittext);
        mobileEdittext= (EditText) findViewById(R.id.mobileEdittext);
        passwordEdittext = (EditText) findViewById(R.id.passwordEdittext);
        prfileImageView = (ImageView) findViewById(R.id.prfileImageView);

    }

    public void registration() {

        registrationRequest(emailEdittext.getText().toString(), firstNameEdittext.getText().toString(), lastNameEdittext.getText().toString(),
                mobileEdittext.getText().toString(),"1","1","1","1","ggg",passwordEdittext.getText().toString());
        //  this.finish();
        //  startActivity(new Intent(LoginActivity.this,DrawerActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupbutton:
                registration();
                break;

            case R.id.prfileImageView:

                showTakeImagePopup();

                break;

            default:
                Log.d("Something went wrong", "onClick ");
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            System.out.println("on cameraaaaa");
            String mCurrentPhotoPath = "";

            Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED, MediaStore.Images.ImageColumns.ORIENTATION}, MediaStore.Images.Media.DATE_ADDED, null,
                    "date_added ASC");
            Log.v("cursor", "cursor=" + cursor);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Uri uri = Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));

                    mCurrentPhotoPath = uri.toString();

                    Log.v("cursor", "uri=" + uri);
                    Log.v("cursor", "mCurrentPhotoPath=" + mCurrentPhotoPath);
                } while (cursor.moveToNext());
                cursor.close();
            }
            sendCameraSuccessCode(mCurrentPhotoPath, CAMERA_REQUEST);
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            /*
			 * Bitmap bitmap = getBitmapFromCameraData(data, this); //
			 * updateUserRequestModel.setPhoto(bitmap);
			 */
            Uri uri = data.getData();

            Log.v("", "contactUri=" + uri);
            ContentResolver cR = getContentResolver();
            String type = cR.getType(uri);
            Log.v("", "file type=" + type);
            String imagePath = getRealPathFromURI(uri);

            Intent intent;
            intent = new Intent(this, CropImage.class);
            intent.putExtra("image-path", imagePath);
            intent.putExtra("scale", true);
            intent.putExtra("circleCrop", false);
            intent.putExtra("return-data", false);
            startActivityForResult(intent, 3);

            // imageButtonProfilePic.setImageBitmap(createScaledBitmap(bitmap));
            // textChoose.setVisibility(View.GONE);
        } else if (requestCode == 3) {
            if (data != null) {
                mCurrentPhotoPath = data.getExtras().getString("imgPath");
                userImage = mCurrentPhotoPath;
               /* Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
                bitmap = Constatnts.getRoundedShape(bitmap);

                // TODO Set image To Bitmap
                isImageSelected = true;
                imgProfilePic.setImageBitmap(createScaledBitmap(bitmap));*/


                Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath);
                bitmap = Constant.getRoundedShape(bitmap);
                imagebitmap = bitmap;
                prfileImageView.setImageBitmap(createScaledBitmap(bitmap));


            }
        }


    }

    private void checkValidation() {

       /* if (fname.getText().toString().equalsIgnoreCase("")) {
            IoUtils.showAlert(RegistrationActivity.this, getString(R.string.alert), getString(R.string.message_enter_firstname));
        } else if (email.getText().toString().equalsIgnoreCase("")) {
            IoUtils.showAlert(RegistrationActivity.this, getString(R.string.alert), getString(R.string.message_enter_email));
        } else if (!email.getText().toString().matches(IoUtils.EMAIL_PATTERN)) {
            IoUtils.showAlert(RegistrationActivity.this, getString(R.string.alert), getString(R.string.message_invalid_email));
        } else if (password.getText().toString().equalsIgnoreCase("")) {
            IoUtils.showAlert(RegistrationActivity.this, getString(R.string.alert), getString(R.string.message_enter_password));
        } else if (password.getText().toString().length() < 6) {
            IoUtils.showAlert(RegistrationActivity.this, getString(R.string.alert), "Password length should be atleast 6");
        } else if (cpassword.getText().toString().equalsIgnoreCase("")) {
            IoUtils.showAlert(RegistrationActivity.this, getString(R.string.alert), getString(R.string.message_enter_cpassword));
        } else if (!password.getText().toString().equalsIgnoreCase(cpassword.getText().toString())) {
            IoUtils.showAlert(RegistrationActivity.this, getString(R.string.alert), getString(R.string.message_password_not_match));
        } else {

        }*/
    }
    private void registrationRequest(String email,
                                     String firstname, String lastname, String mobilenumber, String locationpermission,
                                     String camerapermission, String notificationpermission,
                                     String contactpermission, String profilepic, String password) {
        mProgressDialog = MyProgressDialog.show(RegistrationActivity.this, "",
                "", true, false, null);
        final String TAG = Config.ACTION_REGISTRATION;
        HttpRequestManager.regRequest(RegistrationActivity.this,
                email, firstname, lastname, mobilenumber, locationpermission, camerapermission, notificationpermission, contactpermission, profilepic, password, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        MyApplication.getInstance().getRequestQueue()
                                .getCache().clear();
                        mProgressDialog.dismiss();

                        finish();
                 //       startActivity(new Intent(RegistrationActivity.this, DrawerActivity.class));


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
