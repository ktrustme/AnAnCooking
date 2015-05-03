package com.anan.anancooking.client.util;

import android.provider.BaseColumns;

/**
 * Created by kuoxin on 3/30/15.
 */


public final class StorageSchema {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public StorageSchema() {}
    /* Inner class that defines the table contents */
    public static abstract class DataEntry implements BaseColumns {
        public static final String TABLE_NAME = "recipe_preview";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        //public static final String SAVE_TIME = "save_time";
        public static final String COLUMN_NAME_RECIPE_ID = "recipe_id";
        public static final String COLUMN_NAME_RECIPE_NAME = "recipe_name";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_INGREDIENTS = "ingredients";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PREVIEW_IMAGE = "preview_image";

        public static final String TABLE_NAME2 = "steps";
        //public static final String COLUMN_NAME_RECIPE_ID = "recipe_id";
        public static final String COLUMN_NAME_STEP_DESCRIPTION = "step_description";
        public static final String COLUMN_NAME_STEP_IMAGE = "step_image";


        public static final String COLUMN_NAME_INTEREST_RATE = "interest_rate";
        public static final String COLUMN_NAME_FIRST_PAYMENT_DATE = "first_payment_date";
        public static final String COLUMN_NAME_MONTHLY_PAYMENT = "monthly_payment";
        public static final String COLUMN_NAME_OVERALL_PAYMENT = "overall_payment";
        public static final String COLUMN_NAME_PAYOFF_DATE = "payoff_date";


    }
}