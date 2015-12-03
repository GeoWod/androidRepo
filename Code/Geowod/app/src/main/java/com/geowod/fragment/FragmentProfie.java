package com.geowod.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.geowod.R;
import com.geowod.adapter.MyRecyclerViewAdapter;
import com.geowod.model.DataObject;

import java.util.ArrayList;

/**
 * Created by sony on 09-10-2015.
 */
public class FragmentProfie extends Fragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "RecyclerViewActivity";
    private Button btn_profile;
    private Button btn_geowood;
    private Button btn_friends;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.profile_fragment,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(profileDataset());
        mRecyclerView.setAdapter(mAdapter);
        initiatizeViews();



//        RecyclerView.ItemDecoration itemDecoration =  new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
//        mRecyclerView.addItemDecoration(itemDecoration);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new
                                                                          MyRecyclerViewAdapter.MyClickListener() {
                                                                              @Override
                                                                              public void onItemClick(int position, View v) {
                                                                                  Log.i(LOG_TAG, " Clicked on Item " + position);
                                                                              }
                                                                          });
    }

    private void initiatizeViews() {

        btn_friends = (Button) view.findViewById(R.id.button_friends);
        btn_profile = (Button) view.findViewById(R.id.button_profile);
        btn_geowood = (Button) view.findViewById(R.id.button_geowood);
        btn_friends.setOnClickListener(this);
        btn_profile.setOnClickListener(this);
        btn_geowood.setOnClickListener(this);
    }

    private ArrayList<DataObject> profileDataset() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 2; index++) {
            DataObject obj;
            if (index==0)
             obj= new DataObject("My Name " ,"UserName " );
            else
                obj = new DataObject("My Contact Number ","123456789 ");
            results.add(index, obj);
        }
        return results;
    }

    private ArrayList<DataObject> friendsDateset() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 10; index++) {
            DataObject obj = new DataObject("My Friend " + index,
                    "Friend " + index);
            results.add(index, obj);
        }
        return results;
    }

    private ArrayList<DataObject> geowoodDateset() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 11; index++) {
            DataObject obj = new DataObject("my geowood " + index,
                    "geowood " + index);
            results.add(index, obj);
        }
        return results;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_friends:

               btn_friends.setBackgroundColor(getResources().getColor(R.color.toolbarcolor));
                btn_friends.setTextColor(getResources().getColor(R.color.whitecolor));
                btn_geowood.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                btn_profile.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                btn_geowood.setTextColor(getResources().getColor(R.color.toolbarcolor));
                btn_profile.setTextColor(getResources().getColor(R.color.toolbarcolor));

                mAdapter = new MyRecyclerViewAdapter(friendsDateset());
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.button_profile:

                btn_profile.setBackgroundColor(getResources().getColor(R.color.toolbarcolor));
                btn_profile.setTextColor(getResources().getColor(R.color.whitecolor));
                btn_geowood.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                btn_friends.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                btn_geowood.setTextColor(getResources().getColor(R.color.toolbarcolor));
                btn_friends.setTextColor(getResources().getColor(R.color.toolbarcolor));

                mAdapter = new MyRecyclerViewAdapter(profileDataset());
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.button_geowood:

                btn_geowood.setBackgroundColor(getResources().getColor(R.color.toolbarcolor));
                btn_geowood.setTextColor(getResources().getColor(R.color.whitecolor));
                btn_profile.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                btn_friends.setBackgroundColor(getResources().getColor(R.color.whitecolor));
                btn_profile.setTextColor(getResources().getColor(R.color.toolbarcolor));
                btn_friends.setTextColor(getResources().getColor(R.color.toolbarcolor));

                mAdapter = new MyRecyclerViewAdapter(geowoodDateset());
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                break;
            default:
                Toast.makeText(getActivity(),"something went wrong",Toast.LENGTH_LONG).show();
                break;
        }

    }


}
