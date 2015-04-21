package com.anan.anancooking.client.exception;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.anan.anancooking.client.ui.CrashPageActivity;
import com.anan.anancooking.client.ui.ExceptionDialog;

/**
 * Created by kuoxin on 4/20/15.
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    private final Activity myContext;
    private final String LINE_SEPARATOR = "\n";

    public MyUncaughtExceptionHandler(Activity context) {
        myContext = context;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        //(new ExceptionDialog()).show(myContext.getFragmentManager(),"hehehe");
        //new Toast(myContext).show();
        Intent crashedIntent = new Intent(myContext, CrashPageActivity.class);
        crashedIntent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        myContext.startActivity(crashedIntent);
        //android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}
