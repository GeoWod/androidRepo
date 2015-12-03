package com.geowod.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.geowod.R;

/**
 * Created by sony on 25-10-2015.
 */
public class CreateGeowodActivity extends AppCompatActivity implements View.OnClickListener {

    private View view;
    private ViewPager mPager;
    private LinearLayout amraplayout,fortimeLayout,intervallayout;
    private RelativeLayout amrapcontainer, fortimeContainer,intervalContaier;
    private Spinner repetitionSpinner, movementSpinner;
    private TextView amrapTxtvw, amrapLine, fortimeTxt, fortimeline,intervaltxt,intervalLine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creategeowod);
        initializeView();
        initMovementSpinner();
        initRepetitionSpinner();
    }

    private void initRepetitionSpinner() {
        String[] repetition= {"1","2","3","4","5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,repetition);
        repetitionSpinner.setAdapter(adapter);

    }

    private void initMovementSpinner() {

        String[] movement= {"Air squat 1","Air squat 2","Air squat 3","Air squat 4","Air squat 5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,movement);
        movementSpinner.setAdapter(adapter);
    }

    private void initializeView()
    {
        mPager = (ViewPager)findViewById(R.id.pager);
        amraplayout= (LinearLayout) findViewById(R.id.amrap_L_layout);
        fortimeLayout= (LinearLayout) findViewById(R.id.fortime_L_layout);
        intervallayout= (LinearLayout) findViewById(R.id.interval_L_layout);
        amrapcontainer = (RelativeLayout) findViewById(R.id.amrap_relative_alyout);
        fortimeContainer = (RelativeLayout) findViewById(R.id.fortime_rel_layout);
        intervalContaier = (RelativeLayout) findViewById(R.id.intervals_rel_layout);

        amrapTxtvw = (TextView) findViewById(R.id.amraptxtVw);
        amrapLine = (TextView) findViewById(R.id.amrapLine);
        fortimeTxt = (TextView) findViewById(R.id.forTimeText);
        fortimeline = (TextView) findViewById(R.id.fortimeLine);
        intervaltxt = (TextView) findViewById(R.id.intervalsText);
        intervalLine = (TextView) findViewById(R.id.intervalsLine);

        repetitionSpinner = (Spinner) findViewById(R.id.repetitions_spinner);
        movementSpinner = (Spinner) findViewById(R.id.movement_spinner);

        amrapcontainer.setOnClickListener(this);
        fortimeContainer.setOnClickListener(this);
        intervalContaier.setOnClickListener(this);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void viewPage(int pos) {

        if (pos == 0) {


            amrapTxtvw.setTextColor(Color.parseColor("#000000"));
            fortimeTxt.setTextColor(Color.parseColor("#c3c3c3"));
            intervaltxt.setTextColor(Color.parseColor("#c3c3c3"));
            amrapLine.setVisibility(View.VISIBLE);
            fortimeline.setVisibility(View.INVISIBLE);
            intervalLine.setVisibility(View.INVISIBLE);

            amraplayout.setVisibility(View.VISIBLE);
            intervallayout.setVisibility(View.GONE);
            fortimeLayout.setVisibility(View.GONE);

        }
        if (pos == 1) {

            fortimeTxt.setTextColor(Color.parseColor("#000000"));
            amrapTxtvw.setTextColor(Color.parseColor("#c3c3c3"));
            intervaltxt.setTextColor(Color.parseColor("#c3c3c3"));
            fortimeline.setVisibility(View.VISIBLE);
            amrapLine.setVisibility(View.INVISIBLE);
            intervalLine.setVisibility(View.INVISIBLE);

            amraplayout.setVisibility(View.GONE);
            intervallayout.setVisibility(View.GONE);
            fortimeLayout.setVisibility(View.VISIBLE);


        }
        if (pos == 2) {

            intervaltxt.setTextColor(Color.parseColor("#000000"));
            amrapTxtvw.setTextColor(Color.parseColor("#c3c3c3"));
            fortimeTxt.setTextColor(Color.parseColor("#c3c3c3"));
            intervalLine.setVisibility(View.VISIBLE);
            amrapLine.setVisibility(View.INVISIBLE);
            fortimeline.setVisibility(View.INVISIBLE);

            amraplayout.setVisibility(View.GONE);
            intervallayout.setVisibility(View.VISIBLE);
            fortimeLayout.setVisibility(View.GONE);


        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.amrap_relative_alyout:
                viewPage(0);
                break;
            case R.id.fortime_rel_layout:
                viewPage(1);
                break;
            case R.id.intervals_rel_layout:
                viewPage(2);
                break;
            default:
                break;
        }
    }
}
