package com.example.lenovo.controller;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.lenovo.controller.fragment.FirstUiFragment;
import com.example.lenovo.controller.fragment.SecondUiFragment;
import com.example.lenovo.controller.fragment.ThirdUiFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{
    private Toolbar toolbar;
    private FirstUiFragment firstUi;
    private SecondUiFragment secondUi;
    private ThirdUiFragment thirdUi;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationBar=(BottomNavigationBar)findViewById(R.id.bottom);
        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp,"Now")
                        .setActiveColorResource(R.color.darkforestgreen))
                .addItem(new BottomNavigationItem(R.drawable.ic_assessment_black_24dp,"Today")
                        .setActiveColorResource(R.color.darkforestgreen))
                .addItem(new BottomNavigationItem(R.drawable.ic_tune_black_24dp,"Recently")
                        .setActiveColorResource(R.color.darkforestgreen))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
        navigationView.setCheckedItem(R.id.nav_account);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
        //设置默认的Fragment
        setDefaultFragment();
    }

    private void setDefaultFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        firstUi=new FirstUiFragment();
        transaction.replace(R.id.content,firstUi);
        transaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch(position){
            case 0:
                if(firstUi==null){
                    firstUi=new FirstUiFragment();
                    transaction.add(R.id.content,firstUi);
                }
                else{
                    transaction.show(firstUi);
                }
                break;
            case 1:
                if(secondUi==null){
                    secondUi=new SecondUiFragment();
                    transaction.add(R.id.content,secondUi);
                }
                transaction.show(secondUi);
                break;
            case 2:
                if(thirdUi==null){
                    thirdUi=new ThirdUiFragment();
                    transaction.add(R.id.content,thirdUi);
                }
                transaction.show(thirdUi);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    private void hideFragments(FragmentTransaction transaction) {
        if (firstUi != null) {
            transaction.hide(firstUi);
        }
        if (secondUi != null) {
            transaction.hide(secondUi);
        }
        if (thirdUi != null) {
            transaction.hide(thirdUi);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }
}
