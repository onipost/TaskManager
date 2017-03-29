package com.example.developer.taskmanagerv05;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


public class RelativeLayoutCustom extends RelativeLayout {
    private SetterBase Setter = new SetterBase(this.getContext());

    public RelativeLayoutCustom(Context context, AttributeSet attrs) {
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
    public void setMarginBottomVal(int val){
        Setter.setMarginBottomProp(this, val);
    }
    public void setMarginRightVal(int val){
        Setter.setMarginRightProp(this, val);
    }
    public void setMarginLeftVal(int val){
        Setter.setMarginLeftProp(this, val);
    }
    public void setPaddingLeftVal(int val){
        Setter.setPaddingLeftProp(this, val);
    }
    public void setPaddingRightVal(int val){
        Setter.setPaddingRightProp(this, val);
    }
    public void setOpacityVal(float val){
        Setter.setOpacityProp(this, val);
    }
    public void setVisibilityVal(int val){
        Setter.setVisibilityProp(this, val);
    }
    public void getVisibilityVal(){
        this.getVisibility();

    }
}
