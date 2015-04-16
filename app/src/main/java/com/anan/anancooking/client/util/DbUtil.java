package com.anan.anancooking.client.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;

import java.util.Calendar;


/**
 * A wrapper class over DbHelper, for convenience.
 * Provide interface to insert data into the database, and get all history records from the database.
 * Created by kuoxin on 3/30/15.
 */
public class DbUtil {
    DbHelper dbHelper = null;
    Record record = null;
    private static final Logger logger = LoggerFactory.getLogger("My641log");

    public DbUtil(Context context, Record record){
        this.dbHelper = new DbHelper(context);
        this.record = record;
        //context.get
    }

    public void PutInfo(){
        logger.info("PutInfo(): save data into database.");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        Calendar c = Calendar.getInstance();
        values.put(StorageSchema.DataEntry.SAVE_TIME,c.get(Calendar.MONTH)+"/"+c.get(Calendar.DATE)+"/"+c.get(Calendar.YEAR)+" "+c.get(Calendar.HOUR_OF_DAY)+":" + c.get(Calendar.MINUTE)+" ");
        values.put(StorageSchema.DataEntry.COLUMN_NAME_PURCHASE_PRICE, record.getPurchasePrice());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_DOWN_PAYMENT,record.getDownPayment());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_MORTAGE_TERM,record.getTermInYears());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_INTEREST_RATE,record.getInterestRate());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_FIRST_PAYMENT_DATE,record.getFirstPaymentDate());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_MONTHLY_PAYMENT,record.getMonthlyPayment());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_OVERALL_PAYMENT,record.getOverallPayment());
        values.put(StorageSchema.DataEntry.COLUMN_NAME_PAYOFF_DATE,record.getPayoffDate());
        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                StorageSchema.DataEntry.TABLE_NAME,
                "NOTHING_HERE",
                values);
    }

    public Cursor getHistory() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        logger.info("getHistory(): fetching history records.");
        String[] projection = {
                StorageSchema.DataEntry._ID,
                StorageSchema.DataEntry.SAVE_TIME,
                StorageSchema.DataEntry.COLUMN_NAME_PURCHASE_PRICE,
                StorageSchema.DataEntry.COLUMN_NAME_DOWN_PAYMENT,
                StorageSchema.DataEntry.COLUMN_NAME_MORTAGE_TERM,
                StorageSchema.DataEntry.COLUMN_NAME_INTEREST_RATE,
                StorageSchema.DataEntry.COLUMN_NAME_FIRST_PAYMENT_DATE,
                StorageSchema.DataEntry.COLUMN_NAME_MONTHLY_PAYMENT,
                StorageSchema.DataEntry.COLUMN_NAME_OVERALL_PAYMENT,
                StorageSchema.DataEntry.COLUMN_NAME_PAYOFF_DATE};


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
