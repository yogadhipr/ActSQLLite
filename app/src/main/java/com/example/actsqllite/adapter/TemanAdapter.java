package com.example.actsqllite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actsqllite.MainActivity;
import com.example.actsqllite.R;
import com.example.actsqllite.database.DBController;
import com.example.actsqllite.database.Teman;
import com.example.actsqllite.edit_teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context control;

    public TemanAdapter(ArrayList<Teman> listData){this.listData = listData; }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        control = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm,tlp, id;

        id = listData.get(position).getId();
        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelp();
        DBController db = new DBController(control);

        holder.nama.setTextColor(Color.BLUE);
        holder.nama.setTextSize(20);
        holder.nama.setText(nm);
        holder.telp.setText(tlp);

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu pop = new PopupMenu(control,holder.card);
                pop.inflate(R.menu.menu);
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnEdit:
                                Intent i = new Intent(control, edit_teman.class);
                                i.putExtra("id",id);
                                i.putExtra("nama",nm);
                                i.putExtra("telp",tlp);
                                control.startActivity(i);
                                break;

                            case R.id.mnDelete:
                                HashMap<String,String> val = new HashMap<>();
                                val.put("id",id);
                                db.DeleteData(val);;
                                Intent in = new Intent(control, MainActivity.class);
                                control.startActivity(in);
                                break;
                        }
                        return true;
                    }
                });
                pop.show();
                return true;
            }
        });

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
