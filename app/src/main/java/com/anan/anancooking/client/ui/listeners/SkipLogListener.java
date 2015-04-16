package com.anan.anancooking.client.ui.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.anan.anancooking.client.ui.LogActivity;
import com.anan.anancooking.client.ui.MainPageActivity;

/**
 * Created by kuoxin on 4/15/15.
 */
public class SkipLogListener implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        Activity parentActivity = (Activity) v.getContext();
        Intent intent = new Intent(parentActivity, MainPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        parentActivity.startActivity(intent);
    }
}
