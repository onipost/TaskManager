package com.example.developer.taskmanagerv05;

import android.content.Context;
import android.util.AttributeSet;

public class ImageViewCustom extends android.support.v7.widget.AppCompatImageView{
    private SetterBase Setter = new SetterBase(this.getContext());

    public ImageViewCustom (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setWidthVal(int val){
        Setter.setWidthProp(this, val);
    }
    public void setHeightVal(int val) {
        Setter.setHeightProp(this, val);
    }
    public void setMarginTopVal(int val){
        Setter.setMarginTopProp(this, val);
    }
    public void setOpacityVal(float val){
        Setter.setOpacityProp(this, val);
    }
    public void setVisibilityVal(int val){
        Setter.setVisibilityProp(this, val);
    }
    public void setRotateVal(float val){
        Setter.setRotateProp(this, val);
    }
}