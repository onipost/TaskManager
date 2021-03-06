package com.example.developer.taskmanagerv05;

import android.content.Context;
import android.util.AttributeSet;


public class ButtonCustom extends android.support.v7.widget.AppCompatButton{
    public ButtonCustom(Context context, AttributeSet attrs) {
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
    public void setOpacityVal(float val){
        Setter.setOpacityProp(this, val);
    }
    public void setVisibilityVal(int val){
        Setter.setVisibilityProp(this, val);
    }
}
