package com.anan.anancooking.client.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by kuoxin on 5/3/15.
 */
public class ImageReduce {
    public static Bitmap resizeBitMapImage1(byte[] imageBytes) {
        Bitmap bitMapImage = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);

            double sampleSize = 0;
            Boolean scaleByHeight = Math.abs(options.outHeight) >= Math.abs(options.outWidth);
            int scaleFactor=1;
            while (options.outHeight/scaleFactor * options.outWidth/scaleFactor * 2 >= 1638) {
                scaleFactor ++;
                /*
                sampleSize = scaleByHeight ? options.outHeight /2 : options.outWidth / 2;
                sampleSize = (int) Math.pow(2d, Math.floor(Math.log(sampleSize) / Math.log(2d)));
                */
            }
            sampleSize = scaleFactor;
            sampleSize = (int) Math.pow(2d, Math.floor(Math.log(sampleSize) / Math.log(2d)));
            options.inJustDecodeBounds = false;
            options.inTempStorage = new byte[128];
            while (true) {
                try {
                    options.inSampleSize = (int) sampleSize;
                    bitMapImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);
                    break;
                } catch (Exception ex) {
                    try {
                        sampleSize = sampleSize * 2;
                    } catch (Exception ex1) {

                    }
                }
            }
        } catch (Exception ex) {

        }
        return bitMapImage;
    }
}
