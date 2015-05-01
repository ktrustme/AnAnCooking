package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.view.View;
import android.app.*;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ws.remote.AnAnNetworkProtocols;
import com.anan.anancooking.client.ws.remote.MySingletonRequestQueue;
import com.anan.anancooking.client.ws.remote.TestVolleyCallbackInterface;
import com.anan.anancooking.client.ws.remote.UploadRecipeRequest;
import com.anan.anancooking.model.RecipeCreateListHelper;
import com.anan.anancooking.model.Step;
import com.anan.anancooking.client.ui.viewadapters.*;
import com.android.volley.RequestQueue;

import org.json.JSONArray;

import java.util.ArrayList;


public class RecipeCreationActivity extends Activity
        implements AddStepDialog.OnAddStepConfirmedListener,
        UpdateStepDialog.OnUpdateConfirmedListener,
        InsertionStepDialog.OnInsertionConfirmedListener,
        TestVolleyCallbackInterface
                   {

    private final int SELECT_PHOTO = 999;
    private int stepCounter=1;
    private final static int MAXIMUM_STEP = 20;
    private ListView listView = null;
    CreateRecipeListViewAdapter adapter = null;
    ArrayList<Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecipeCreateListHelper rclh = new RecipeCreateListHelper();
        steps = rclh.getArrayList();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_recipe);

        this.adapter = new CreateRecipeListViewAdapter(steps, this);

        listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        //Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler(this));

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



    public void openAddDialog(View view){
        DialogFragment newFragment = AddStepDialog.newInstance();
        newFragment.show(getFragmentManager(), "dialog");


        /*
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
        */

    }

    public void chooseImage(Step step, int position){
        /*Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, IMAGE_PICKER_SELECT);
        */
        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivityForResult(intent, SELECT_PHOTO);

        return;
    }


    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_PICKER_SELECT  && resultCode == Activity.RESULT_OK) {
            RecipeCreationActivity activity = (RecipeCreationActivity)getActivity();
            Bitmap bitmap = getBitmapFromCameraData(data, activity);
            mSelectedImage.setImageBitmap(bitmap);
      }
    }*/

    public void openUpdateStepDialog(Step step, int position) {
        Bundle args = new Bundle();

        // pass the argument from the stored step list
        args.putString("edit_text_update_description", step.getDescription());
        args.putByteArray("image_view",step.getBytes());

        args.putString("text_view_update_step_position",position+"");
        UpdateStepDialog updateStepDialog = UpdateStepDialog.newInstance(step.getDescription(),position,step.getBytes());
        updateStepDialog.setArguments(args);
        updateStepDialog.show(getFragmentManager(), "update_dialog");
    }

    public void openInsertionStepDialog(Step step, int position){
        Bundle args = new Bundle();
        args.putString("text_view_insert_position", position+"" );
        InsertionStepDialog insertionStepDialog = InsertionStepDialog.newInstance(position);
        insertionStepDialog.setArguments(args);
        insertionStepDialog.show(getFragmentManager(), "update_dialog");
    }

    private void refreshListView() {
        listView.setAdapter(adapter);
    }


    //@Override
    public void addStep(Step step) {
        steps.add(step);
        refreshListView();
        listView.setSelection(listView.getAdapter().getCount() - 1);
        return;
    }


    public void updateStep(Step step, int position) {
        steps.set(position,step);
        refreshListView();
        return;
    }


    @Override
    public void insertStep(Step step, int position) {
        System.out.println("insertStep position = "+position);
        steps.add(position+1,step);
        refreshListView();
        return;
    }

    public void sendToServer(View view){
        RequestQueue queue = MySingletonRequestQueue.getInstance(this.getApplicationContext()).
                getRequestQueue();
        JSONArray jsArray = new JSONArray(convertList(steps));
        queue.add(new UploadRecipeRequest(AnAnNetworkProtocols.HOST_NAME,AnAnNetworkProtocols.PORT_NUM, jsArray.toString(),this));

    }

    public ArrayList<String> convertList(ArrayList<Step> steps){
        int n = steps.size();
        ArrayList<String> ret = new ArrayList<String>();
        for(int i=0;i<n;i++){
            ret.add(i,steps.get(i).toString());
        }
        return ret;
    }

    @Override
    public void setText(String str) {

    }
}
