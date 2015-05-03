package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anan.anancooking.R;
import com.anan.anancooking.client.exception.MyUncaughtExceptionHandler;
import com.anan.anancooking.client.ui.contentloader.RecipeBriefDescriptionLoader;
import com.anan.anancooking.client.util.DbUtil;
import com.anan.anancooking.client.ws.remote.AnAnNetworkProtocols;
import com.anan.anancooking.client.ws.remote.FetchRecipeRequest;
import com.anan.anancooking.client.ws.remote.FetchRecipeRequestCallbackInterface;
import com.anan.anancooking.client.ws.remote.MySingletonRequestQueue;
import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipeInterface;
import com.anan.anancooking.model.Step;
import com.android.volley.RequestQueue;

import java.util.List;


public class RecipeIntroActivity extends Activity implements FetchRecipeRequestCallbackInterface {

    RecipeInterface recipe = null;
    Button saveButton = null;
    Button satartUseRecipeButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_intro);
        setIngredientsText("loading");
        setDescriptionText("loading");
        setTimeText("loading");
        setPreviewImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_rotate));
        disableSaveButton();
        disableStartUseRecipeButton();
        //new RecipeBriefDescriptionLoader(this).execute(recipeId);
        if (getIntent().getStringExtra("recipe_id") != null) {
            RequestQueue queue = MySingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
            int id = Integer.valueOf(getIntent().getStringExtra("recipe_id"));//Hardcoded ID~~~~~~lalalallalal~~~
            queue.add(new FetchRecipeRequest(this, AnAnNetworkProtocols.HOST_NAME, AnAnNetworkProtocols.PORT_NUM, id));
        } else if(getIntent().getExtras()!=null){
            vanishSaveButton();
            enableStartUseRecipeButton();
            saveButton.setVisibility(View.GONE);
            Bundle bn = getIntent().getExtras();
            this.recipe = (RecipeImplementation) bn.getSerializable("recipe");
            setTimeText("" + recipe.getTime());
            setDescriptionText(recipe.getDescription());
            if(recipe.getPreviewByteCode()!=null)
                setPreviewImage(BitmapFactory.decodeByteArray(recipe.getPreviewByteCode(), 0, recipe.getPreviewByteCode().length));
            setIngredientsText(recipe.getIngredients());
            System.out.println("Recipe step length is " + recipe.getSteps().size());
        }

        //Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(this));
    }

    private void vanishSaveButton() {
        if (saveButton == null)
            saveButton = (Button) findViewById(R.id.button_save_recipe);
        saveButton.setVisibility(View.GONE);
        findViewById(R.id.button_imageview_save_recipe).setVisibility(View.GONE);
        saveButton.setClickable(false);
    }

    private void enableSaveButton() {
        if (saveButton == null)
            saveButton = (Button) findViewById(R.id.button_save_recipe);
        saveButton.setVisibility(View.VISIBLE);
        saveButton.setClickable(true);
    }

    private void disableSaveButton() {
        if (saveButton == null)
            saveButton = (Button) findViewById(R.id.button_save_recipe);
        saveButton.setVisibility(View.VISIBLE);
        saveButton.setClickable(false);
    }

    private void enableStartUseRecipeButton() {
        if (satartUseRecipeButton == null)
            satartUseRecipeButton = (Button) findViewById(R.id.button_start_use_recipe_activity);
        satartUseRecipeButton.setClickable(true);
    }

    private void disableStartUseRecipeButton() {
        if (satartUseRecipeButton == null)
            satartUseRecipeButton = (Button) findViewById(R.id.button_start_use_recipe_activity);
        satartUseRecipeButton.setClickable(false);
    }


    @Override
    public void setIngredientsText(String ingredientsText) {
        //hardcode, ~~~ lalala
        ((TextView) findViewById(R.id.text_view_ingredients_recipe_intro)).setText(ingredientsText);
    }

    @Override
    public void setDescriptionText(String descriptionText) {
        ((TextView) findViewById(R.id.text_view_description_recipe_intro)).setText(descriptionText);
    }

    @Override
    public void setTimeText(String timeText) {
        ((TextView) findViewById(R.id.text_view_time_recipe_intro)).setText(timeText);
    }

    @Override
    public void setPreviewImage(Bitmap bitmap) {
        ImageView iv = (ImageView) findViewById(R.id.image_view_preview_recipe_intro);
        //new Handler().post(new Loader(iv, R.drawable.fried_rice_finished));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        iv.setAdjustViewBounds(true);
        iv.setLayoutParams(new LinearLayout.LayoutParams(width, width * 3 / 4));
        iv.requestLayout();

        iv.setImageBitmap(bitmap);
    }

    @Override
    public void setRecipe(RecipeInterface recipe) {
        this.recipe = recipe;
        enableSaveButton();
        enableStartUseRecipeButton();

    }

    public void startSteps(View view) {
        UseRecipeActivity.steps = this.recipe.getSteps();

        Intent intent = new Intent(this, com.anan.anancooking.client.ui.UseRecipeActivity.class);

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


    public void saveToDb(View view) {
        DbUtil db = new DbUtil(this);
        //RecipeImplementation clicked_recipe
        System.out.println("Ingredints is " + recipe.getIngredients());
        System.out.println("Description is " + recipe.getDescription());
        System.out.println("Name is " + recipe.getName());
        System.out.println("Time is " + recipe.getTime());
        db.PutRecipe((RecipeImplementation) recipe);
        Toast.makeText(this, "Recipe Saved ^_^", Toast.LENGTH_SHORT).show();

    }
}
