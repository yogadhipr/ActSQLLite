package com.example.actsqllite.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actsqllite.R;
import com.example.actsqllite.database.Teman;

import java.util.ArrayList;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;

    public TemanAdapter(ArrayList<Teman> listData){this.listData = listData; }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm,tlp;

        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelp();

        holder.nama.setTextColor(Color.BLUE);
        holder.nama.setTextSize(20);
        holder.nama.setText(nm);
        holder.telp.setText(tlp);
    }

    @Override
    public int getItemCount() {
        return listData != null ? listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView card;
        private TextView nama,telp;
        public TemanViewHolder(View v) {
            super(v);
            card = v.findViewById(R.id.card);
            nama = v.findViewById(R.id.textNama);
            telp = v.findViewById(R.id.textTelp);
        }
    }
}
