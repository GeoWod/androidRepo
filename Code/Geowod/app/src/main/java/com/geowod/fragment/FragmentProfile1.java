package com.geowod.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.geowod.R;
import com.geowod.adapter.MyRecyclerViewAdapter;
import com.geowod.model.DataObject;

import java.util.ArrayList;

/**
 * Created by Praphulla on 11/26/2015.
 */
public class FragmentProfile1 extends Fragment implements View.OnClickListener{


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "RecyclerViewActivity";

    private View view;
    private ViewPager mPager;
    private RelativeLayout lin_profile, lin_mygeowod,lin_friends;
    private TextView viewProfileText, viewProfileLine, myGeowodText, mygeowodLine,friendsText,friendsLine;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment1,container,false);


        initiatizeViews();

        return view;
    }



    private void initiatizeViews() {

        lin_profile = (RelativeLayout) view.findViewById(R.id.lin_profile);
        lin_mygeowod = (RelativeLayout)view.findViewById(R.id.lin_mygeowod);
        lin_friends = (RelativeLayout)view.findViewById(R.id.lin_friends);
        viewProfileText = (TextView)view.findViewById(R.id.viewProfileText);
        viewProfileLine = (TextView)view.findViewById(R.id.viewProfileLine);
        myGeowodText = (TextView)view.findViewById(R.id.myGeowodText);
        mygeowodLine = (TextView)view.findViewById(R.id.mygeowodLine);
        friendsText = (TextView)view.findViewById(R.id.friendsText);
        friendsLine = (TextView)view.findViewById(R.id.friendsLine);
        mPager = (ViewPager) view.findViewById(R.id.pager);

        lin_profile.setOnClickListener(this);
        lin_mygeowod.setOnClickListener(this);
        lin_friends.setOnClickListener(this);


        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPage(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPager.setAdapter(new ScreenSlidePagerAdapter(getActivity()
                .getSupportFragmentManager()));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.lin_profile:

                viewPage(0);
                break;
            case R.id.lin_mygeowod:

                viewPage(1);
                break;
            case R.id.lin_friends:

                viewPage(2);
                break;
            default:
                Toast.makeText(getActivity(), "something went wrong", Toast.LENGTH_LONG).show();
                break;
        }

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {

                return new ViewProfileFragment();
            } else  if (position == 1){

                return new MyGeowodFragment();
            }else
            {
                return new FriendsFragment();
            }



        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private void viewPage(int pos) {

        if (pos == 0) {


            Toast.makeText(getActivity(),"pos 0 click",Toast.LENGTH_SHORT).show();
            viewProfileText.setTextColor(Color.parseColor("#000000"));
            myGeowodText.setTextColor(Color.parseColor("#c3c3c3"));
            friendsText.setTextColor(Color.parseColor("#c3c3c3"));
            viewProfileLine.setVisibility(View.VISIBLE);
            mygeowodLine.setVisibility(View.INVISIBLE);
            friendsLine.setVisibility(View.INVISIBLE);
            mPager.setCurrentItem(0);
        }
        if (pos == 1) {

            Toast.makeText(getActivity(),"pos 1 click",Toast.LENGTH_SHORT).show();
            myGeowodText.setTextColor(Color.parseColor("#000000"));
            friendsText.setTextColor(Color.parseColor("#c3c3c3"));
            viewProfileText.setTextColor(Color.parseColor("#c3c3c3"));
            mygeowodLine.setVisibility(View.VISIBLE);
            friendsLine.setVisibility(View.INVISIBLE);
            viewProfileLine.setVisibility(View.INVISIBLE);
            mPager.setCurrentItem(1);
        }
        if (pos == 2) {

            Toast.makeText(getActivity(),"pos 2 click",Toast.LENGTH_SHORT).show();
            friendsText.setTextColor(Color.parseColor("#000000"));
            myGeowodText.setTextColor(Color.parseColor("#c3c3c3"));
            viewProfileText.setTextColor(Color.parseColor("#c3c3c3"));
            friendsLine.setVisibility(View.VISIBLE);
            mygeowodLine.setVisibility(View.INVISIBLE);
            viewProfileLine.setVisibility(View.INVISIBLE);
            mPager.setCurrentItem(2);
        }
    }



}
