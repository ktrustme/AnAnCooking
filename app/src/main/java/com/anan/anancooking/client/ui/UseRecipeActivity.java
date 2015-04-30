package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ListView;
import android.widget.ShareActionProvider;


import com.anan.anancooking.R;
import com.anan.anancooking.client.exception.MyUncaughtExceptionHandler;
import com.anan.anancooking.client.ui.viewadapters.CustomListViewAdapterUseRecipe;
import com.anan.anancooking.client.ui.viewadapters.UseRecipeListViewAdapter;
import com.anan.anancooking.model.Steps;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class UseRecipeActivity extends Activity {
    ListView lv = null;
    ShareActionProvider myShareActionProvider = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_recipe);
        setListView();
        //Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(this));
    }

    private void setShareIntent(Intent shareIntent) {
        if (myShareActionProvider != null) {
            myShareActionProvider.setShareIntent(shareIntent);
        }
    }
/*
    private void setShare() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Test Content Sharing From AnAnCooking");
        sendIntent.setType("");
        View view =null;
        //LinearLayout ll = (LinearLayout) getLayoutInflater().inflate(R.layout.linearlayout,null);
        //ll.setOrientation(LinearLayout.VERTICAL);
        //view = lv.getAdapter().getView(3,null,lv);
        view = findViewById(R.id.recipe_intro_activity);
        view.setDrawingCacheEnabled(true);
        Bitmap b = view.getDrawingCache();

        final File dir = new File(Environment.getExternalStorageDirectory(), "/some/location");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        final File img = new File(dir, "TestShareImage" + ".jpg");
        if (img.exists()) {
            img.delete();
        }

        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        b.compress(Bitmap.CompressFormat.JPEG, 100, outStream);

        try {
            outStream.flush();
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        sendIntent.putExtra(Intent.EXTRA_STREAM, "Test Content Sharing From AnAnCooking");

        startActivity(sendIntent);

        if (myShareActionProvider != null) {
            myShareActionProvider.setShareIntent(sendIntent);
        }


    }
    */

    private void setListView() {
        this.lv = (ListView) findViewById(R.id.listView_steps);
        UseRecipeListViewAdapter adapter = new UseRecipeListViewAdapter(this, R.layout.list_item_single_step, Steps.asList());
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
}
