package com.anan.anancooking.client.ui.listeners;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;

import com.anan.anancooking.client.ui.contentloader.MainPageRecommendationListViewLoader;
import com.anan.anancooking.model.RecipePreviewInterface;

/**
 * Created by kuoxin on 4/16/15.
 */
public class MainPageOnFreshListener implements SwipeRefreshLayout.OnRefreshListener {
    Activity parentActivity = null;
    ArrayAdapter<RecipePreviewInterface> myListAdapter;
SwipeRefreshLayout swiperefresh = null;
    public MainPageOnFreshListener(Activity parentActivity, ArrayAdapter<RecipePreviewInterface> myListAdapter, SwipeRefreshLayout swiperefresh) {
        this.parentActivity = parentActivity;
        this.swiperefresh = swiperefresh;
        this.myListAdapter = myListAdapter;
    }

    @Override
    public void onRefresh() {
        new MainPageRecommendationListViewLoader(parentActivity, myListAdapter,swiperefresh).execute();
    }
}
