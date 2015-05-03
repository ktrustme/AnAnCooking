package com.anan.anancooking.client.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.Step;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A wrapper class over DbHelper, for convenience.
 * Provide interface to insert data into the database, and get all history records from the database.
 * Created by kuoxin on 3/30/15.
 */
public class DbUtil {
    DbHelper dbHelper = null;
    private static final Logger logger = LoggerFactory.getLogger("My641log");

    public DbUtil(Context context) {
        this.dbHelper = new DbHelper(context);
        //context.get
    }

    public void deleteRecipe(String recipeId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Insert the new row, returning the primary key value of the new row
        db.delete(StorageSchema.DataEntry.TABLE_NAME, StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID + " = " + "'" + recipeId + "'", null);
        db.delete(StorageSchema.DataEntry.TABLE_NAME2, StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID + " = " + "'" + recipeId + "'", null);

    }

    public void PutRecipe(RecipeImplementation recipe) {
        logger.info("PutInfo(): save data into database.");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID, recipe.getRecipeID());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_RECIPE_NAME, recipe.getName());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_TIME, "" + recipe.getTime());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_INGREDIENTS, recipe.getIngredients());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_DESCRIPTION, recipe.getDescription());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_PREVIEW_IMAGE, recipe.getPreviewByteCode());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                StorageSchema.DataEntry.TABLE_NAME,
                "NOTHING_HERE",
                values);

        List<Step> steps = recipe.getSteps();

        for (Step step : steps) {
            System.out.println("Save steps");
            System.out.println("id is " + recipe.getRecipeID());
            values = new ContentValues();
            values.put(StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID, recipe.getRecipeID());
            values.put(StorageSchema.DataEntry.COLUMN_NAME_STEP_DESCRIPTION, step.getDescription());
            values.put(StorageSchema.DataEntry.COLUMN_NAME_STEP_IMAGE, step.getBytes());

            newRowId = db.insert(
                    StorageSchema.DataEntry.TABLE_NAME2,
                    "NOTHING_HERE",
                    values);
        }



    }


    public RecipeImplementation getRecipe(String recipeId) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        RecipeImplementation recipe = new RecipeImplementation();

        String[] projection = {
                StorageSchema.DataEntry._ID,
                StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID,
                StorageSchema.DataEntry.COLUMN_NAME_RECIPE_NAME,
                StorageSchema.DataEntry.COLUMN_NAME_TIME,
                StorageSchema.DataEntry.COLUMN_NAME_INGREDIENTS,
                StorageSchema.DataEntry.COLUMN_NAME_DESCRIPTION,
                StorageSchema.DataEntry.COLUMN_NAME_PREVIEW_IMAGE,
        };


        Cursor c = db.query(
                StorageSchema.DataEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID + "=?",                                // The columns for the WHERE clause
                recipeId.split(";"),                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        /*
        Cursor c =db.rawQuery("Select * from "+StorageSchema.DataEntry.TABLE_NAME+
                " where "+StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID + "="+recipeId,"".split(";"));
        */


        if (c.moveToNext()) {
            recipe.setRecipeID(recipeId);
            recipe.setName(c.getString(c.getColumnIndex(StorageSchema.DataEntry.COLUMN_NAME_RECIPE_NAME)));
            recipe.setIngredients(c.getString(c.getColumnIndex(StorageSchema.DataEntry.COLUMN_NAME_INGREDIENTS)));
            recipe.setDescription(c.getString(c.getColumnIndex(StorageSchema.DataEntry.COLUMN_NAME_DESCRIPTION)));
            recipe.setPreviewByteCode(c.getBlob(c.getColumnIndex(StorageSchema.DataEntry.COLUMN_NAME_PREVIEW_IMAGE)));
            recipe.setTime(Integer.valueOf(c.getString(c.getColumnIndex(StorageSchema.DataEntry.COLUMN_NAME_TIME))));
        }

        String[] projection2 = {
                StorageSchema.DataEntry._ID,
                StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID,
                StorageSchema.DataEntry.COLUMN_NAME_STEP_DESCRIPTION,
                StorageSchema.DataEntry.COLUMN_NAME_STEP_IMAGE,
        };

        ArrayList<Step> steps = new ArrayList<Step>();
        c = db.query(
                StorageSchema.DataEntry.TABLE_NAME2,  // The table to query
                projection2,                               // The columns to return
                StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID + "=?",                                // The columns for the WHERE clause
                recipeId.split(";"),                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        while (c.moveToNext()) {
            Step step = new Step();
            step.setDescription(c.getString(c.getColumnIndex(StorageSchema.DataEntry.COLUMN_NAME_STEP_DESCRIPTION)));
            step.setBytes(c.getBlob(c.getColumnIndex(StorageSchema.DataEntry.COLUMN_NAME_STEP_IMAGE)));
            steps.add(step);
        }
        recipe.setSteps(steps);
        System.out.println("Steps's length is "+recipe.getSteps().size());
        return recipe;
    }

    public Cursor getHistory() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        logger.info("getHistory(): fetching history records.");
        String[] projection = {
                StorageSchema.DataEntry._ID,
                StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID,
                StorageSchema.DataEntry.COLUMN_NAME_RECIPE_NAME,
                StorageSchema.DataEntry.COLUMN_NAME_TIME,
                StorageSchema.DataEntry.COLUMN_NAME_INGREDIENTS,
                StorageSchema.DataEntry.COLUMN_NAME_DESCRIPTION,
                StorageSchema.DataEntry.COLUMN_NAME_PREVIEW_IMAGE,
        };

        Cursor c = db.query(
                StorageSchema.DataEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        return c;

    }
}
