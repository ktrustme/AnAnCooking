package com.anan.anancooking.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by kuoxin on 4/4/15.
 */
public interface RecipeInterface {

    ArrayList<Step> getSteps();
    String getIngredients();
    int getTime();
    String getDescription();
    byte[] getPreviewByteCode();

    RecipeInterface setTime(int time);
    RecipeInterface setIngredients(String ingredients);
    RecipeInterface setDescription(String description);
    RecipeInterface setPreviewByteCode(byte[] previewByteCode);
    //RecipeInterface setSteps(ArrayList<Step> steps);
}
