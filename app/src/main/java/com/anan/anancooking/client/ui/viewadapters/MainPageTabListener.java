package com.anan.anancooking.client.ui.viewadapters;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.view.ViewPager;

/**
 * Created by kuoxin on 4/15/15.
 */
public class MainPageTabListener implements ActionBar.TabListener {
    Activity parentActivity;
    ViewPager viewPager;
    public MainPageTabListener(Activity parentActivity, ViewPager viewPager){
        this.parentActivity = parentActivity;
        this.viewPager = viewPager;
    }
    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        //tab.setCustomView(R.layout.tab_layout);
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        //tab.setCustomView(R.layout.tab_layout_unselected);
        parentActivity.getFragmentManager().beginTransaction().commit();

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }
}
