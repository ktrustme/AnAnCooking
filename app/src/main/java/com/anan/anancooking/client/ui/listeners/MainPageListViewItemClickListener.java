package com.anan.anancooking.client.ui.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.RecipeIntroActivity;

/**
 * Created by kuoxin on 4/16/15.
 */
public class MainPageListViewItemClickListener implements AdapterView.OnItemClickListener {
    Activity parentActivity = null;

    public MainPageListViewItemClickListener(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(parentActivity, RecipeIntroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        String recipe_id = (String) ((TextView) view.findViewById(R.id.hidden_recipe_id)).getText();
        intent.putExtra("recipe_id", recipe_id );
        parentActivity.startActivity(intent);
    }
}
