package com.topster.topster.firstlab_cinema;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

/**
 * Created by felipefujioka on 1/4/16.
 */
public class LoadingScreenViewModel extends BaseObservable{

    private int value = 0;
    private String message;

    public LoadingScreenViewModel(){
        this.value = 0;
    }
    @Bindable
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        notifyPropertyChanged(BR.value);
        notifyPropertyChanged(BR.message);
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public String getMessage() {
        return ""+value;
    }

    public void onClickButton(View v){
        setValue(getValue()+1);
    }
}
