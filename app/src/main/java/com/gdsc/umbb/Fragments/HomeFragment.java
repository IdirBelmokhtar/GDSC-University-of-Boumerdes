package com.gdsc.umbb.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdsc.umbb.R;
import com.gdsc.umbb.myadapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<String> itemdoc = new ArrayList<>();
        itemdoc.add("School certificate");
        itemdoc.add("letter of recommendation");
        itemdoc.add("report card");
        itemdoc.add("Diploma");
        itemdoc.add("Abundant certificate");
        itemdoc.add("cover letter");
        itemdoc.add("change group");
        itemdoc.add("change speciality");

        recyclerView = view.findViewById(R.id.recycle);
        myadapter adapter = new myadapter(itemdoc);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        return view;
    }
}