package com.gdsc.umbb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.name_std);

        // Vérifier si la carte SD est montée en lecture-écriture
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) MainActivity.this,
                        new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }
            try {
                InputStream inputStream = getResources().openRawResource(R.raw.recourss);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder output = new StringBuilder(); // Pour stocker la sortie modifiée

                String line;
                while ((line = reader.readLine()) != null) {
                    String modifiedLine = line;

                    // Effectuer les substitutions personnalisées ici
                    modifiedLine = modifiedLine.replace("@{name_std}", "Votre nom");
                    modifiedLine = modifiedLine.replace("@{prenom_std}", "Votre prénom");

                    output.append(modifiedLine).append("\n");
                }

                reader.close();

                String modifiedContent = output.toString();

                // Créer un nouveau fichier avec les substitutions
                String filePath = Environment.getExternalStorageDirectory().toString() + "/gdsc";
                File directory = new File(filePath);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                File outputFile = new File(directory, "recours_modifie.html");
                FileWriter writer = new FileWriter(outputFile);
                writer.write(modifiedContent);
                writer.close();

                // Le fichier avec les substitutions a été créé avec succès
                System.out.println("Le fichier modifié a été créé : " + outputFile.getAbsolutePath());

                text.setText(outputFile.getAbsolutePath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Si la carte SD n'est pas montée, afficher un message d'erreur
            Toast.makeText(MainActivity.this, "Impossible d'enregistrer l'image. Veuillez insérer une carte SD.", Toast.LENGTH_SHORT).show();
        }

/*

        try {
            InputStream inputStream = getResources().openRawResource(R.raw.recours_1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder output = new StringBuilder(); // Pour stocker la sortie modifiée

            String line;
            while ((line = reader.readLine()) != null) {
                String modifiedLine = line
                        .replace("@{name_std}", "Votre nom")
                        .replace("@{prenom_std}", "Votre prénom");
                // Ajouter d'autres substitutions ici selon vos besoins

                output.append(modifiedLine).append("\n");
            }

            reader.close();

            String modifiedContent = output.toString();
            System.out.println(modifiedContent);

            // Faites ce que vous souhaitez avec le contenu modifié (par exemple, écrire dans un nouveau fichier)
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
/*
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.recours_1);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String new_html = "";
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char character = line.charAt(i);
                    if (character == '@') {
                        int j = i + 1;
                        char character_ = line.charAt(j);
                        if (character_ == '{'){
                            String value = "";
                            while (character_ == '}'){
                                value = value + String.valueOf(character_);
                                j++;
                            }
                            text.setText(value);
                            System.out.println(value);
                        }

                    }else{
                        new_html = new_html + character;
                    }
                }
                text.setText(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        // Setting values in Preference:
        SharedPreferences.Editor editor = getSharedPreferences("USER", MODE_PRIVATE).edit();
        editor.putString("nom", s1);
        editor.putString("prenom", s2);
        //...

        editor.apply();


        // Retrieve data from preference:
        SharedPreferences prefs = getSharedPreferences("USER", MODE_PRIVATE);
        String nom = prefs.getString("nom", "");
        String prenom = prefs.getString("prenom", "");
        //...

*/

    }
}