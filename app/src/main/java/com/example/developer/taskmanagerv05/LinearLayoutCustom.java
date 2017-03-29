package com.example.developer.taskmanagerv05;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class LinearLayoutCustom extends LinearLayout {
    public LinearLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private SetterBase Setter = new SetterBase(this.getContext());
    public void setWidthVal(int val){
        Setter.setWidthProp(this, val);
    }
    public void setHeightVal(int val) {
        Setter.setHeightProp(this, val);
    }
    public void setMarginTopVal(int val){
        Setter.setMarginTopProp(this, val);
    }
    public void setMarginRightVal(int val){
        Setter.setMarginRightProp(this, val);
    }
    public void setOpacityVal(float val){
        Setter.setOpacityProp(this, val);
    }
    public void setVisibilityVal(int val){
        Setter.setVisibilityProp(this, val);
    }
}
