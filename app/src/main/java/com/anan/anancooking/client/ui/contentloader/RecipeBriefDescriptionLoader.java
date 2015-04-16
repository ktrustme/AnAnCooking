package com.anan.anancooking.client.ui.contentloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipeInterface;

import java.io.ByteArrayOutputStream;

/**
 * Created by kuoxin on 4/16/15.
 */


public class RecipeBriefDescriptionLoader extends AsyncTask<Long, Void, RecipeInterface> {
    Activity parentActivity = null;

    public RecipeBriefDescriptionLoader(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    /**
     * This method would download the recipe from ther server.
     * It would fetch the recipe back based on the unique recipe id
     * @param recipeIDs
     * @return
     */
    @Override
    protected RecipeInterface doInBackground(Long... recipeIDs) {
        //HTTP request, get a recipe object back;
        //the following line should be changed to send a http request and fetch a recipe back
        RecipeInterface recipe = new RecipeImplementation();

        Drawable drawable= parentActivity.getResources().getDrawable(R.drawable.fried_rice_finished);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);



        byte[] bitmapdata = stream.toByteArray();
        recipe.setPreviewByteCode(bitmapdata);

        return recipe;

    }

    /**
     * The method would be called immediately after download task
     * @param recipe
     */
    @Override
    protected void onPostExecute(RecipeInterface recipe) {
        //First find the ui component, then load content into the component.
        ImageView previewImage = (ImageView) parentActivity.findViewById(R.id.image_view_preview_recipe_intro);
        //Converting byte array to bitmap drawable is troublesome, but seems currently the best way to do that.
        previewImage.setImageDrawable(new BitmapDrawable(BitmapFactory.decodeByteArray(recipe.getPreviewByteCode(),0,recipe.getPreviewByteCode().length)));
        //其他的text之类的
        ((TextView) parentActivity.findViewById(R.id.text_view_ingredients_recipe_intro)).setText("One tomato\nTwo Eggs\n4 Teaspoon Oil\n2 Chopped Onions\n1 Green Pepper");
        ((TextView) parentActivity.findViewById(R.id.text_view_time_recipe_intro)).setText("10 Mins");
        ((TextView) parentActivity.findViewById(R.id.text_view_description_recipe_intro)).setText("What the hell is that!!!?!?!? Whatever, just make it!");
    }
}