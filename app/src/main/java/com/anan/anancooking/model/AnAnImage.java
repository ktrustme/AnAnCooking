package com.anan.anancooking.model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by kuoxin on 4/16/15.
 */
public class AnAnImage implements Serializable {
    Bitmap bitmap = null;

    public AnAnImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
