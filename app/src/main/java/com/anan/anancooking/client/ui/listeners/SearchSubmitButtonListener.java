package com.anan.anancooking.client.ui.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.SearchView;

import com.anan.anancooking.client.ui.MainPageActivity;
import com.anan.anancooking.client.ui.SearchResultActivity;

/**
 * Created by kuoxin on 4/15/15.
 */
public class SearchSubmitButtonListener implements SearchView.OnQueryTextListener {
    Activity parentActivity = null;

    public SearchSubmitButtonListener(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query != null) {
            Intent intent = new Intent(parentActivity, SearchResultActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            intent.putExtra("ingredients", query);
            parentActivity.startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
