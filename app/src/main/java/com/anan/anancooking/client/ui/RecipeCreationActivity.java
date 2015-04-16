package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.app.*;

import com.anan.anancooking.R;
import com.anan.anancooking.model.Step;
import com.anan.anancooking.client.ui.viewadapters.*;

import java.util.ArrayList;


public class RecipeCreationActivity extends Activity {
    private int stepCounter=1;
    private final static int MAXIMUM_STEP = 20;
    //ArrayAdapter<String> adapter;
    ArrayList<Step> list = new ArrayList<Step>();
    ArrayList<String> stringList = new ArrayList<>();
    CreateRecipeListViewAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //adapter = new ArrayAdapter<>(this,
        //        android.R.layout.simple_list_item_1, stringList);

        setContentView(R.layout.activity_main);

        //instantiate custom adapter
        this.adapter = new CreateRecipeListViewAdapter(list, this);
        //ListViewAdapter adapter = new ListViewAdapter(list, this);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    public void onResume(){
        super.onResume();
    }



    public void addItems(View view){
        if(stepCounter<=MAXIMUM_STEP) {
            //ListViewAdapter adapter = new ListViewAdapter(list, this);
            ListView listView = (ListView) findViewById(android.R.id.list);
            Step step = new Step();
            adapter.getList().add(step);
            stepCounter++;
            listView.setAdapter(adapter);
        }else{
            StringBuilder sb = new StringBuilder("Over step limit ");
            sb.append(MAXIMUM_STEP).append(" already.");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error").setMessage(sb.toString()).show();
        }

    }
    /*
    public void chooseImage(View view){
        Intent intent = new Intent(getApplicationContext(), ImagePickerActivity.class);
        startActivity(intent);
        return;
    }
    */
    public void save(View view){

    }



}
