package com.anan.anancooking.client.ui;

import com.anan.anancooking.client.ui.contentloader.MainPageRecommendationListViewLoader;
import com.anan.anancooking.client.ui.listeners.MainPageListViewItemClickListener;
import com.anan.anancooking.client.ui.listeners.MainPageOnFreshListener;
import com.anan.anancooking.client.ui.viewadapters.*;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ws.remote.AnAnNetworkProtocols;
import com.anan.anancooking.client.ws.remote.FetchRecipeRequest;
import com.anan.anancooking.client.ws.remote.FetchRecommendastionPreviewsRequest;
import com.anan.anancooking.client.ws.remote.FetchRecommendationPreviewListCallbackInterface;
import com.anan.anancooking.client.ws.remote.MySingletonRequestQueue;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.RecipePreviewInterface;
import com.anan.anancooking.model.RecipePreviews;
import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

public class RecommendationFragment extends Fragment implements FetchRecommendationPreviewListCallbackInterface {
    List<RecipePreviewImplementation> recommendationPreviewList = null;

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
        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);

        // Retrieve the SwipeRefreshLayout and ListView instances

        mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setColorScheme(R.color.accent_color);

        // Retrieve the ListView
        myListView = (ListView) view.findViewById(R.id.listview_mainpage);
        //listmyListView.addHeaderView(inflater.inflate(R.layout.recommendation_fragment_header, null));

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
        /*
        this.myListAdapter = new MainPageListViewAdapter(
                getActivity(),
                R.layout.list_item_briefintroduction,
                RecipePreviews.randomList(LIST_ITEM_COUNT));
        */

        if(recommendationPreviewList == null)
            recommendationPreviewList = new  ArrayList<RecipePreviewImplementation>();
        this.myListAdapter = new MainPageListViewAdapter(getActivity(), R.layout.list_item_briefintroduction, recommendationPreviewList);
        // Set the adapter between the ListView and its backing data.
        myListView.setAdapter(myListAdapter);
        //mySwipeRefreshLayout.setOnRefreshListener(new MainPageOnFreshListener(this.getActivity(), myListAdapter));

        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initiateRefresh();
            }
        });

        mySwipeRefreshLayout.setProgressViewOffset(false, 0,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        initiateRefresh();
    }
// END_INCLUDE (setup_views)

    private void initiateRefresh() {
        /**
         * Execute the background task, which uses {@link android.os.AsyncTask} to load the data.
         */
        //new DummyBackgroundTask().execute();
        //new MainPageRecommendationListViewLoader(this.getActivity(), myListAdapter,mySwipeRefreshLayout).execute();
        mySwipeRefreshLayout.setRefreshing(true);

        //if(this.recommendationPreviewList == null)
        sendFetchRecommendationPreviewListRequest();
    }

    @Override
    public void setRecommendationPreviewList(List<RecipePreviewImplementation> recommendationPreviewList) {
        this.recommendationPreviewList.removeAll(this.recommendationPreviewList);
        this.recommendationPreviewList.addAll(recommendationPreviewList);
        ((BaseAdapter) myListView.getAdapter()).notifyDataSetChanged();

        mySwipeRefreshLayout.setRefreshing(false);
    }


    public void sendFetchRecommendationPreviewListRequest(){
        //if(this.recommendationPreviewList == null) {
            RequestQueue queue = MySingletonRequestQueue.getInstance(getActivity().getApplicationContext()).getRequestQueue();
            queue.add(new FetchRecommendastionPreviewsRequest(this,
                    AnAnNetworkProtocols.HOST_NAME, AnAnNetworkProtocols.PORT_NUM));
        //}
    }

    public void displayDebug(){
        Toast.makeText(getActivity(),"done?",Toast.LENGTH_LONG).show();
    }
}
