package com.gdsc.umbb;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.gdsc.umbb.Fragments.InformationFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {

    private ArrayList<String> itemdocs;
    private Context context;

    public myadapter(ArrayList<String> itemdocs, Context context) {
        this.itemdocs = itemdocs;
        this.context = context;
    }

    // les donnees similaire.
    private String nom = "@{name}", nom_ = "";
    private String prenom = "@{pren}", prenom_ = "";
    private String faculte = "@{fac}", faculte_ = "";
    private String email = "@{mail}", email_ = "";
    private String matricule = "@{mat}", matricule_ = "";
    private String departement = "@{dep}", departement_ = "";
    private String specialite = "@{spec}", specialite_ = "";
    private String annee_universitaire = "@{anu}", annee_universitaire_ = "";
    private String date = "@{dat}", date_ = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    private String nom_de_destinataire = "@{nom_dest}", nom_de_destinataire_ = "";

    // Les informations de plus pour:
    // Faire un Recours:
    private String copie_exmn_ou_test = "@{copie_exmn_ou_test}", copie_exmn_ou_test_ = "";
    private String modul = "@{modul}", modul_ = "";

    // Demande de bloquer:
    private String annee_universitaire_a_bloquer = "@{anBloc}", annee_universitaire_a_bloquer_ = "";
    private String difficultes_de_ = "@{diff}", difficultes_de__ = "";

    // Demande de changement de Specialite:
    private String specialite_destinataire = "@{specD}", specialite_destinataire_ = "";

    // Demande de changement d'université:
    private String raison = "@{raisons}", raison_ = "";
    private String annee_concernee = "@{annee_concernee}", annee_concernee_ = "";
    private String explication = "@{explication}", explication_ = "";
    private String dep_dest = "@{dep_dest}", dep_dest_ = "";

    // Demande de changement de groupe:
    private String groupe_destination = "@{grpD}", groupe_destination_ = "";
    private String groupe_Actuel = "@{grpA}", groupe_Actuel_ = "";

    // Demande Salle et materiel:
    private String SM_faculte = "@{fac}"; // meme
    private String SM_annee_universitaire = "@{anu}"; // meme
    private String SM_nom_de_club = "@{club_organisation}", SM_nom_de_club_ = "";
    private String SM_email_de_club = "@{email_club_organisation}", SM_email_de_club_ = "";
    private String SM_date_de_la_demande = "@{dat}"; // meme
    private String SM_nom_de_destinataire = "@{nom_dest}", SM_nom_de_destinataire_ = "";
    private String SM_nom_de_la_salle = "@{nom_salle}", SM_nom_de_la_salle_ = "";
    private String SM_lieu_de_la_salle = "@{lieu_salle}", SM_lieu_de_la_salle_ = "";
    private String SM_date_event = "@{date_event}", SM_date_event_ = "";
    private String SM_debut = "@{debut}", SM_debut_ = "";
    private String SM_fin = "@{fin}", SM_fin_ = "";
    private String SM_nature_event = "@{nature_event}", SM_nature_event_ = "";
    private String SM_details = "@{details}", SM_details_ = "";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdoc, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s = itemdocs.get(position);
        holder.namedoc.setText(s);

        holder.doclayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get info from SharedPreferences: and check if preferences is not null.
                SharedPreferences prefs = context.getSharedPreferences("USER", MODE_PRIVATE);
                if (prefs == null) {
                    ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.drawer_fragment_container, new InformationFragment()).addToBackStack(null).commit();
                    Toast.makeText(context, "Veuillez remplir vos informations", Toast.LENGTH_LONG).show();
                } else {
                    nom_ = "Belmokhtar";
                    prenom_ = "Idir";
                    faculte_ = "Sciences INIM UMBB";
                    email_ = "exemple@gmail.com";
                    matricule_ = "1X1X31045702";
                    departement_ = "Informatique";
                    specialite_ = "TI";
                    annee_universitaire_ = "2023/2024";
                    date_ = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                    nom_de_destinataire_ = "Chef Département";
                    /*
                    nom_ = prefs.getString("@{name}", "");
                    prenom_ = prefs.getString("@{pren}", "");
                    faculte_ = prefs.getString("@{fac}", "");
                    email_ = prefs.getString("@{mail}", "");
                    matricule_ = prefs.getString("@{mat}", "");
                    departement_ = prefs.getString("@{dep}", "");
                    specialite_ = prefs.getString("@{spec}", "");
                    annee_universitaire_ = prefs.getString("@{anu}", "");
                    date_ = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                    nom_de_destinataire_ = prefs.getString("@{nom_dest}", "");
                    */

                    if (s.equals("Faire un Recours")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View dialogView = ((Activity) context).getLayoutInflater().inflate(R.layout.dialog_recours, null);
                        builder.setView(dialogView);

                        // Create the AlertDialog object
                        AlertDialog dialog = builder.create();

                        // Set the window layout params
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        // Set the window background drawable
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        // Obtenez les références des vues dans la boîte de dialogue
                        EditText et1 = dialogView.findViewById(R.id.copie_exam_ou_test);
                        EditText et2 = dialogView.findViewById(R.id.modul_);

                        // Définir le bouton "Créer" pour enregistrer les valeurs et ouvrir une autre activité
                        dialogView.findViewById(R.id.buttonCreate_recours).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Lire les valeurs saisies
                                copie_exmn_ou_test_ = et1.getText().toString();
                                modul_ = et2.getText().toString();
                                faire_recours();
                            }
                        });

                        // Show the dialog
                        dialog.show();

                    } else if (s.equals("Demande de bloquer")) {
                        // Créez une boîte de dialogue personnalisée
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View dialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.dialog_demande_bloquer, null);
                        builder.setView(dialogView);

                        // Create the AlertDialog object
                        AlertDialog dialog = builder.create();

                        // Set the window layout params
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        // Set the window background drawable
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        // Obtenez les références des vues dans la boîte de dialogue
                        EditText et1 = dialogView.findViewById(R.id.annee_universitaire_a_bloquer_);
                        EditText et2 = dialogView.findViewById(R.id.difficultes_de__);

                        // Définir le bouton "Créer" pour enregistrer les valeurs et ouvrir une autre activité
                        dialogView.findViewById(R.id.button_bloquer).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Lire les valeurs saisies
                                annee_universitaire_a_bloquer_ = et1.getText().toString();
                                difficultes_de__ = et2.getText().toString();
                                demande_bloquer();
                            }
                        });

                        // Afficher la boîte de dialogue
                        dialog.show();

                    } else if (s.equals("Demande de changement de Specialite")) {
                        // Créez une boîte de dialogue personnalisée
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View dialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.dialog_demande_change_spec, null);
                        builder.setView(dialogView);

                        // Create the AlertDialog object
                        AlertDialog dialog = builder.create();

                        // Set the window layout params
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        // Set the window background drawable
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        // Obtenez les références des vues dans la boîte de dialogue
                        EditText et1 = dialogView.findViewById(R.id.specialite_destinataire_);

                        // Définir le bouton "Créer" pour enregistrer les valeurs et ouvrir une autre activité
                        dialogView.findViewById(R.id.button_change_spec).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Lire les valeurs saisies
                                specialite_destinataire_ = et1.getText().toString();
                                demande_change_spec();
                            }
                        });

                        // Afficher la boîte de dialogue
                        dialog.show();

                    } else if (s.equals("Demande de changement d'université")) {
                        // Créez une boîte de dialogue personnalisée
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View dialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.dialog_demande_change_univ, null);
                        builder.setView(dialogView);

                        // Create the AlertDialog object
                        AlertDialog dialog = builder.create();

                        // Set the window layout params
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        // Set the window background drawable
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        // Obtenez les références des vues dans la boîte de dialogue
                        EditText et1 = dialogView.findViewById(R.id.raison_);
                        EditText et2 = dialogView.findViewById(R.id.annee_concernee_);
                        EditText et3 = dialogView.findViewById(R.id.explication_);
                        EditText et4 = dialogView.findViewById(R.id.dep_dest_);

                        // Définir le bouton "Créer" pour enregistrer les valeurs et ouvrir une autre activité
                        dialogView.findViewById(R.id.button_change_univ).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Lire les valeurs saisies
                                raison_ = et1.getText().toString();
                                annee_concernee_ = et2.getText().toString();
                                explication_ = et3.getText().toString();
                                dep_dest_ = et4.getText().toString();
                                demande_change_univ();
                            }
                        });

                        // Afficher la boîte de dialogue
                        dialog.show();

                    } else if (s.equals("Demande de changement de groupe")) {
                        // Créez une boîte de dialogue personnalisée
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View dialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.dialog_demande_change_groupe, null);
                        builder.setView(dialogView);

                        // Create the AlertDialog object
                        AlertDialog dialog = builder.create();

                        // Set the window layout params
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        // Set the window background drawable
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        // Obtenez les références des vues dans la boîte de dialogue
                        EditText et1 = dialogView.findViewById(R.id.groupe_destination_);
                        EditText et2 = dialogView.findViewById(R.id.groupe_Actuel_);

                        // Définir le bouton "Créer" pour enregistrer les valeurs et ouvrir une autre activité
                        dialogView.findViewById(R.id.button_change_groupe).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Lire les valeurs saisies
                                groupe_destination_ = et1.getText().toString();
                                groupe_Actuel_ = et2.getText().toString();
                                demande_change_groupe();
                            }
                        });

                        // Afficher la boîte de dialogue
                        dialog.show();

                    } else if (s.equals("Demande Salle et materiel")) {
                        // Créez une boîte de dialogue personnalisée
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        View dialogView = ((Activity)context).getLayoutInflater().inflate(R.layout.dialog_demande_salle_materiel, null);
                        builder.setView(dialogView);

                        // Create the AlertDialog object
                        AlertDialog dialog = builder.create();

                        // Set the window layout params
                        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                        // Set the window background drawable
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        // Obtenez les références des vues dans la boîte de dialogue
                        EditText et1 = dialogView.findViewById(R.id.SM_nom_de_club_);
                        EditText et2 = dialogView.findViewById(R.id.SM_email_de_club_);
                        EditText et3 = dialogView.findViewById(R.id.SM_nom_de_destinataire_);
                        EditText et4 = dialogView.findViewById(R.id.SM_nom_de_la_salle_);
                        EditText et5 = dialogView.findViewById(R.id.SM_lieu_de_la_salle_);
                        EditText et6 = dialogView.findViewById(R.id.SM_date_event_);
                        EditText et7 = dialogView.findViewById(R.id.SM_debut_);
                        EditText et8 = dialogView.findViewById(R.id.SM_fin_);
                        EditText et9 = dialogView.findViewById(R.id.SM_nature_event_);
                        EditText et10 = dialogView.findViewById(R.id.SM_details_);

                        // Définir le bouton "Créer" pour enregistrer les valeurs et ouvrir une autre activité
                        dialogView.findViewById(R.id.button_salle_material).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Lire les valeurs saisies

                                SM_nom_de_club_ = et1.getText().toString();
                                SM_email_de_club_ = et2.getText().toString();
                                SM_nom_de_destinataire_ = et3.getText().toString();
                                SM_nom_de_la_salle_ = et4.getText().toString();
                                SM_lieu_de_la_salle_ = et5.getText().toString();
                                SM_date_event_ = et6.getText().toString();
                                SM_debut_ = et7.getText().toString();
                                SM_fin_ = et8.getText().toString();
                                SM_nature_event_ = et9.getText().toString();
                                SM_details_ = et10.getText().toString();
                                demande_salle_materiel();
                            }
                        });

                        // Afficher la boîte de dialogue
                        dialog.show();

                    }
                }
            }
        });

    }

    private void faire_recours() {
        if (permissionSTORAGE()) {
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.recourss);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder output = new StringBuilder(); // Pour stocker la sortie modifiée

                String line;
                while ((line = reader.readLine()) != null) {
                    String modifiedLine = line;

                    // Effectuer les substitutions personnalisées ici
                    // Part1
                    modifiedLine = modifiedLine.replace(nom, nom_);
                    modifiedLine = modifiedLine.replace(prenom, prenom_);
                    modifiedLine = modifiedLine.replace(faculte, faculte_);
                    modifiedLine = modifiedLine.replace(email, email_);
                    modifiedLine = modifiedLine.replace(matricule, matricule_);
                    modifiedLine = modifiedLine.replace(departement, departement_);
                    modifiedLine = modifiedLine.replace(specialite, specialite_);
                    modifiedLine = modifiedLine.replace(annee_universitaire, annee_universitaire_);
                    modifiedLine = modifiedLine.replace(date, date_);
                    modifiedLine = modifiedLine.replace(nom_de_destinataire, nom_de_destinataire_);

                    // Part2
                    modifiedLine = modifiedLine.replace(copie_exmn_ou_test, copie_exmn_ou_test_);
                    modifiedLine = modifiedLine.replace(modul, modul_);

                    output.append(modifiedLine).append("\n");
                }

                reader.close();

                String modifiedContent = output.toString();

                // Send modifiedContent to DemandeActivity
                Intent intent = new Intent(context, DemandeActivity.class);
                intent.putExtra("workTextHtml", modifiedContent);
                context.startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void demande_bloquer() {
        if (permissionSTORAGE()) {
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.demande_bloquer);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder output = new StringBuilder(); // Pour stocker la sortie modifiée

                String line;
                while ((line = reader.readLine()) != null) {
                    String modifiedLine = line;

                    // Effectuer les substitutions personnalisées ici
                    // Part1
                    modifiedLine = modifiedLine.replace(nom, nom_);
                    modifiedLine = modifiedLine.replace(prenom, prenom_);
                    modifiedLine = modifiedLine.replace(faculte, faculte_);
                    modifiedLine = modifiedLine.replace(email, email_);
                    modifiedLine = modifiedLine.replace(matricule, matricule_);
                    modifiedLine = modifiedLine.replace(departement, departement_);
                    modifiedLine = modifiedLine.replace(specialite, specialite_);
                    modifiedLine = modifiedLine.replace(annee_universitaire, annee_universitaire_);
                    modifiedLine = modifiedLine.replace(date, date_);
                    modifiedLine = modifiedLine.replace(nom_de_destinataire, nom_de_destinataire_);

                    // Part2
                    modifiedLine = modifiedLine.replace(annee_universitaire_a_bloquer, annee_universitaire_a_bloquer_);
                    modifiedLine = modifiedLine.replace(difficultes_de_, difficultes_de__);

                    output.append(modifiedLine).append("\n");
                }

                reader.close();

                String modifiedContent = output.toString();

                // Send modifiedContent to DemandeActivity
                Intent intent = new Intent(context, DemandeActivity.class);
                intent.putExtra("workTextHtml", modifiedContent);
                context.startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void demande_change_spec() {
        if (permissionSTORAGE()) {
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.demande_changement_de_specialite);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder output = new StringBuilder(); // Pour stocker la sortie modifiée

                String line;
                while ((line = reader.readLine()) != null) {
                    String modifiedLine = line;

                    // Effectuer les substitutions personnalisées ici
                    // Part1
                    modifiedLine = modifiedLine.replace(nom, nom_);
                    modifiedLine = modifiedLine.replace(prenom, prenom_);
                    modifiedLine = modifiedLine.replace(faculte, faculte_);
                    modifiedLine = modifiedLine.replace(email, email_);
                    modifiedLine = modifiedLine.replace(matricule, matricule_);
                    modifiedLine = modifiedLine.replace(departement, departement_);
                    modifiedLine = modifiedLine.replace(specialite, specialite_);
                    modifiedLine = modifiedLine.replace(annee_universitaire, annee_universitaire_);
                    modifiedLine = modifiedLine.replace(date, date_);
                    modifiedLine = modifiedLine.replace(nom_de_destinataire, nom_de_destinataire_);

                    // Part2
                    modifiedLine = modifiedLine.replace(specialite_destinataire, specialite_destinataire_);

                    output.append(modifiedLine).append("\n");
                }

                reader.close();

                String modifiedContent = output.toString();

                // Send modifiedContent to DemandeActivity
                Intent intent = new Intent(context, DemandeActivity.class);
                intent.putExtra("workTextHtml", modifiedContent);
                context.startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void demande_change_univ() {
        if (permissionSTORAGE()) {
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.demande_changement_duniversite);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder output = new StringBuilder(); // Pour stocker la sortie modifiée

                String line;
                while ((line = reader.readLine()) != null) {
                    String modifiedLine = line;

                    // Effectuer les substitutions personnalisées ici
                    // Part1
                    modifiedLine = modifiedLine.replace(nom, nom_);
                    modifiedLine = modifiedLine.replace(prenom, prenom_);
                    modifiedLine = modifiedLine.replace(faculte, faculte_);
                    modifiedLine = modifiedLine.replace(email, email_);
                    modifiedLine = modifiedLine.replace(matricule, matricule_);
                    modifiedLine = modifiedLine.replace(departement, departement_);
                    modifiedLine = modifiedLine.replace(specialite, specialite_);
                    modifiedLine = modifiedLine.replace(annee_universitaire, annee_universitaire_);
                    modifiedLine = modifiedLine.replace(date, date_);
                    modifiedLine = modifiedLine.replace(nom_de_destinataire, nom_de_destinataire_);

                    // Part2
                    modifiedLine = modifiedLine.replace(raison, raison_);
                    modifiedLine = modifiedLine.replace(annee_concernee, annee_concernee_);
                    modifiedLine = modifiedLine.replace(explication, explication_);
                    modifiedLine = modifiedLine.replace(dep_dest, dep_dest_);

                    output.append(modifiedLine).append("\n");
                }

                reader.close();

                String modifiedContent = output.toString();

                // Send modifiedContent to DemandeActivity
                Intent intent = new Intent(context, DemandeActivity.class);
                intent.putExtra("workTextHtml", modifiedContent);
                context.startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void demande_change_groupe() {
        if (permissionSTORAGE()) {
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.demande_changement_groupe);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder output = new StringBuilder(); // Pour stocker la sortie modifiée

                String line;
                while ((line = reader.readLine()) != null) {
                    String modifiedLine = line;

                    // Effectuer les substitutions personnalisées ici
                    // Part1
                    modifiedLine = modifiedLine.replace(nom, nom_);
                    modifiedLine = modifiedLine.replace(prenom, prenom_);
                    modifiedLine = modifiedLine.replace(faculte, faculte_);
                    modifiedLine = modifiedLine.replace(email, email_);
                    modifiedLine = modifiedLine.replace(matricule, matricule_);
                    modifiedLine = modifiedLine.replace(departement, departement_);
                    modifiedLine = modifiedLine.replace(specialite, specialite_);
                    modifiedLine = modifiedLine.replace(annee_universitaire, annee_universitaire_);
                    modifiedLine = modifiedLine.replace(date, date_);
                    modifiedLine = modifiedLine.replace(nom_de_destinataire, nom_de_destinataire_);

                    // Part2
                    modifiedLine = modifiedLine.replace(groupe_destination, groupe_destination_);
                    modifiedLine = modifiedLine.replace(groupe_Actuel, groupe_Actuel_);

                    output.append(modifiedLine).append("\n");
                }

                reader.close();

                String modifiedContent = output.toString();

                // Send modifiedContent to DemandeActivity
                Intent intent = new Intent(context, DemandeActivity.class);
                intent.putExtra("workTextHtml", modifiedContent);
                context.startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void demande_salle_materiel() {
        if (permissionSTORAGE()) {
            try {
                InputStream inputStream = context.getResources().openRawResource(R.raw.demande_salle_et_materiel);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder output = new StringBuilder(); // Pour stocker la sortie modifiée

                String line;
                while ((line = reader.readLine()) != null) {
                    String modifiedLine = line;

                    // Effectuer les substitutions personnalisées ici
                    modifiedLine = modifiedLine.replace(SM_faculte, faculte_); // meme
                    modifiedLine = modifiedLine.replace(SM_annee_universitaire, annee_universitaire_); // meme
                    modifiedLine = modifiedLine.replace(SM_nom_de_club, SM_nom_de_club_);
                    modifiedLine = modifiedLine.replace(SM_email_de_club, SM_email_de_club_);
                    modifiedLine = modifiedLine.replace(SM_date_de_la_demande, date_); // meme
                    modifiedLine = modifiedLine.replace(SM_nom_de_destinataire, SM_nom_de_destinataire_);
                    modifiedLine = modifiedLine.replace(SM_nom_de_la_salle, SM_nom_de_la_salle_);
                    modifiedLine = modifiedLine.replace(SM_lieu_de_la_salle, SM_lieu_de_la_salle_);
                    modifiedLine = modifiedLine.replace(SM_date_event, SM_date_event_);
                    modifiedLine = modifiedLine.replace(SM_debut, SM_debut_);
                    modifiedLine = modifiedLine.replace(SM_fin, SM_fin_);
                    modifiedLine = modifiedLine.replace(SM_nature_event, SM_nature_event_);
                    modifiedLine = modifiedLine.replace(SM_details, SM_details_);

                    output.append(modifiedLine).append("\n");
                }

                reader.close();

                String modifiedContent = output.toString();

                // Send modifiedContent to DemandeActivity
                Intent intent = new Intent(context, DemandeActivity.class);
                intent.putExtra("workTextHtml", modifiedContent);
                context.startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean permissionSTORAGE() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }
            return true;
        } else {
            // Si la carte SD n'est pas montée, afficher un message d'erreur
            Toast.makeText(context, "Impossible d'enregistrer l'image. Veuillez insérer une carte SD.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public int getItemCount() {

        return itemdocs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namedoc;
        private LinearLayout doclayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namedoc = itemView.findViewById(R.id.name);
            doclayout = itemView.findViewById(R.id.doclayout);
        }
    }
}
