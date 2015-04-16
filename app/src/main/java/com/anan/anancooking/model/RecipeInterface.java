package com.anan.anancooking.model;

import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/**
 * Created by kuoxin on 4/4/15.
 */
public interface RecipeInterface {

    RecipeStep[] getSteps();
    String getIngredients();
    //change this part to a list
    int getTime();
    String getDescription();


    BitmapDrawable getPreview();

    RecipeInterface setIngredients(String ingredients);
    RecipeInterface setTime(int time);
    RecipeInterface setDescription(String description);
    RecipeInterface setPreview(BitmapDrawable preview);
}
