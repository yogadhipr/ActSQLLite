package com.example.actsqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.actsqllite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class edit_teman extends AppCompatActivity {

    private TextInputEditText tNama,tTelp;
    private Button saveBtn;
    String nm,tlp,id;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teman);

        tNama = findViewById(R.id.edNama);
        tTelp = findViewById(R.id.edTelp);
        saveBtn = findViewById(R.id.saveBtn);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nama");
        tlp = getIntent().getStringExtra("telp");


        setTitle("Edit Data");
        tNama.setText(nm);
        tTelp.setText(tlp);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tNama.getText().toString().equals("") || tTelp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data belum lengkap!",Toast.LENGTH_LONG).show();
                }else{
                    nm = tNama.getText().toString();
                    tlp = tTelp.getText().toString();
                    HashMap<String,String> val = new HashMap<>();
                    val.put("id",id);
                    val.put("nama",nm);
                    val.put("telp",tlp);
                    controller.UpdateData(val);
                    callHome();
                }
            }
        });
    }
    public void callHome(){
        Intent i = new Intent(edit_teman.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}