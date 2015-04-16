package com.anan.anancooking.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by kuoxin on 4/13/15.
 */
public interface StepInterface {
    void setName(String name);
    void setDescription(String description);
    void setImageByteCode(byte[] imageByteCode);
    String getName();
    String getDescription();
    byte[] getImageByteCode();

}
