package com.example.developer.taskmanagerv05;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class SetterBase extends View{

    protected String TAG = "SetterBase_EXCEPTION";

    public SetterBase(Context context) {
        super(context);
    }

    public void setWidthProp(View view,int val){
        try {
            ViewGroup.LayoutParams lParams = view.getLayoutParams();
            lParams.width = val;
            view.setLayoutParams(lParams);
        }
        catch (Exception exception){
            Log.d(TAG, view+" HASN`T WIDTH. EXCEPTION: "+exception);
        }
    }
    public void setHeightProp(View view, int val) {
        try {
            ViewGroup.LayoutParams lParams = view.getLayoutParams();
            lParams.height = val;
            view.setLayoutParams(lParams);
        }
        catch (Exception exception){
            Log.d(TAG, view+" HASN`T HEIGHT. EXCEPTION: "+exception);
        }
    }

    public void setMarginTopProp(View view, int val){
        try {
            ViewGroup.MarginLayoutParams lParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            lParams.topMargin = val;
            view.setLayoutParams(lParams);
        }
        catch (Exception exception) {
            Log.d(TAG, view+" HASN`T MARGIN_TOP. EXCEPTION: "+exception);

        }
    }

    public void setMarginBottomProp(View view, int val){
        try {
            ViewGroup.MarginLayoutParams lParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            lParams.bottomMargin= val;
            view.setLayoutParams(lParams);
        }
        catch (Exception exception) {
            Log.d(TAG, view+" HASN`T MARGIN_BOTTOM. EXCEPTION: "+exception);

        }
    }
    public void setMarginRightProp(View view, int val){
        try {
            ViewGroup.MarginLayoutParams lParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            lParams.rightMargin= val;
            view.setLayoutParams(lParams);
        }
        catch (Exception exception) {
            Log.d(TAG, view+" HASN`T MARGIN_RIGHT. EXCEPTION: "+exception);

        }
    }
    public void setMarginLeftProp(View view, int val){
        try {
            ViewGroup.MarginLayoutParams lParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            lParams.leftMargin= val;
            view.setLayoutParams(lParams);
        }
        catch (Exception exception) {
            Log.d(TAG, view+" HASN`T MARGIN_LEFT. EXCEPTION: "+exception);

        }
    }
    public void setOpacityProp(View view, float val){
        try {
            view.setAlpha(val);
        }
        catch (Exception exception){
            Log.d(TAG, view+" HASN`T OPACITY. EXCEPTION: "+exception);
        }
    }
    public void setVisibilityProp(View view,int val){
        try {
            switch (val){
                case 1:
                    view.setVisibility(View.VISIBLE);
                    break;
                case 0:
                    view.setVisibility(View.INVISIBLE);
            }
        }
        catch (Exception exception){
            Log.d(TAG, view+" HASN`T VISIBILITY. EXCEPTION: "+exception);
        }
    }
    public void setRotateProp(View view, float val){
        try{
            view.setRotation(val);
        }
        catch (Exception exception){
            Log.d(TAG, view+" HASN`T ROTATE. EXCEPTION: "+exception);
        }
    }
    public void setPaddingLeftProp(View view, int val){
        try {
            view.setPadding(val, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        }
        catch (Exception exception) {
            Log.d(TAG, view+" HASN`T PADDING_LEFT. EXCEPTION: "+exception);

        }
    }
    public void setPaddingRightProp(View view, int val){
        try {
            view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), val, view.getPaddingBottom());
        }
        catch (Exception exception) {
            Log.d(TAG, view+" HASN`T PADDING_RIGHT. EXCEPTION: "+exception);

        }
    }
}
