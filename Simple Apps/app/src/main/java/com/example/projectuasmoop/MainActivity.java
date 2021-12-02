package com.example.projectuasmoop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
    Button btn;
    Spinner spinnerbangsal,spinnerpenyakit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.next);
        spinnerbangsal = findViewById(R.id.spinner);
        spinnerpenyakit = findViewById(R.id.spinner2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindah();
            }
        });
    }

    protected void pindah() {
        String bangsal = spinnerbangsal.getSelectedItem().toString();
        String sakit = spinnerpenyakit.getSelectedItem().toString();
        Intent intent = new Intent(getBaseContext(),MainActivity2.class);
        intent.putExtra("bangsal",bangsal);
        intent.putExtra("penyakit",sakit);
        startActivity(intent);

    }
}