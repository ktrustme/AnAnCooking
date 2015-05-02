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
    String recipeName = null;
    String ingredients = null;
    int time = 0;
    String description = null;
    ArrayList<Step> steps = new ArrayList<Step>();
    byte[] previewByteCode;

    public void setName(String name){
        this.recipeName = name;
    }

    @Override
    public RecipeInterface setSteps(ArrayList<Step> steps) {
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

}
