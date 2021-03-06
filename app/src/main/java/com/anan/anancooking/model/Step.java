package com.anan.anancooking.model;
import android.widget.*;

import java.io.Serializable;

/**
 * Created by zihsiangsyu on 4/4/15.
 */
public class Step implements Serializable {
    private String recipeID;
    private int stepID;
    private String description;
    private byte[] image;
//    private ImageView imageView;
//
//
//    public Step(String description, ImageView imageView, int stepID){
//        this.stepID = stepID;
//        this.description = description;
//        this.imageView = imageView;
//    }


    public Step(String recipeID, int stepID, String des, byte[] image) {
        this.recipeID = recipeID;
        this.stepID = stepID;
        this.image = image;
        this.description = des;
    }

    public Step(){

    }

    public void setStepID(int stepID){
        this.stepID = stepID;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setBytes(byte[] image) {
        this.image = image;
    }

    public void setRecipeID(String id) {
        this.recipeID = id;
    }

//    public void setImageView(ImageView imageView){
//        this.imageView = imageView;
//    }
//
//    public String getDescription(){
//        return this.description;
//    }
//
//    public ImageView getImageView(){
//        return this.imageView;
//    }

    public String getDescription() {
        return this.description;
    }

    public byte[] getBytes() {
        return this.image;
    }

    public String getRecipeID() {
        return this.recipeID;
    }
    public int getStepID(){ return this.stepID;}

    @Override
    public String toString(){

        StringBuilder ret = new StringBuilder();
        ret.append(recipeID).append(",");
        ret.append(stepID).append(",");
        ret.append(description).append(",");
        ret.append(image);
        return ret.toString();

    }

    public static Step deserializer(String s){
        Step step = new Step();
        String[] temp = s.split(",");
        step.setRecipeID(temp[0]);
        step.setStepID(Integer.parseInt(temp[1]));
        step.setDescription(temp[2]);
        return step;
    }

}

