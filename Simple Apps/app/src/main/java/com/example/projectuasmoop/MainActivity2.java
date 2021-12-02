package com.example.projectuasmoop;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    DatabaseSql db;
    Button masukdata,mainmenu;
    EditText username;
    CheckBox kondisi1,kondisi2,kondisi3,kondisi4;
    String situasi1,situasi2,situasi3,situasi4;
    String asalBangsal,penyakit;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DatabaseSql(this);
        mainmenu = findViewById(R.id.mainmenu);
        username = findViewById(R.id.nama);
        kondisi1 = findViewById(R.id.check1);
        kondisi2 = findViewById(R.id.check2);
        kondisi3 = findViewById(R.id.check3);
        kondisi4 = findViewById(R.id.check4);
        masukdata = findViewById(R.id.inputdata);
        Intent intent = getIntent();
        asalBangsal = intent.getStringExtra("bangsal");
        penyakit = intent.getStringExtra("penyakit");
        if (asalBangsal.equals("Bangsal Anak")){
            if(penyakit.equals("Tipus")){
                kondisi2.setText("Tingkat leukosit normal");
                kondisi3.setText("Tidak diare");
                kondisi4.setText("Nafsu makan kembali");
            }
        }else if (asalBangsal.equals("Bangsal Dewasa")){
            if (penyakit.equals("Demam Berdarah")){
                kondisi1.setText("Temperatur kurang dari 36,5");
                kondisi2.setText("Tingkat hemoglobin diatas 30000");
                kondisi3.setText("Mata tidak cekung");
                kondisi4.setText("Aktif");
            }else if (penyakit.equals("Tipus")){
                kondisi2.setText("Tingkat leukosit normal");
                kondisi3.setText("Tidak diare");
                kondisi4.setText("Nafsu makan kembali");
            }
        }

        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        masukdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (asalBangsal.equals("Bangsal Dewasa"))
                    masukDataDewasa();
                else if (asalBangsal.equals("Bangsal Anak"))
                    masukDataAnak();

                username.setText("");
                kondisi1.setChecked(false);
                kondisi2.setChecked(false);
                kondisi3.setChecked(false);
                kondisi4.setChecked(false);
            }
        });
    }



    public void masukDataAnak(){ //data akan masuk ke dalam tabel bangsal anak
        checkboxcase();
        String strUsername = username.getText().toString();
        if (strUsername.equals("")){
            Toast.makeText(getApplicationContext(), "Masukan nama pasien", Toast.LENGTH_SHORT).show();
        }else {
            Boolean daftar = db.insertUser(strUsername,penyakit,situasi1,situasi2,situasi3,situasi4);
            if (daftar == true) {
                Toast.makeText(getApplicationContext(), "Data masuk ke bangsal anak", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getApplicationContext(), "Data tidak masuk", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void masukDataDewasa(){//data akan masuk ke dalam tabel bangsal dewasa
        checkboxcase();
        String strUsername = username.getText().toString();
        if (strUsername.equals("")){
            Toast.makeText(getApplicationContext(), "Masukan nama pasien", Toast.LENGTH_SHORT).show();
        }else {
            Boolean daftar = db.insertUserDewasa(strUsername,penyakit,situasi1,situasi2,situasi3,situasi4);
            if (daftar == true) {
                Toast.makeText(getApplicationContext(), "Data masuk ke bangsal dewasa", Toast.LENGTH_SHORT).show();
                username.setText("");
            }
            else {
                Toast.makeText(getApplicationContext(), "Data tidak masuk", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void checkboxcase(){

        if (kondisi1.isChecked())
            situasi1 = kondisi1.getText().toString();
        else
            situasi1 = "-";

        if (kondisi2.isChecked())
            situasi2 = kondisi2.getText().toString();
        else
            situasi2 = "-";

        if (kondisi3.isChecked())
            situasi3 = kondisi3.getText().toString();
        else
            situasi3 = "-";

        if (kondisi4.isChecked())
            situasi4 = kondisi4.getText().toString();
        else
            situasi4 = "-";

    }

}