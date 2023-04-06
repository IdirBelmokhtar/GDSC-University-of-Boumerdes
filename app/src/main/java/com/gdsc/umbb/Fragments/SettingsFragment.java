package com.gdsc.umbb.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gdsc.umbb.R;

public class SettingsFragment extends Fragment {

    private LinearLayout language, theme, reset_options;
    private ImageView language_arrow, theme_arrow, reset_options_arrow;
    private LinearLayout language_layout, theme_layout, reset_options_layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        language = view.findViewById(R.id.language);
        theme = view.findViewById(R.id.theme);
        reset_options = view.findViewById(R.id.reset_options);
        language_arrow = view.findViewById(R.id.language_arrow);
        theme_arrow = view.findViewById(R.id.theme_arrow);
        reset_options_arrow = view.findViewById(R.id.reset_options_arrow);
        language_layout = view.findViewById(R.id.language_layout);
        theme_layout = view.findViewById(R.id.theme_layout);
        reset_options_layout = view.findViewById(R.id.reset_options_layout);

        Animation rotate_1 = AnimationUtils.loadAnimation(getContext(),
                R.anim.rotate_right_to_bottom);

        Animation rotate_2 = AnimationUtils.loadAnimation(getContext(),
                R.anim.rotate_bottom_to_right);

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (language_layout.getVisibility() == View.VISIBLE){
                    language_layout.setVisibility(View.GONE);
                    language_arrow.startAnimation(rotate_2);
                }else {
                    language_layout.setVisibility(View.VISIBLE);
                    language_arrow.startAnimation(rotate_1);
                }
            }
        });

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (theme_layout.getVisibility() == View.VISIBLE){
                    theme_layout.setVisibility(View.GONE);
                    theme_arrow.startAnimation(rotate_2);
                }else {
                    theme_layout.setVisibility(View.VISIBLE);
                    theme_arrow.startAnimation(rotate_1);
                }
            }
        });

        reset_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reset_options_layout.getVisibility() == View.VISIBLE){
                    reset_options_layout.setVisibility(View.GONE);
                    reset_options_arrow.startAnimation(rotate_2);
                }else {
                    reset_options_layout.setVisibility(View.VISIBLE);
                    reset_options_arrow.startAnimation(rotate_1);
                }
            }
        });

        return view;
    }
}