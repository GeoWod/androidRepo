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

import com.geowod.R;
import com.geowod.adapter.MyRecyclerViewAdapter;
import com.geowod.model.DataObject;

import java.util.ArrayList;

/**
 * Created by Praphulla on 11/26/2015.
 */
public class FriendsFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;
    private static String LOG_TAG = "FriendsFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.friends_fragment,container,false);
        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(friendsDateset());
        mRecyclerView.setAdapter(mAdapter);
        ((MyRecyclerViewAdapter)mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item  " + position);
            }
        });

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

}
