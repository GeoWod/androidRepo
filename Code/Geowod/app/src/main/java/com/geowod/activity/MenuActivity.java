package com.geowod.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.geowod.R;
import com.geowod.fragment.FragmentChallengs;
import com.geowod.fragment.FragmentLocation;
import com.geowod.fragment.FragmentProfie;
import com.geowod.fragment.FragmentProfile1;

/**
 * Created by Praphulla on 11/24/2015.
 */
public class MenuActivity extends FragmentActivity implements View.OnClickListener{


    private DrawerLayout mDrawerLayout;
    private ImageView img_menu,header_image;
    private RelativeLayout rel_menu;
    private TextView tvheader,header_name;
    private LinearLayout home, challenges, leaderboard, earnPoints, profile,logout;
    private RelativeLayout rel_fragment_container;
    private LinearLayout img_right,img_filter;
    private int selectedPos=1;


    private String userid,firstname,photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main1);
        initialiseViews(savedInstanceState);
        setOnClickListner();

    }

    private void setOnClickListner() {

        img_menu.setOnClickListener(this);
        home.setOnClickListener(this);
        challenges.setOnClickListener(this);
        logout.setOnClickListener(this);
        leaderboard.setOnClickListener(this);
        profile.setOnClickListener(this);
        earnPoints.setOnClickListener(this);
        img_filter.setOnClickListener(this);
        img_right.setOnClickListener(this);

    }

    private void initialiseViews(Bundle savedInstanceState) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        img_menu = (ImageView) findViewById(R.id.img_menu);
        rel_menu = (RelativeLayout) findViewById(R.id.rel_menu);
        tvheader = (TextView) findViewById(R.id.tvheader);
        home = (LinearLayout) findViewById(R.id.home);
        challenges = (LinearLayout) findViewById(R.id.challenges);
        leaderboard = (LinearLayout) findViewById(R.id.leaderboard);
        earnPoints = (LinearLayout) findViewById(R.id.earnPoints);
        profile = (LinearLayout) findViewById(R.id.profile);
        logout = (LinearLayout) findViewById(R.id.logout);
        rel_fragment_container = (RelativeLayout) findViewById(R.id.rel_fragment_container);

        img_right= (LinearLayout) findViewById(R.id.img_right);
        img_filter= (LinearLayout) findViewById(R.id.img_filter);




        if (savedInstanceState == null) {

            if (getIntent().getStringExtra("menuactivity") != null) {
                if (getIntent().getStringExtra("menuactivity").equals(
                        "menuactivity")) {
                    displayView(1);
                } else {
                    displayView(0);
                }
            }  else {
                displayView(1);
            }

        }


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.img_menu) {
            mDrawerLayout.openDrawer(rel_menu);


        } else if (v.getId() == R.id.home) {
            displayView(1);
        } else if (v.getId() == R.id.challenges) {
            displayView(2);
        } else if (v.getId() == R.id.leaderboard) {
            displayView(3);
        } else if (v.getId() == R.id.earnPoints) {
            displayView(4);
        } else if (v.getId() == R.id.profile) {
            displayView(5);
        }  else if (v.getId() == R.id.img_right) {

            selectRightButton(selectedPos);

        }else if (v.getId() == R.id.img_filter) {

            Intent intent=new Intent(MenuActivity.this,FilterActivity.class);
            startActivity(intent);

        }else if (v.getId() == R.id.logout) {

            displayView(6);
        }


    }

    private void displayView(int position) {

        Fragment fragment = null;

        switch (position) {

            case 0:
                //   tvheader.setText("My Projects");
                //  fragment = new FragmentHome();
                tvheader.setText("Profile");
                selectedPos=position;
                setRightButton(position);
            //    fragment = new FragmentProfile();
                break;

            case 1:
                //changeHeader();
                tvheader.setText("Home");
                selectedPos=position;
                setRightButton(position);
                fragment = new FragmentLocation();
                //   fragment = new FragmentRecentlyUser();
                break;

            case 2:
                //changeHeader();
                tvheader.setText("Challenges");
                selectedPos=position;
                setRightButton(position);
                   fragment = new FragmentChallengs();
          //      fragment = new FragmentUserPortfolio();
                break;


            case 3:
                //changeHeader();
                tvheader.setText("LeaderBoard");
                selectedPos=position;
                setRightButton(position);
            //    fragment = new FragmentContractor();

                break;

            case 4:
                tvheader.setText("EarnPoints");
                selectedPos=position;
                setRightButton(position);
              //  fragment = new FragmentInbox();

                break;

            case 5:

                tvheader.setText("Profile");
                selectedPos=position;
                setRightButton(position);
                fragment=new FragmentProfile1();

                break;

            case 6:
                logout();
                break;


            default:

                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.rel_fragment_container, fragment).commit();
            // mDrawerLayout.setItemChecked(position, true);

            // mDrawerLayout.setSelection(position);
            setTitle("");
            mDrawerLayout.closeDrawer(rel_menu);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }


    }

    private void setRightButton(int position)
    {
        switch (position) {
            case 0:
                img_right.setVisibility(View.INVISIBLE);
                img_filter.setVisibility(View.INVISIBLE);
                break;

            case 1:

                img_right.setVisibility(View.VISIBLE);
                img_filter.setVisibility(View.VISIBLE);
                break;

            case 2:
                img_right.setVisibility(View.INVISIBLE);
                img_filter.setVisibility(View.INVISIBLE);

                break;


            case 3:
                img_right.setVisibility(View.INVISIBLE);
                img_filter.setVisibility(View.INVISIBLE);
                /*img_right.setBackgroundResource(R.drawable.add);
                img_right.setPadding(10, 10, 10, 10);*/
                break;

            case 4:
                img_right.setVisibility(View.INVISIBLE);
                img_filter.setVisibility(View.INVISIBLE);
                break;

            case 5:
                img_right.setVisibility(View.INVISIBLE);
                img_filter.setVisibility(View.INVISIBLE);
                break;

            case 6:
                img_right.setVisibility(View.INVISIBLE);
                img_filter.setVisibility(View.INVISIBLE);
                break;

            default:

                break;
        }

    }

    private void selectRightButton(int position)
    {
        switch (position) {
            case 0:
                break;

            case 1:
               /* Intent intent=new Intent(ActivityMenu.this,AddProject.class);
                startActivity(intent);*/
                break;

            case 2:
                /*Intent intent_portfolio=new Intent(ActivityMenu.this,ActivityAddFolder.class);
                startActivity(intent_portfolio);*/
                break;


            case 3:
               /* Intent intent_contractor=new Intent(ActivityMenu.this,ActivityAddContractor.class);
                startActivity(intent_contractor);*/
                break;

            case 4:

                break;

            case 5:

                break;

            case 6:

                break;


            default:

                break;
        }

    }

    private  void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

        builder.setTitle("Geowod");
        builder.setMessage("Are you sure want to logout?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

                Intent intent=new Intent(MenuActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();

               /* SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("loginuserdata", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor_new = sharedPreferences.edit();
                editor_new.clear();
                editor_new.commit();

                SharedPreferences sharedPreferences1 = getApplicationContext().getSharedPreferences("fbuserdata", getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor_new1 = sharedPreferences1.edit();
                editor_new1.clear();
                editor_new1.commit();

                LoginManager.getInstance().logOut();


                logoutApiCall(userid, deviceType, key, timestamp);*/

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        builder.show();
    }


    private void logoutApiCall( String userid, String deviceType, String key, String timestamp) {

        final ProgressDialog pDialog = new ProgressDialog(MenuActivity.this);
        pDialog.show();
        pDialog.setMessage(getString(R.string.please_wait));
        pDialog.setCancelable(false);


    }
    public Typeface getTypeFaceBold() {
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),
                "Raleway-Bold_0.ttf");
        return typefaceBold;
    }
    public  Typeface getTypeFaceRegular() {
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),
                "Raleway-Regular_0.ttf");
        return typefaceBold;
    }

    public  Typeface getTypeFaceThin() {
        Typeface typefaceBold = Typeface.createFromAsset(getAssets(),
                "Raleway-Thin_0.ttf");
        return typefaceBold;
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //	super.onBackPressed();
        exitFromApp();

    }

    private void exitFromApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

        builder.setTitle("Geowod");
        builder.setMessage("Are you sure want to exit from Geowod?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
				/*moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);*/

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        builder.show();
    }
}
