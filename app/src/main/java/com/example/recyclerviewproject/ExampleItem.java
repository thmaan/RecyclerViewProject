package com.example.recyclerviewproject;
public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public ExampleItem(int imageResource, String text1, String tex2){
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = tex2;
    }
    public void changeText1(String text){
        mText1 = text;
    }
    public void changeText2(String text){
        mText2 = text;
    }
    public int getmImageResource(){
        return mImageResource;
    }
    public String getmText1(){
        return mText1;
    }
    public String getmText2(){
        return mText2;
    }
}
