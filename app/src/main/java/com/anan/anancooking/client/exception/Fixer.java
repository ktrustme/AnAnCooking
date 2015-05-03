/*------------------------------
 This file: Fix5.java
 Programmer: Xin Kuo
 Andrew ID: xkuo
 Course/Section: 18641
 Assignment: Homework 4
 Description:	
 Last Modified: 02/7/2015
 Known Bugs: Currently none
 Compiler: Java SE 7, javac
 ------------------------------*/
package com.anan.anancooking.client.exception;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.anan.anancooking.R;

import java.util.logging.Logger;

/**
 * @author kuoxin
 *         The methods in this class are responsible for all the fixing stuff.
 *         But current what those method do is just record some meesage into
 *         the log file.
 */
public class Fixer {
    private Activity parentActivity;

    public Fixer(Activity parentActivity) {
        this.parentActivity = parentActivity;
    }

    public void fix1(MyExceptionEnum exenum) {
        showAlertDialog(exenum.getMessage());
    }

    public void fix2(MyExceptionEnum exenum) {
        showAlertDialog(exenum.getMessage());
    }

    public void fix3(MyExceptionEnum exenum) {
        showAlertDialog(exenum.getMessage());
    }


    private void showAlertDialog(String message) {
        LayoutInflater inflater = this.parentActivity.getLayoutInflater();
        String title = "Oops...";
        AlertDialog.Builder builder = new AlertDialog.Builder(parentActivity);
        builder.setTitle(title);
        View exceptionDialogLayout = inflater.inflate(R.layout.dialog_exception, null);
        builder.setView(exceptionDialogLayout);
        ((TextView) exceptionDialogLayout.findViewById(R.id.exception_dialog_textview)).setText(message);
        AlertDialog dialog = builder.create();
        exceptionDialogLayout.findViewById(R.id.button_exception_ok).setOnClickListener(new OkButtonListener(dialog));
        dialog.show();

    }

    private class OkButtonListener implements View.OnClickListener {
        AlertDialog dialog = null;

        public OkButtonListener(AlertDialog dialog) {
            this.dialog = dialog;
        }

        @Override
        public void onClick(View view) {
            dialog.cancel();
        }
    }
}
