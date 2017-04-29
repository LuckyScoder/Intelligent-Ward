package com.example.lenovo.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.controller.R;
import com.example.lenovo.controller.adapter.MyFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class ThirdUiFragment extends Fragment {

    private MyFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    private List<String> titles;

    private List<Fragment> fragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.third_ui,container,false);
        titles = new ArrayList<String>();
        titles.add("空调");
        fragments = new ArrayList<Fragment>();
        fragments.add(new AirConditionerFragment());
        pagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),titles,fragments);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    //    tabLayout.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }
}
