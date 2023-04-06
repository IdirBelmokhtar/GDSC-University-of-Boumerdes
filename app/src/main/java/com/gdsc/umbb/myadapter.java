package com.gdsc.umbb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {


    private ArrayList<String> itemdocs;

    public myadapter(ArrayList<String> itemdocs) {
        this.itemdocs = itemdocs;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdoc, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.namedoc.setText(itemdocs.get(position));
    }

    @Override
    public int getItemCount() {

        return itemdocs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namedoc;
        private ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namedoc = itemView.findViewById(R.id.name);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
