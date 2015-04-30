package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
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


public class InsertionStepDialog extends DialogFragment implements View.OnClickListener{
    private final int SELECT_PHOTO = 1099;
    private OnInsertionConfirmedListener mCallback;
    private int myPosition;
    private ImageView imageView;

    private RecipeCreateListHelper rclh = new RecipeCreateListHelper();

    public InsertionStepDialog(){

    }

    public static InsertionStepDialog newInstance(int position) {
        InsertionStepDialog frag = new InsertionStepDialog();
        Bundle args = new Bundle();
        String positionString = String.valueOf(position);
        args.putString("text_view_insert_position", positionString);

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
        String positionString = (getArguments().getString("text_view_insert_position"));
        int position = Integer.parseInt(positionString);
        getDialog().setTitle("Insert the step");
        View view = inflater.inflate(R.layout.fragment_insertion_step_dialog, container, false);
        Bundle args = getArguments();
        (view.findViewById(R.id.save_btn)).setOnClickListener(this);

        TextView insertPosition = (TextView) view.findViewById(R.id.text_view_insert_position);
        insertPosition.setText(position+"");
        imageView = (ImageView) view.findViewById(R.id.selected_image_view);

        Button saveBtn = (Button) view.findViewById(R.id.image_pick_btn_in_insert_step);
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

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnInsertionConfirmedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

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
    public void onClick(View view) {
        Step step = new Step();
        step.setDescription(((TextView) getView().findViewById(R.id.edit＿text_insert_description)).getText().toString());
        int position = Integer.parseInt(((TextView) getView().findViewById(R.id.text_view_insert_position)).getText().toString());

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
        step.setBytes(imageByteArray);

        //System.out.println("onClick position = "+position);
        mCallback.insertStep(step,position);
        getDialog().dismiss();
    }

    public interface OnInsertionConfirmedListener {
        // TODO: Update argument type and name
        public void insertStep(Step step, int position);
    }

}
