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
 * Created by Administrator on 2017/4/26.
 */

public class SecondUiFragment extends Fragment {

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
        View view=inflater.inflate(R.layout.second_ui,container,false);
        titles = new ArrayList<String>();
        titles.add("实时");titles.add("历史");
        fragments = new ArrayList<Fragment>();
        fragments.add(new ContentNowFragment());
        fragments.add(new ContentHistoryFragment());
        pagerAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager(),titles,fragments);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager2);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout2);
        tabLayout.setupWithViewPager(viewPager);
        //    tabLayout.setTabMode(TabLayout.MODE_FIXED);
        return view;
    }
}
