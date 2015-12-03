package com.geowod.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.geowod.R;

/**
 * Created by Praphulla on 11/25/2015.
 */
public class FilterActivity extends Activity implements View.OnClickListener{

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_activity);


        initView();
        setOnClickListner();



    }

    private void setOnClickListner() {

        backButton.setOnClickListener(this);

    }

    private void initView() {

        backButton=(ImageView)findViewById(R.id.backButton);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.backButton :

                finish();

                break;

                default:

                    break;
        }

    }
}
