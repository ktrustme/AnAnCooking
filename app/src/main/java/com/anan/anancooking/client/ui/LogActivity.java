package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.exception.MyUncaughtExceptionHandler;
import com.anan.anancooking.client.ui.listeners.AnAnLogButtonListener;
import com.anan.anancooking.client.ui.listeners.FacebookLogButtonListener;
import com.anan.anancooking.client.ui.listeners.GoogleLogButtonListener;
import com.anan.anancooking.client.ui.listeners.SkipLogListener;


public class LogActivity extends Activity {
    ProgressDialog progress = null;
    Button googleLogButton = null;
    Button facebookLogButton = null;
    Button ananLogButton = null;
    TextView skipText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        setStatusBar();
        setProgressCircle();
        setButton();
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(this));
    }

    /**
     * Set the StatusBar visibility
     */
    private void setStatusBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

    }

    /**
     * Set the progress circle
     */
    private void setProgressCircle() {
        this.progress = new ProgressDialog(this);
        //this.progress.getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
    }

    /**
     * Set the button component.
     */
    private void setButton() {
        googleLogButton = (Button) findViewById(R.id.button_log_google);
        googleLogButton.setOnClickListener(new GoogleLogButtonListener());

        facebookLogButton = (Button) findViewById(R.id.button_log_facebook);
        facebookLogButton.setOnClickListener(new FacebookLogButtonListener());

        ananLogButton = (Button) findViewById(R.id.button_log_anan);
        ananLogButton.setOnClickListener(new AnAnLogButtonListener());

        skipText = (TextView) findViewById(R.id.text_skip);
        skipText.setOnClickListener(new SkipLogListener());
    }

    /**
     * We don't have option menu in this page.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * And we dont' have option menu item also...
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Display a progress circle with given message
     * @param message
     */
    public void showProgressCircle(String message){
        this.progress.setMessage(message);
        this.progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.progress.setIndeterminate(true);
        this.progress.show();
    }
}
