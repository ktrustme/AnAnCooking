package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anan.anancooking.R;
import com.anan.anancooking.model.RecipeCreateListHelper;
import com.anan.anancooking.model.Step;


public class AddStepDialog extends DialogFragment implements View.OnClickListener {
    private OnAddStepConfirmedListener mCallback;
    private int position = 0;

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

        EditText description = (EditText) view.findViewById(R.id.edittext_description);
        Button saveBtn = (Button) view.findViewById(R.id.save_btn);

        return view;
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
        step.setDescription(((TextView) getView().findViewById(R.id.edittext_description)).getText().toString());
        mCallback.addStep(step);
        getDialog().dismiss();
    }

    public interface OnAddStepConfirmedListener {
        // TODO: Update argument type and name
        public void addStep(Step step);
    }
}
