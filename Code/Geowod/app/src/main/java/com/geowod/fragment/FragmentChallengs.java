package com.geowod.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.geowod.R;

/**
 * Created by sony on 13-10-2015.
 */
public class FragmentChallengs extends Fragment {

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_challenge,container,false);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();

        String[] todo = {"Los Angles","New York","San Diego","Huston"};
        ListAdapter todoAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, todo);
        ListView listtodo = (ListView) mView.findViewById(R.id.listtodo);
        listtodo.setAdapter(todoAdapter);

        String[] completed = {"Chicago","San Francisco","San Joes","Dallas"};
        ListAdapter completedAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, completed);
        ListView listcompleted = (ListView)mView.findViewById(R.id.listcompleted);
        listcompleted.setAdapter(completedAdapter);

    }
}
