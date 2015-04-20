package com.anan.anancooking.client.ui;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.anan.anancooking.R;

/**
 * Created by kuoxin on 4/20/15.
 */

public class ExceptionDialog extends DialogFragment {

    public static ExceptionDialog newInstance() {
        ExceptionDialog fragment = new ExceptionDialog();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ExceptionDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().setTitle("Unexpected Error Occurred...");
        View v = null;

        v = inflater.inflate(R.layout.fragment_exception_dialog, container);
        //(v.findViewById(R.id.confirm_add)).setOnClickListener(this);

        return v;

    }

}
