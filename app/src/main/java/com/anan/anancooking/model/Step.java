package com.anan.anancooking.model;
import android.widget.*;
/**
 * Created by zihsiangsyu on 4/4/15.
 */
public class Step {
    private String description;
    private ImageView imageView;

    public Step(String description, ImageView imageView){
        this.description = description;
        this.imageView = imageView;
    }

    public Step(){

    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setImageView(ImageView imageView){
        this.imageView = imageView;
    }

    public String getDescription(){
        return this.description;
    }

    public ImageView getImageView(){
        return this.imageView;
    }
}
