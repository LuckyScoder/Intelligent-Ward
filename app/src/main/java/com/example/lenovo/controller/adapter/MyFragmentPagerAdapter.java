package com.example.lenovo.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/4/22.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<String> tabtitles;
    private List<Fragment> fragments;

    public MyFragmentPagerAdapter(FragmentManager fm,List<String> tabtitles,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.tabtitles = tabtitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles.get(position);
    }

    public List<String> addTitles(String str){
        tabtitles.add(str);
        return tabtitles;
    }

    public List<Fragment> addFragments(Fragment fragment) {
        fragments.add(fragment);
        return fragments;
    }
}
