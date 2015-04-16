package com.anan.anancooking.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by kuoxin on 4/13/15.
 */
public interface StepInterface {
    String getTitle();
    String getDescription();
    Bitmap getImage();
    BitmapDrawable getImageDrawable();

}
