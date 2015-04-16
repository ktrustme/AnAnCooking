package com.anan.anancooking.client.ui.listeners;

import android.view.View;

import com.anan.anancooking.client.ui.LogActivity;

/**
 * Created by kuoxin on 4/15/15.
 */
public class AnAnLogButtonListener implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        LogActivity parentActivity = (LogActivity)v.getContext();
        parentActivity.showProgressCircle("Wait for a sec..");
    }
}
