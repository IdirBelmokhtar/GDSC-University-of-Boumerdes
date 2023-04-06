package com.gdsc.umbb.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.gdsc.umbb.R;

public class InformationFragment extends Fragment {

    Spinner sp;
    ArrayAdapter<String> arrayAdapter;
    String[] filier = {"ISIL", "SI", "ST", "Computer Science"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        sp = view.findViewById(R.id.filed);

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, filier);
        sp.setAdapter(arrayAdapter);

        return view;
    }
}