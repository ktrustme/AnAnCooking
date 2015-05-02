package com.anan.anancooking.client.ui.viewadapters;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.anan.anancooking.R;
import com.anan.anancooking.client.ui.AddStepDialog;
import com.anan.anancooking.model.RecipeCreateListHelper;
import com.anan.anancooking.model.Step;


public class SaveToServerConfirmDialog extends DialogFragment implements View.OnClickListener {
    private SendToServerConfirmedListener mCallback;
    private boolean confirmed = false;
    private RecipeCreateListHelper rclh = new RecipeCreateListHelper();

    public SaveToServerConfirmDialog(){

    }

    public static SaveToServerConfirmDialog newInstance() {
        SaveToServerConfirmDialog frag = new SaveToServerConfirmDialog();
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



        getDialog().setTitle("Confirm finish and save?");
        View view = inflater.inflate(R.layout.fragment_save_to_server_confirm_dialog, container, false);
        Bundle args = getArguments();
        Button yesBtn = (Button) view.findViewById(R.id.confrim_yes_btn);
        Button noBtn = (Button) view.findViewById(R.id.confrim_no_btn);

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.confirm();
                getDialog().dismiss();
            }
        });


        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
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
            mCallback = (SendToServerConfirmedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    @Override
    public void onClick(View v) {

    }

    public interface SendToServerConfirmedListener {
        // TODO: Update argument type and name
        public void confirm();
    }
}
