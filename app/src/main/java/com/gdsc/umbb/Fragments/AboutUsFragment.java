package com.gdsc.umbb.Fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdsc.umbb.R;

public class AboutUsFragment extends Fragment {
 private ImageView[] links;
 private TextView linkTree;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_about_us, container, false);
        links=new ImageView[4];
        links[0]=view.findViewById(R.id.insta);
        links[1]=view.findViewById(R.id.fb);
        links[2]=view.findViewById(R.id.discord);
        links[3]=view.findViewById(R.id.linkedin);
        linkTree=view.findViewById(R.id.linkTree);
        linkTree.setPaintFlags(linkTree.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        openLinks(links);
        return view;
    }
    private void openLinks(ImageView[] url){
        url[0].setOnClickListener(v->{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/gdscumbb/"));
            startActivity(browserIntent);
        });
        url[1].setOnClickListener(v->{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/gdscumbb?_rdc=1&_rdr"));
            startActivity(browserIntent);
        });
        url[2].setOnClickListener(v->{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.com/invite/zRRvbkGrdG"));
            startActivity(browserIntent);
        });
        url[3].setOnClickListener(v->{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/gdscumbb/"));
            startActivity(browserIntent);
        });
        linkTree.setOnClickListener(v->{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://linktr.ee/gdscumbb"));
            startActivity(browserIntent);
        });

    }
}