package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Context;

import com.bignerdranch.android.criminalintent.database.KeyValueDB;

import java.security.Key;
import java.util.UUID;

/**
 * Created by dswal on 17/10/2016.
 */
public class TripSettingsFragment extends Fragment {

    private static final String ARG_TRIP_SETTINGS_ID = "trip_settings_id";

    private Trip mTrip;

    private EditText mNameField;
    private EditText mIdField;
    private EditText mGenderField;
    private EditText mEmailField;
    private EditText mCommentField;


    public static TripSettingsFragment newInstance(UUID tripId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRIP_SETTINGS_ID, tripId);
        TripSettingsFragment fragment = new TripSettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trip_settings, container, false);


//        http://stackoverflow.com/questions/8215308/using-context-in-a-fragment

        String name = KeyValueDB.getName(getContext());
        String id = KeyValueDB.getId(getContext());
        String email = KeyValueDB.getEmail(getContext());
        String gender = KeyValueDB.getGender(getContext());
        String comment = KeyValueDB.getComment(getContext());


        mNameField = (EditText) v.findViewById(R.id.name_input);
        mNameField.setText(name);
        mNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
               KeyValueDB.setName(getActivity(), s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }


        });

        mIdField = (EditText) v.findViewById(R.id.id_input);
        mIdField.setText(id);
        mIdField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                KeyValueDB.setId(getActivity(), s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }


        });

        mEmailField = (EditText) v.findViewById(R.id.email_input);
        mEmailField.setText(email);
        mEmailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                KeyValueDB.setEmail(getActivity(), s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }


        });

        mGenderField = (EditText) v.findViewById(R.id.gender_input);
        mGenderField.setText(gender);
        mGenderField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                KeyValueDB.setGender(getActivity(), s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }


        });

        mCommentField = (EditText) v.findViewById(R.id.comment_input);
        mCommentField.setText(comment);
        mCommentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                KeyValueDB.setComment(getActivity(), s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }


        });





        return v;
    }



}
