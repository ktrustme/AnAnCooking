package com.anan.anancooking.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.io.Serializable;

/**
 * Created by kuoxin on 4/13/15.
 */
public class RecipePreviewImplementation implements RecipePreviewInterface, Serializable {
    private String name = null;
    private int time = 0;
    private String ingredients = null;
    private String description = null;
    private byte[] previewByteCode;

    String recipeID = null;

    public void setDescription(String des){
        this.description = des;
    }

    @Override
    public RecipePreviewInterface setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public RecipePreviewInterface setRecipeId(String id) {
        return this;
    }

    @Override
    public String getRecipeId() {
        return recipeID;
    }

    @Override
    public RecipePreviewInterface setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    @Override
    public RecipePreviewInterface setTime(int time) {
        this.time = time;
        return this;
    }

    @Override
    public RecipePreviewInterface setPreviewByteCode(byte[] previewByteCode) {
        this.previewByteCode = previewByteCode;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
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
    public byte[] getPreviewByteCode() {
        return this.previewByteCode;
    }

    public String getDescription(){return this.description;}

}
