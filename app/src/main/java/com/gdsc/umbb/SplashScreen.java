package com.gdsc.umbb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    View greenView, yellowView, redView, blueView;
    View greenLogo, yellowLogo, redLogo, blueLogo;
    TextView gdsc, umbb, loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Hide Action Bar and Status Bar in splash screen.
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        startActivity(new Intent(SplashScreen.this, HomeActivity.class));
        finish();

        greenLogo = findViewById(R.id.splash_green_logo);
        yellowLogo = findViewById(R.id.splash_yellow_logo);
        redLogo = findViewById(R.id.splash_red_logo);
        blueLogo = findViewById(R.id.splash_blue_logo);

        gdsc = findViewById(R.id.gdsc_splash);
        umbb = findViewById(R.id.umbb_splash);
        loading = findViewById(R.id.loading_splash);

        greenLogo.setVisibility(View.INVISIBLE);
        yellowLogo.setVisibility(View.INVISIBLE);
        redLogo.setVisibility(View.INVISIBLE);
        blueLogo.setVisibility(View.INVISIBLE);
        gdsc.setVisibility(View.INVISIBLE);
        umbb.setVisibility(View.INVISIBLE);
        loading.setVisibility(View.INVISIBLE);


        // Start the first animation of splash screen
        firstAnimation();

        // Start the second animation of splash screen after 2 seconds.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                secondAnimation();

                // Start the third animation of splash screen after 4 seconds.
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        thirdAnimation();
                    }
                }, 2000);
            }
        }, 2000);


        // Start OnboardingActivity after 6 seconds.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = getSharedPreferences("GET STARTED", MODE_PRIVATE);
                boolean start = prefs.getBoolean("start", false);
                if (!start){
                    startActivity(new Intent(SplashScreen.this, OnboardingActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }else {
                    startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }

            }
        }, 6000);
    }

    private void thirdAnimation() {
        Animation animate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alpha);

        gdsc.startAnimation(animate);
        umbb.startAnimation(animate);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.alpha));

                loadingAnimation(1);
            }
        }, 1000);
    }

    private void loadingAnimation(int i) {
        if (i == 1){
            loading.setText("Loading.");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingAnimation(2);
                }
            }, 500);
        } else if (i == 2){
            loading.setText("Loading..");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingAnimation(3);
                }
            }, 500);
        }else if (i == 3){
            loading.setText("Loading...");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadingAnimation(1);
                }
            }, 500);
        }
    }

    private void secondAnimation() {

        greenAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                yellowAnimation();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        blueAnimation();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                redAnimation();
                            }
                        }, 250);
                    }
                }, 250);
            }
        }, 250);
    }

    private void redAnimation() {
        Animation animation1, animation2;
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alpha1);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alpha2);

        redLogo.startAnimation(animation1);
        postDelayed(redLogo, animation2, 1000);
    }

    private void blueAnimation() {
        Animation animation1, animation2;
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alpha1);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alpha2);

        blueLogo.startAnimation(animation1);
        postDelayed(blueLogo, animation2, 1000);
    }

    private void yellowAnimation() {
        Animation animation1, animation2;
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alpha1);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alpha2);

        yellowLogo.startAnimation(animation1);
        postDelayed(yellowLogo, animation2, 1000);
    }

    private void greenAnimation() {
        Animation animation1, animation2;
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alpha1);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.alpha2);

        greenLogo.startAnimation(animation1);
        postDelayed(greenLogo, animation2, 1000);
    }

    private void postDelayed(View view, Animation animation, int delayMillis) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.startAnimation(animation);
            }
        }, delayMillis);
    }

    private void firstAnimation() {
        Animation animation;

        greenView = findViewById(R.id.splash_green_color);
        yellowView = findViewById(R.id.splash_yellow_color);
        redView = findViewById(R.id.splash_red_color);
        blueView = findViewById(R.id.splash_blue_color);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.translate_right_to_top);
        greenView.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.translate_right_to_bottom);
        yellowView.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.translate_left_to_top);
        redView.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.translate_left_to_bottom);
        blueView.startAnimation(animation);
    }
}