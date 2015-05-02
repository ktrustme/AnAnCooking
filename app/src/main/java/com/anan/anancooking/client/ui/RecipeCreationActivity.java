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
import com.anan.anancooking.model.RecipeImplementation;
import com.anan.anancooking.model.RecipePreviewImplementation;
import com.anan.anancooking.model.Step;
import com.anan.anancooking.client.ui.viewadapters.*;
import com.android.volley.RequestQueue;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class RecipeCreationActivity extends Activity
        implements AddStepDialog.OnAddStepConfirmedListener,
        UpdateStepDialog.OnUpdateConfirmedListener,
        InsertionStepDialog.OnInsertionConfirmedListener,
        TestVolleyCallbackInterface,
        SaveToServerConfirmDialog.SendToServerConfirmedListener
{

    private RecipePreviewImplementation rpi=null;
    private ListView listView = null;
    CreateRecipeListViewAdapter adapter = null;
    ArrayList<Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecipeCreateListHelper rclh = new RecipeCreateListHelper();
        steps = rclh.getArrayList();
        super.onCreate(savedInstanceState);

        // catch the RecipePreviewImplementation from previous arcitivty

        Intent intent = getIntent();
        this.rpi = (RecipePreviewImplementation) intent.getSerializableExtra(CreateRecipeBriefDescriptionActivity.PASS_TO_NEXT_STEP);
        //System.out.println(rpi.getTime());


        // setting the list view
        setContentView(R.layout.activity_create_recipe);
        this.adapter = new CreateRecipeListViewAdapter(steps, this);
        listView = (ListView) findViewById(android.R.id.list);
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



    public void openAddDialog(View view){
        DialogFragment newFragment = AddStepDialog.newInstance();
        newFragment.show(getFragmentManager(), "dialog");
    }

 /*   public void chooseImage(Step step, int position){
        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivityForResult(intent, SELECT_PHOTO);

        return;
    }
*/

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
        //System.out.println("insertStep position = "+position);
        steps.add(position+1,step);
        refreshListView();
        return;
    }

    public void sendToServerDialog(View view){
        //System.out.println(steps);
        //Bundle args = new Bundle();
        SaveToServerConfirmDialog saveToServerConfirmDialog = SaveToServerConfirmDialog.newInstance();
        //saveToServerConfirmDialog.setArguments(args);
        saveToServerConfirmDialog.show(getFragmentManager(), "save_to_Server_dialog");
    }
/*
    public ArrayList<String> convertList(ArrayList<Step> steps){
        int n = steps.size();
        ArrayList<String> ret = new ArrayList<String>();
        for(int i=0;i<n;i++){
            steps.get(i).setStepID(i);
            ret.add(i,steps.get(i).toString());
        }
        return ret;
    }
*/
    @Override
    public void setText(String str) {
        // do nothing
    }

    @Override
    public void confirm() {
        //System.out.println(steps);

        RequestQueue queue = MySingletonRequestQueue.getInstance(this.getApplicationContext()).
                getRequestQueue();

        // setting the step array
        RecipeImplementation recipe = new RecipeImplementation();
        recipe.setName(rpi.getName());
        recipe.setIngredients(rpi.getIngredients());
        recipe.setTime(rpi.getTime());
        recipe.setDescription(rpi.getDescription());
        recipe.setPreviewByteCode(rpi.getPreviewByteCode());
        recipe.setSteps(steps);

        /*
        JSONObject recipeJson = new JSONObject();
        try {
            recipeJson.put("recipe", recipe);
            System.out.println("Json no problem to convert.");
        }catch(Exception e){
            e.toString();
        }*/

        JSONObject recipeJson = new JSONObject();
        Gson gson = new Gson();
        try {
            recipeJson = new JSONObject(gson.toJson(recipe).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // queue
        queue.add(new UploadRecipeRequest(AnAnNetworkProtocols.HOST_NAME,AnAnNetworkProtocols.PORT_NUM,this,recipeJson));
        steps.clear();
        refreshListView();

        // back to main page
        Intent intent = new Intent(this, MainPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }
}
