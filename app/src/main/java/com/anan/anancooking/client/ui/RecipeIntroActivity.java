package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.exception.MyUncaughtExceptionHandler;
import com.anan.anancooking.client.ui.contentloader.RecipeBriefDescriptionLoader;


public class RecipeIntroActivity extends Activity {
    long recipeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_intro);
        setIngredientsText();
        setDescriptionText();
        setTimeText();
        setPreviewImage();
        new RecipeBriefDescriptionLoader(this).execute(recipeId);
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(this));
    }

    private void setIngredientsText() {
        //hardcode, ~~~ lalala
        ((TextView) findViewById(R.id.text_view_ingredients_recipe_intro)).setText("Ingredients...");
    }

    private void setDescriptionText() {
        ((TextView) findViewById(R.id.text_view_time_recipe_intro)).setText("time...");
    }

    private void setTimeText() {
        ((TextView) findViewById(R.id.text_view_description_recipe_intro)).setText("description...");
    }

    private void setPreviewImage() {
        ImageView iv = (ImageView) findViewById(R.id.image_view_preview_recipe_intro);
        //new Handler().post(new Loader(iv, R.drawable.fried_rice_finished));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        iv.setAdjustViewBounds(true);
        iv.setLayoutParams(new LinearLayout.LayoutParams(width, width * 3 / 4));
        iv.requestLayout();
    }

    public void startSteps(View view) {
        Intent intent = new Intent(this, com.anan.anancooking.client.ui.UseRecipeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
