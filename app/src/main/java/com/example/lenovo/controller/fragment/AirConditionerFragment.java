package com.example.lenovo.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.controller.R;

/**
 * Created by Administrator on 2017/4/22.
 */

public class AirConditionerFragment extends Fragment {

    //空调状态定义
    public static final int POWER_UP = 1;
    public static final int POWER_DOWN = 0;
    public static final int REFRIGERATION = 2; //制冷
    public static final int HEAT = 3;          //制热
    public static final int LARGE_AIR = 4;     //风量大
    public static final int MIDDLE_AIR = 5;    //风量中
    public static final int SMALL_AIR = 6;     //风量小

    private int power;
    private int mode;
    private int air;
    private int r_temperature;

    private static final String TAG = "AirConditionerFragment ";

    public static AirConditionerFragment newInstance(int page){
        Bundle args = new Bundle();
        args.putInt(TAG, page);
        AirConditionerFragment fragment = new AirConditionerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.airconditoner_fragment, container, false);
        return view;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getAir() {
        return air;
    }

    public void setAir(int air) {
        this.air = air;
    }

    public int getR_temperature() {
        return r_temperature;
    }

    public void setR_temperature(int r_temperature) {
        this.r_temperature = r_temperature;
    }
}
