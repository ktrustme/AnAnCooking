package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.exception.MyUncaughtExceptionHandler;
import com.anan.anancooking.model.RecipePreviewImplementation;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CreateRecipeBriefDescriptionActivity extends Activity implements SeekBar.OnSeekBarChangeListener{
    private final int SELECT_PHOTO = 4040;
    public static final String PASS_TO_NEXT_STEP = "pass rpi to next activity";

    private EditText t2=null;
    private SeekBar sb=null;
    private ImageView imageView;

    private String recipeName;
    private String ingredients;
    private int time;
    private String description;
    private Bitmap image;

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
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(this));

        Button saveBtn = (Button) findViewById(R.id.image_pick_btn_in_create_preview);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }

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
        t2 = (EditText) findViewById(R.id.edit_text_time);
        t2.setText("0");
        t2.setOnEditorActionListener(listener);
        imageView = (ImageView) findViewById(R.id.selected_image_view);

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

        RecipePreviewImplementation rpi = new RecipePreviewImplementation();


        // setting the content of rpi
        this.recipeName = ((EditText) findViewById(R.id.edit_text_recipe_name)).getText().toString();
        this.ingredients = ((EditText) findViewById(R.id.edit_text_ingredient)).getText().toString();
        this.time = Integer.parseInt(((EditText) findViewById(R.id.edit_text_time)).getText().toString());
        this.description = ((EditText) findViewById(R.id.edit_text_description)).getText().toString();
        // setting the imageView to Byte array
        this.imageView = (ImageView)findViewById(R.id.selected_image_view);
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bm = imageView.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageByteArray = stream.toByteArray();
        imageView.setDrawingCacheEnabled(false);

        rpi.setName(this.recipeName);
        rpi.setIngredients(this.ingredients);
        rpi.setTime(this.time);
        rpi.setPreviewByteCode(imageByteArray);
        rpi.setDescription(description);
        // send the creating rpi to next step and start step-adding activity
        intent.putExtra(this.PASS_TO_NEXT_STEP,rpi);
        startActivity(intent);
    }
}
