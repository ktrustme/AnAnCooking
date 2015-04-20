package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.model.RecipeCreateListHelper;
import com.anan.anancooking.model.Step;


public class InsertionStepDialog extends DialogFragment implements View.OnClickListener{
    private OnInsertionConfirmedListener mCallback;
    private int myPosition;

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
        Button saveBtn = (Button) view.findViewById(R.id.save_btn);

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

    @Override
    public void onClick(View view) {
        Step step = new Step();
        step.setDescription(((TextView) getView().findViewById(R.id.edit＿text_insert_description)).getText().toString());
        int position = Integer.parseInt(((TextView) getView().findViewById(R.id.text_view_insert_position)).getText().toString());
        System.out.println("onClick position = "+position);
        mCallback.insertStep(step,position);
        getDialog().dismiss();
    }

    public interface OnInsertionConfirmedListener {
        // TODO: Update argument type and name
        public void insertStep(Step step, int position);
    }

}
