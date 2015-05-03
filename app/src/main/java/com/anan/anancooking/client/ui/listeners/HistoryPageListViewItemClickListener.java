package com.anan.anancooking.client.ui.listeners;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.RecipeIntroActivity;
import com.anan.anancooking.client.util.DbUtil;
import com.anan.anancooking.model.RecipeImplementation;

/**
 * Created by kuoxin on 4/16/15.
 */
public class HistoryPageListViewItemClickListener implements AdapterView.OnItemClickListener {
    Activity parentActivity = null;
    RecipeImplementation recipe = null;

    public HistoryPageListViewItemClickListener(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DbUtil db = new DbUtil(parentActivity);
        String recipe_id = (String) ((TextView) view.findViewById(R.id.hidden_recipe_id)).getText();
        if(recipe_id!=null){
            recipe = db.getRecipe(recipe_id);
        }

        Bundle b = new Bundle();
        b.putSerializable("recipe", this.recipe);
        Intent intent = new Intent(parentActivity, RecipeIntroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        intent.putExtras(b);
        parentActivity.startActivity(intent);
    }
}
