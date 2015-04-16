package com.anan.anancooking.client.ui;

import com.anan.anancooking.client.ui.listeners.MainPageListViewItemClickListener;
import com.anan.anancooking.client.ui.viewadapters.*;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.anan.anancooking.R;
import com.anan.anancooking.model.BriefRecipe;
import com.anan.anancooking.model.Recipes;

import java.util.List;

public class RecommendationFragment extends Fragment{


    private static final int LIST_ITEM_COUNT = 5;

    private SwipeRefreshLayout mySwipeRefreshLayout;

    private ListView myListView;

    private ArrayAdapter myListAdapter;


    /**
     * p
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecommendationFragment.
     */

    public static RecommendationFragment newInstance() {
        RecommendationFragment fragment = new RecommendationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public RecommendationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommendation2, container, false);

        // Retrieve the SwipeRefreshLayout and ListView instances
        mySwipeRefreshLayout = (SwipeRefreshLayout) getView();
        mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setColorScheme(R.color.accent_color);

        // Retrieve the ListView
        myListView = (ListView) view.findViewById(android.R.id.list);
        myListView.setOnItemClickListener(new MainPageListViewItemClickListener(this.getActivity()));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * Create an ArrayAdapter to contain the data for the ListView. Each item in the ListView
         * uses the system-defined simple_list_item_1 layout that contains one TextView.
         */
        myListAdapter = new MainPageListViewAdapter(
                getActivity(),
                R.layout.list_item_briefintroduction,
                Recipes.randomList(LIST_ITEM_COUNT));

        // Set the adapter between the ListView and its backing data.
        myListView.setAdapter(myListAdapter);


        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh();
            }
        });
        // END_INCLUDE (setup_refreshlistener)
    }
    // END_INCLUDE (setup_views)

    private void initiateRefresh() {
        /**
         * Execute the background task, which uses {@link android.os.AsyncTask} to load the data.
         */
        new DummyBackgroundTask().execute();
    }

    private void onRefreshComplete(List<BriefRecipe> result) {

        // Remove all items from the ListAdapter, and then replace them with the new items
        myListAdapter.clear();
        for (BriefRecipe recipe : result) {
            myListAdapter.add(recipe);
        }
        // Stop the refreshing indicator
        mySwipeRefreshLayout.setRefreshing(false);
    }


    private class DummyBackgroundTask extends AsyncTask<Void, Void, List<BriefRecipe>> {

        static final int TASK_DURATION = 3 * 1000; // 3 seconds

        @Override
        protected List<BriefRecipe> doInBackground(Void... params) {
            // Sleep for a small amount of time to simulate a background-task
            try {
                Thread.sleep(TASK_DURATION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Return a new random list of cheeses
            return Recipes.randomList(LIST_ITEM_COUNT);
        }

        @Override
        protected void onPostExecute(List<BriefRecipe> result) {
            super.onPostExecute(result);

            // Tell the Fragment that the refresh has completed
            onRefreshComplete(result);
        }
    }

}
