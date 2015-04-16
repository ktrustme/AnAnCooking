package com.anan.anancooking.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.anan.anancooking.R;

/**
 * Created by kuoxin on 4/4/15.
 */
public class RecipeImplementation implements RecipeInterface{
    String ingredients = null;
    int time = 0;
    String description = null;
    RecipeStep[] steps= new RecipeStep[10];
    BitmapDrawable preview = null;



    public RecipeInterface setPreview(BitmapDrawable preview) {
        this.preview = preview;
        return this;
    }

    public RecipeInterface setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeInterface setTime(int time) {
        this.time = time;
        return this;
    }

    public RecipeInterface setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }




    @Override
    public RecipeStep[] getSteps() {
        return steps;
    }

    @Override
    public String getIngredients() {
        return ingredients;
    }

    @Override
    public int getTime() {
        return time;
    }


    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public BitmapDrawable getPreview() {
        return preview;
    }
}
