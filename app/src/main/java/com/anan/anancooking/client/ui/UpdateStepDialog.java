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


public class UpdateStepDialog extends DialogFragment implements View.OnClickListener{
    private OnUpdateConfirmedListener mCallback;
    private final int SELECT_PHOTO = 4040;
    private ImageView imageView;

    private RecipeCreateListHelper rclh = new RecipeCreateListHelper();

    public UpdateStepDialog(){

    }

    public static UpdateStepDialog newInstance(String s, int position, byte[] imageByteArray) {
        UpdateStepDialog frag = new UpdateStepDialog();
        Bundle args = new Bundle();
        args.putString("edit_text_update_description", s);
        args.putString("text_view_update_step_position",position+"");
        args.putByteArray("image_view",imageByteArray);
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
        String previous = getArguments().getString("edit_text_update_description");
        String positionString = getArguments().getString("text_view_update_step_position");
        byte[] imageByteArray = getArguments().getByteArray("image_view");

        getDialog().setTitle("update the step");
        View view = inflater.inflate(R.layout.fragment_update_step_dialog, container, false);
        Bundle args = getArguments();
        (view.findViewById(R.id.save_btn)).setOnClickListener(this);


        EditText description = (EditText) view.findViewById(R.id.edit_text_update_description);
        TextView stepPosition = (TextView) view.findViewById(R.id.text_view_update_step_position);
        description.setText(previous);  // set the hint
        stepPosition.setText(positionString);

        imageView = (ImageView) view.findViewById(R.id.selected_image_view);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
        imageView.setImageBitmap(bitmap);

        Button saveBtn = (Button) view.findViewById(R.id.image_pick_btn_in_update_step);
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
            mCallback = (OnUpdateConfirmedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    @Override
    public void onClick(View view) {
        RecipeCreateListHelper rclh = new RecipeCreateListHelper();
        Step step = new Step();
        step.setDescription(((TextView) getView().findViewById(R.id.edit_text_update_description)).getText().toString());
        int position = Integer.parseInt(((TextView) getView().findViewById(R.id.text_view_update_step_position)).getText().toString());

        ImageView view2 = (ImageView)getView().findViewById(R.id.selected_image_view);
        view2.setDrawingCacheEnabled(true);
        view2.buildDrawingCache();
        Bitmap bm = view2.getDrawingCache();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageByteArray = stream.toByteArray();
        view2.setDrawingCacheEnabled(false);
        step.setBytes(imageByteArray);


        mCallback.updateStep(step, position);
        getDialog().dismiss();
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

    public interface OnUpdateConfirmedListener {
        // TODO: Update argument type and name
        public void updateStep(Step step, int position);
    }
}
