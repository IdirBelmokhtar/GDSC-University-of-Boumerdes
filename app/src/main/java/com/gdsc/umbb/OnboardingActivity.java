package com.gdsc.umbb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OnboardingActivity extends AppCompatActivity {

    private ImageView charco_1, charco_2, charco_3;
    private TextView title, description;
    private Button next, skip;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        getSupportActionBar().hide();

        this.charco_1 = findViewById(R.id.charco_1);
        this.charco_2 = findViewById(R.id.charco_2);
        this.charco_3 = findViewById(R.id.charco_3);

        this.title = findViewById(R.id.onboarding_title);
        this.description = findViewById(R.id.onboarding_description);

        this.next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animate = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.alpha);

                if (page == 0) {
                    charco_1.setVisibility(View.GONE);
                    charco_2.setVisibility(View.VISIBLE);
                    charco_3.setVisibility(View.GONE);
                    page = 1;

                    title.setText("How can we help you?");
                    description.setText("My form provide different DM forms for you!");
                    title.startAnimation(animate);
                    description.startAnimation(animate);
                }else if (page == 1){
                    charco_1.setVisibility(View.GONE);
                    charco_2.setVisibility(View.GONE);
                    charco_3.setVisibility(View.VISIBLE);
                    page = 2;

                    next.setText("Get Started");
                    skip.setVisibility(View.GONE);


                    title.setText("From students for students");
                    description.setText("This app is made by love and coffee from you univ students");
                    title.startAnimation(animate);
                    description.startAnimation(animate);
                }else if (page == 2){
                    SharedPreferences.Editor editor = getSharedPreferences("GET STARTED", MODE_PRIVATE).edit();
                    editor.putBoolean("start",true);
                    editor.apply();

                    startActivity(new Intent(OnboardingActivity.this, HomeActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }
        });

        this.skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("GET STARTED", MODE_PRIVATE).edit();
                editor.putBoolean("start",true);
                editor.apply();

                // Start HomeActivity
                startActivity(new Intent(OnboardingActivity.this, HomeActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }
}