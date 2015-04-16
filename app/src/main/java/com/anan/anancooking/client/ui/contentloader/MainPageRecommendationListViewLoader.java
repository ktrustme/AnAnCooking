package com.anan.anancooking.client.ui.contentloader;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.viewadapters.CustomListViewAdapterUseRecipe;
import com.anan.anancooking.client.ui.viewadapters.MainPageListViewAdapter;
import com.anan.anancooking.model.RecipePreviewInterface;
import com.anan.anancooking.model.RecipePreviews;

import java.util.List;

/**
 * Created by kuoxin on 4/16/15.
 */


public class MainPageRecommendationListViewLoader extends AsyncTask<Void, Void, List<RecipePreviewInterface>> {

    static final int TASK_DURATION = 3 * 1000; // 3 seconds
    static final int LIST_ITEM_COUNT = 5;
    Activity parentActivity = null;
    ArrayAdapter<RecipePreviewInterface> myListAdapter;
    SwipeRefreshLayout mySwipeRefreshLayout = null;

    public MainPageRecommendationListViewLoader(Activity parentActivity, ArrayAdapter<RecipePreviewInterface> myListAdapter, SwipeRefreshLayout swiperefresh) {
        this.parentActivity = parentActivity;
        this.myListAdapter = myListAdapter;
        this.mySwipeRefreshLayout = swiperefresh;
    }

    @Override
    protected List<RecipePreviewInterface> doInBackground(Void... params) {
        // Sleep for a small amount of time to simulate a background-task
        try {
            Thread.sleep(TASK_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Return a new random list of cheeses
        return RecipePreviews.randomList(LIST_ITEM_COUNT);
    }

    @Override
    protected void onPostExecute(List<RecipePreviewInterface> result) {
        super.onPostExecute(result);

        // Tell the Fragment that the refresh has completed
        //onRefreshComplete(result);
        this.myListAdapter.clear();
        for (RecipePreviewInterface recipe : result) {
            this.myListAdapter.add(recipe);
        }
        // Stop the refreshing indicator
        mySwipeRefreshLayout.setRefreshing(false);

    }
}