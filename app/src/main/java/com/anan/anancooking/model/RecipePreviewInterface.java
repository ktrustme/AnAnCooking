package com.anan.anancooking.model;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by kuoxin on 4/13/15.
 */
public interface RecipePreviewInterface {
    RecipePreviewInterface setName(String name);

    RecipePreviewInterface setRecipeId(String id);

    String getRecipeId();

    RecipePreviewInterface setIngredients(String ingredients);

    RecipePreviewInterface setTime(int time);

    RecipePreviewInterface setPreviewByteCode(byte[] previewByteCode);

    String getName();

    String getIngredients();

    int getTime();

    byte[] getPreviewByteCode();
}
