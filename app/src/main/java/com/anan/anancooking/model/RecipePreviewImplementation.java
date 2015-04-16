package com.anan.anancooking.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by kuoxin on 4/13/15.
 */
public class RecipePreviewImplementation implements RecipePreviewInterface {
    String name = null;
    int time = 0;
    String ingredients = null;
    byte[] previewByteCode;


    @Override
    public RecipePreviewInterface setName(String name) {
        this.name = name;
        return this;
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

}
