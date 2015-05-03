package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;


import com.anan.anancooking.R;

import com.anan.anancooking.client.ui.viewadapters.UseRecipeListViewAdapter;

import com.anan.anancooking.model.Step;


import java.util.List;


public class UseRecipeActivity extends Activity implements SensorEventListener {
    ListView lv = null;
    ShareActionProvider myShareActionProvider = null;
    List<Step> steps = null;
    private SensorManager mSensorManager;
    private Sensor mLight;
    private TextView testSensorReadingText;
    private float lightSensorResult=-100;
    private float initiallightSensorResult=-100;
    private long previousDarkTime=0;
    private long previousPreviousDarkTime=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Bundle bn = getIntent().getExtras();
        this.steps = (List<Step>) bn.getSerializable("recipeSteps");
        setContentView(R.layout.activity_use_recipe);
        setListView();
        setSensor();

        //Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(this));
    }

    private void setSensor() {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        testSensorReadingText = (TextView) findViewById(R.id.test_sensor_reading);

    }

    private void setShareIntent(Intent shareIntent) {
        if (myShareActionProvider != null) {
            myShareActionProvider.setShareIntent(shareIntent);
        }
    }

    private void setListView() {

        this.lv = (ListView) findViewById(R.id.listView_steps);
        UseRecipeListViewAdapter adapter = new UseRecipeListViewAdapter(this, R.layout.list_item_single_step, steps);
        this.lv.setAdapter(adapter);
        this.lv.addFooterView(getLayoutInflater().inflate(R.layout.rating_button, null));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_use_recipe, menu);
        MenuItem item = menu.findItem(R.id.action_share);
        // Fetch and store ShareActionProvider

        myShareActionProvider = (ShareActionProvider) item.getActionProvider();
        // Return true to display menu

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Test Content Sharing From AnAnCooking");
        sendIntent.setType("*/*");
        setShareIntent(sendIntent);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, com.anan.anancooking.client.ui.SettingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);

            return true;
        } else if (id == R.id.action_share) {
            //setShare();
        }

        return super.onOptionsItemSelected(item);
    }

    public void moveUp(View v) {
        int first = lv.getFirstVisiblePosition();
        int last = lv.getLastVisiblePosition();
        lv.smoothScrollToPositionFromTop(first, 10);
    }

    public void moveDown(View v) {
        int first = lv.getFirstVisiblePosition();
        int last = lv.getLastVisiblePosition();
        if (first == last)
            lv.smoothScrollToPosition(first + 1);
        else if ((last - first) == 1)
            lv.smoothScrollToPositionFromTop(first + 1, -10);
        else
            lv.smoothScrollToPositionFromTop(first + 2, -10);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lightSensorResult = event.values[0];

        if (initiallightSensorResult<0)
            initiallightSensorResult=lightSensorResult;


        //if right now is dark
        if(lightSensorResult<5) {
            //if the previous time slot is light, a dark event is trigered
            if (this.lightSensorResult>5) {

                long currentDarkTime = System.currentTimeMillis();
                if(currentDarkTime-this.previousDarkTime>1000){
                    moveDown(testSensorReadingText);
                    this.previousDarkTime=currentDarkTime;
                }else
                {
                    this.previousDarkTime=currentDarkTime;
                    moveUp(testSensorReadingText);
                }

            }
        }
        this.lightSensorResult=lightSensorResult;
        testSensorReadingText.setText(""+System.currentTimeMillis());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}
