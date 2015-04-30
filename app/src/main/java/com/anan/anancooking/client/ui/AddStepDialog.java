package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.model.RecipeCreateListHelper;
import com.anan.anancooking.model.Step;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


public class AddStepDialog extends DialogFragment implements View.OnClickListener {
    private final int SELECT_PHOTO = 1010;
    private OnAddStepConfirmedListener mCallback;
    private int position = 0;
    ImageView imageView;

    private RecipeCreateListHelper rclh = new RecipeCreateListHelper();

    public AddStepDialog(){

    }

    public static AddStepDialog newInstance() {
        AddStepDialog frag = new AddStepDialog();
        Bundle args = new Bundle();
        frag.setArguments(args);

        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        getDialog().setTitle("Add new step");
        View view = inflater.inflate(R.layout.fragment_add_step_dialog, container, false);
        Bundle args = getArguments();
        (view.findViewById(R.id.save_btn)).setOnClickListener(this);
        imageView = (ImageView) view.findViewById(R.id.selected_image_view);

        Button saveBtn = (Button) view.findViewById(R.id.image_pick_btn_in_add_step);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
        return view;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == getActivity().RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imageView.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }

    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnAddStepConfirmedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    @Override
    public void onClick(View view) {
        RecipeCreateListHelper rclh = new RecipeCreateListHelper();
        Step step = new Step();



        ImageView view2 = (ImageView)getView().findViewById(R.id.selected_image_view);
        view2.setDrawingCacheEnabled(true);
        view2.buildDrawingCache();
        //System.out.println("in add step:  view2 = " + view2);
        Bitmap bm = view2.getDrawingCache();
        //System.out.println("in add step:  " + bm2);


        //view.setDrawingCacheEnabled(true);
        //view.buildDrawingCache();
        //Bitmap bm = view.getDrawingCache();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageByteArray = stream.toByteArray();
        view2.setDrawingCacheEnabled(false);

        // set Step variables.
        step.setDescription(((TextView) getView().findViewById(R.id.edittext_description)).getText().toString());
        step.setBytes(imageByteArray);
        mCallback.addStep(step);
        getDialog().dismiss();
    }



    public interface OnAddStepConfirmedListener {
        // TODO: Update argument type and name
        public void addStep(Step step);
    }
}
