package com.anan.anancooking.client.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Extends SQLiteOpenHelper. Provide basic operations like create db, delete db, and the most important: get the wrritable or readable db.
 * Created by kuoxin on 3/30/15.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String BLOB_TYPE = " MEDIUMBLOB";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StorageSchema.DataEntry.TABLE_NAME + " (" +
                    StorageSchema.DataEntry._ID + " INTEGER PRIMARY KEY," +
                    StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID + TEXT_TYPE + COMMA_SEP +
                    StorageSchema.DataEntry.COLUMN_NAME_RECIPE_NAME + TEXT_TYPE + COMMA_SEP +
                    StorageSchema.DataEntry.COLUMN_NAME_TIME + TEXT_TYPE + COMMA_SEP +
                    StorageSchema.DataEntry.COLUMN_NAME_INGREDIENTS + TEXT_TYPE + COMMA_SEP +
                    StorageSchema.DataEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    StorageSchema.DataEntry.COLUMN_NAME_PREVIEW_IMAGE + BLOB_TYPE+
                    " )";

    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + StorageSchema.DataEntry.TABLE_NAME2 + " (" +
                    StorageSchema.DataEntry._ID + " INTEGER PRIMARY KEY," +
                    StorageSchema.DataEntry.COLUMN_NAME_RECIPE_ID + TEXT_TYPE + COMMA_SEP +
                    StorageSchema.DataEntry.COLUMN_NAME_STEP_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    StorageSchema.DataEntry.COLUMN_NAME_STEP_IMAGE + BLOB_TYPE+
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StorageSchema.DataEntry.TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + StorageSchema.DataEntry.TABLE_NAME2;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "LOCAL_RECIPE.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}