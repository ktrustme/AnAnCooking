package com.anan.anancooking.client.ui.viewadapters;

import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by kuoxin on 4/29/15.
 */
public class ImageByteArrayToDrawableConverter {
    public static BitmapDrawable convert(byte[] imageByteArray){
        return  new BitmapDrawable(BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length));
    }
}
