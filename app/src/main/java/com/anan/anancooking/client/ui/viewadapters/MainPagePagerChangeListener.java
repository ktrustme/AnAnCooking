package com.anan.anancooking.client.ui.viewadapters;

import android.app.Activity;
import android.support.v4.view.ViewPager;

/**
 * Created by kuoxin on 4/15/15.
 */
public class MainPagePagerChangeListener extends ViewPager.SimpleOnPageChangeListener{
    Activity parentActivity = null;
    public MainPagePagerChangeListener(Activity parentActivity)
    {
        this.parentActivity = parentActivity;
    }
    @Override
    public void onPageSelected(int position) {
        //parentActivity.getActionBar().setSelectedNavigationItem(position);
    }
}
