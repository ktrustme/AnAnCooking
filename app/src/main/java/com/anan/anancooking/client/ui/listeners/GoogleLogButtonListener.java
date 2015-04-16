package com.anan.anancooking.client.ui.listeners;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;

import com.anan.anancooking.client.ui.LogActivity;

/**
 * Created by kuoxin on 4/15/15.
 */
public class GoogleLogButtonListener implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        LogActivity parentActivity = (LogActivity)v.getContext();
        parentActivity.showProgressCircle("Wait for a sec..");
    }
}
