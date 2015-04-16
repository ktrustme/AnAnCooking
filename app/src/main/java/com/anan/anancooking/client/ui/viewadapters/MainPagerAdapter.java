package com.anan.anancooking.client.ui.viewadapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.RecommendationFragment;

import java.util.ArrayList;

/**
 * Created by kuoxin on 4/15/15.
 */


public class MainPagerAdapter extends FragmentPagerAdapter {
    Activity parentActivity = null;
    ArrayList<Fragment> fragmentsList = new ArrayList<Fragment>();
    ArrayList<String> titlesList = new ArrayList<String>();

    public void addFragment(Fragment fragment, String title) {
        fragmentsList.add(fragment);
        titlesList.add(title);
        return;
    }

    @Override
    public Fragment getItem(int i) {
        /*
        Fragment fragment = null;
        if (i >= fragmentsList.size())
            fragment = RecommendationFragment.newInstance("f", "f");
        Bundle args = new Bundle();
        */
        if (i <= getCount())
            return fragmentsList.get(i);
        else
            return null;
    }


    public MainPagerAdapter(FragmentManager fm, Activity parentActivity) {
        super(fm);
        this.parentActivity = parentActivity;
    }


    @Override
    public int getCount() {
        return fragmentsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlesList.get(position);
    }


    /*

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // Inflate a new layout from our resources
        View view = parentActivity.getLayoutInflater().inflate(R.layout.pager_item,
                container, false);
        // Add the newly created View to the ViewPager
        container.addView(view);

        // Retrieve a TextView from the inflated View, and update it's text
        TextView title = (TextView) view.findViewById(R.id.item_title);
        title.setText(String.valueOf(position + 1));


        // Return the View
        return view;
    }
    */

}
