package com.geowod.model;

/**
 * Created by sony on 10-10-2015.
 */
public class DataObject {
    private String mText1;
    private String mText2;

    public DataObject(String mText1, String mText2) {
        this.mText1 = mText1;
        this.mText2 = mText2;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }
}
