package com.anan.anancooking.client.ws.remote;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.anan.anancooking.model.RecipeInterface;

/**
 * Created by kuoxin on 4/30/15.
 */
public interface FetchRecipeRequestCallbackInterface {
    public void setIngredientsText(String ingredientsText);
    public void setDescriptionText(String descriptionText);
    public void setTimeText(String timeText);
    public void setPreviewImage(Bitmap bitmap);
    public void setRecipe(RecipeInterface recipe);
}
