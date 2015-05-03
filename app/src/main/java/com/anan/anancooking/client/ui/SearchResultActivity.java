package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.listeners.MainPageListViewItemClickListener;
import com.anan.anancooking.client.ui.viewadapters.MainPageListViewAdapter;
import com.anan.anancooking.client.ws.remote.AnAnNetworkProtocols;
import com.anan.anancooking.client.ws.remote.MySingletonRequestQueue;
import com.anan.anancooking.client.ws.remote.SearchCallbackInterface;
import com.anan.anancooking.client.ws.remote.SearchRequest;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends Activity implements SearchCallbackInterface {
    List<RecipePreviewImplementation> searchResultPreviewList = null;

    private ListView myListView;

    private ArrayAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();
        String ingredients = getIntent().getStringExtra("ingredients");
        setListView();
        sendSearchRequest(ingredients);

    }

    private void setListView() {
        myListView = (ListView) findViewById(R.id.search_page_listview);
        if (searchResultPreviewList == null)
            searchResultPreviewList = new ArrayList<RecipePreviewImplementation>();
        this.myListAdapter = new MainPageListViewAdapter(this, R.layout.list_item_briefintroduction, searchResultPreviewList);
        // Set the adapter between the ListView and its backing data.

        myListView.setAdapter(myListAdapter);
        myListView.setOnItemClickListener(new MainPageListViewItemClickListener(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendSearchRequest(String ingredients) {
        Toast.makeText(this, "Call send search request...", Toast.LENGTH_SHORT).show();
        RequestQueue queue = MySingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        queue.add(new SearchRequest(this, AnAnNetworkProtocols.HOST_NAME, AnAnNetworkProtocols.PORT_NUM, ingredients));
    }

    @Override
    public void setSearchResultList(List<RecipePreviewImplementation> searchResultPreviewList) {
        Toast.makeText(this, "Call set result lsit...length is "+searchResultPreviewList.size(), Toast.LENGTH_SHORT).show();
        this.searchResultPreviewList.removeAll(this.searchResultPreviewList);
        this.searchResultPreviewList.addAll(searchResultPreviewList);
        ((BaseAdapter) myListView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void displayDebug() {
        Toast.makeText(this, "For debugging...", Toast.LENGTH_SHORT).show();
    }
}
