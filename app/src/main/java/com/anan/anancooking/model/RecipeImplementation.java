package com.anan.anancooking.model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.anan.anancooking.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kuoxin on 4/4/15.
 */
public class RecipeImplementation implements RecipeInterface, Serializable {
    private String recipeName = null;
    private String recipeID = null;
    private String ingredients = null;
    private int time = 0;
    private String description = null;
    private ArrayList<Step> steps = new ArrayList<Step>();
    private byte[] previewByteCode;

    @Override
    public RecipeInterface setSteps(ArrayList<Step> steps) {
        this.steps = steps;
        return null;
    }

    @Override
    public RecipeInterface setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public RecipeInterface setPreviewByteCode(byte[] previewByteCode) {
        this.previewByteCode = previewByteCode;
        return this;
    }


    @Override
    public RecipeInterface setTime(int time) {
        this.time = time;
        return this;
    }

    @Override
    public RecipeInterface setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    @Override
    public java.util.ArrayList<Step> getSteps() {
        return this.steps;
    }

    @Override
    public String getIngredients() {
        return this.ingredients;
    }

    @Override
    public int getTime() {
        return this.time;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public byte[] getPreviewByteCode() {
        return this.previewByteCode;
    }

    @Override
    public String getName() {
        return recipeName;
    }

    @Override
    public RecipeInterface setName(String recipeName) {
        this.recipeName = recipeName;
        return this;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeId) {
        this.recipeID = recipeId;
    }
}
