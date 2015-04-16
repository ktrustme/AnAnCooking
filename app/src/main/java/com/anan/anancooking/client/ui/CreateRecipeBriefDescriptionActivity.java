package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.*;

public class CreateRecipeBriefDescriptionActivity extends Activity implements SeekBar.OnSeekBarChangeListener{

    private EditText t2=null;
    private SeekBar sb=null;
    private TextView.OnEditorActionListener listener = new EditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE||actionId ==EditorInfo.IME_ACTION_NEXT) {

                // the user is done typing.
                double tmp = 0.0;
                try {
                    tmp = Double.valueOf(t2.getText().toString());
                }catch(NumberFormatException e){
                    sb.setProgress(0);
                    t2.setText("0");
                }
                if(tmp >= 0 && tmp <= 100)
                    sb.setProgress((int)Math.round(tmp/100 * (double)sb.getMax()));
                else if(tmp < 0) {
                    sb.setProgress(0);
                    t2.setText("0");
                }
                else {
                    sb.setProgress(sb.getMax());
                    t2.setText("100");
                }
                return true; // consume.

            }
            return false; // pass on to other listeners.
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe_brief_description);
        setSeekBar();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_recipe_brief_description, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, com.anan.anancooking.client.ui.SettingActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setSeekBar(){
        sb = (SeekBar) findViewById(R.id.seekBar1);
        sb.setOnSeekBarChangeListener(this);
        t2 = (EditText) findViewById(R.id.edit_message2);
        t2.setText("0");
        t2.setOnEditorActionListener(listener);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        t2.setText(String.format("%.0f",100*((double)sb.getProgress())/((double)sb.getMax())));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void addSteps(View view){
        Intent intent = new Intent(this, RecipeCreationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }
}
