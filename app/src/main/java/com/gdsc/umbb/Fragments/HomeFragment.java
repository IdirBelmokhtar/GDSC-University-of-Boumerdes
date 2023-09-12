package com.gdsc.umbb.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdsc.umbb.MainActivity;
import com.gdsc.umbb.R;
import com.gdsc.umbb.myadapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public static RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<String> itemdoc = new ArrayList<>();
        itemdoc.add("Faire un Recours");
        itemdoc.add("Demande de bloquer");
        itemdoc.add("Demande de changement de Specialite");
        itemdoc.add("Demande de changement d'université");
        itemdoc.add("Demande de changement de groupe");
        itemdoc.add("Demande Salle et materiel");

        recyclerView = view.findViewById(R.id.recycle);
        myadapter adapter = new myadapter(itemdoc, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        return view;
    }
}