package com.gdsc.umbb.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gdsc.umbb.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InformationFragment extends Fragment {

    Spinner sp;
    ArrayAdapter<String> arrayAdapter;
    String[] filier = {"ISIL", "SI", "ST", "Computer Science"};
    EditText mat, firstname, lastname, college, fac, dep, email;
    Button save, cancel;

    String nom_, prenom_, faculte_, email_, matricule_, departement_, specialite_, annee_universitaire_;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        sp = view.findViewById(R.id.filed);

        mat = view.findViewById(R.id.mat);
        firstname = view.findViewById(R.id.firstname);
        lastname = view.findViewById(R.id.lastname);
        college = view.findViewById(R.id.college);
        fac = view.findViewById(R.id.fac);
        dep = view.findViewById(R.id.dep);
        email = view.findViewById(R.id.email);

        save = view.findViewById(R.id.save);
        cancel = view.findViewById(R.id.cancel);

        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, filier);
        sp.setAdapter(arrayAdapter);


        SharedPreferences prefs = getContext().getSharedPreferences("USER_INFO", MODE_PRIVATE);
        nom_ = prefs.getString("@{name}", "");
        prenom_ = prefs.getString("@{pren}", "");
        faculte_ = prefs.getString("@{fac}", "");
        email_ = prefs.getString("@{mail}", "");
        matricule_ = prefs.getString("@{mat}", "");
        departement_ = prefs.getString("@{dep}", "");
        specialite_ = prefs.getString("@{spec}", "");
        annee_universitaire_ = prefs.getString("@{anu}", "");

        lastname.setText(nom_);
        firstname.setText(prenom_);
        fac.setText(faculte_);
        email.setText(email_);
        mat.setText(matricule_);
        dep.setText(departement_);
        college.setText(annee_universitaire_);
        if (specialite_.equals("ISIL")) sp.setSelection(0);
        if (specialite_.equals("SI")) sp.setSelection(1);
        if (specialite_.equals("ST")) sp.setSelection(2);
        if (specialite_.equals("Computer Science")) sp.setSelection(3);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastname.getText().toString().equals("")
                        || firstname.getText().toString().equals("")
                        || fac.getText().toString().equals("")
                        || email.getText().toString().equals("")
                        || mat.getText().toString().equals("")
                        || dep.getText().toString().equals("")
                        || college.getText().toString().equals("")) {

                    Toast.makeText(getContext(), "Veuillez remplir tous les informations", Toast.LENGTH_LONG).show();

                } else {
                    SharedPreferences.Editor editor = getContext().getSharedPreferences("USER_INFO", MODE_PRIVATE).edit();
                    editor.putString("@{name}", lastname.getText().toString());
                    editor.putString("@{pren}", firstname.getText().toString());
                    editor.putString("@{fac}", fac.getText().toString());
                    editor.putString("@{mail}", email.getText().toString());
                    editor.putString("@{mat}", mat.getText().toString());
                    editor.putString("@{dep}", dep.getText().toString());
                    editor.putString("@{spec}", sp.getSelectedItem().toString());
                    editor.putString("@{anu}", college.getText().toString());
                    editor.apply();

                    Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.drawer_fragment_container, new HomeFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}