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
        public static final String TABLE_NAME = "HISTORY_RECORDS";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String SAVE_TIME = "save_time";
        public static final String COLUMN_NAME_PURCHASE_PRICE = "purchase_price";
        public static final String COLUMN_NAME_DOWN_PAYMENT = "down_payment";
        public static final String COLUMN_NAME_MORTAGE_TERM = "mortage_term";
        public static final String COLUMN_NAME_INTEREST_RATE = "interest_rate";
        public static final String COLUMN_NAME_FIRST_PAYMENT_DATE = "first_payment_date";
        public static final String COLUMN_NAME_MONTHLY_PAYMENT = "monthly_payment";
        public static final String COLUMN_NAME_OVERALL_PAYMENT = "overall_payment";
        public static final String COLUMN_NAME_PAYOFF_DATE = "payoff_date";


    }
}