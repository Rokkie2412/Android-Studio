package com.example.moop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Spinner ukuran,jeniscuci,cucitambahan;
    EditText tanggal,plat,harga,transaksi;
    Button cek,bayar;
    int add,basic;
    Calendar calendar = Calendar.getInstance(); //deklrasi variable untuk mendapatkan instance calendar
    String currdate = DateFormat.getDateInstance().format(calendar.getTime()); //dari instnace calendar akan mendapatkan tanggal pada hari ini


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView); //deklrasi variable untuk mencari id dari bottom navigation view
        bottomNavigationView.setSelectedItemId(R.id.Home); //mengatur kodingan java ini akan masuk pada state yang mana dengan id
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) { //untuk mengatur intent
                switch (item.getItemId()){
                    case R.id.History:
                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                        overridePendingTransition(1,1);
                        return true;
                    case R.id.Home:
                        return true;
                }
                return false;
            }
        });
        tanggal = findViewById(R.id.date);
        tanggal.setEnabled(false);
        tanggal.setText(currdate);

        plat = findViewById(R.id.Plat);
        harga = findViewById(R.id.harga);
        harga.setEnabled(false);
        transaksi = findViewById(R.id.editTextTextMultiLine);
        transaksi.setEnabled(false);

        ukuran = findViewById(R.id.ukuranmobil);
        jeniscuci = findViewById(R.id.jeniscuci);
        cucitambahan = findViewById(R.id.addservice);

        cek = findViewById(R.id.cek);
        bayar = findViewById(R.id.Bayar);

        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receipt();
            }
        });

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

    }
    public void daftar_harga(){ //untuk harga
        String selukuran = ukuran.getSelectedItem().toString(); //mencari item yang dipilih pada spinner
        String seljenis = jeniscuci.getSelectedItem().toString();
        String seladd = cucitambahan.getSelectedItem().toString();
        switch (selukuran) {
            case "Small":
                if (seljenis.equals("Standar Wash")) {
                    basic = 70000;
                } else if (seljenis.equals("Bubble Wash")) {
                    basic = 90000;
                }

                switch (seladd) {
                    case "None":
                        add = 0;
                        break;
                    case "Salon Mesin":
                        add = 20000;
                        break;
                    case "Salon Interior Mobil":
                        add = 35000;
                        break;
                    case "Salon Eskterior Mobil":
                        add = 65000;
                        break;
                    case "Salon Mesin + Interior Mobil":
                        add = 50000;
                        break;
                    case "Salon Mesin + Eksterior Mobil":
                        add = 80000;
                        break;
                    case "Salon Interior + Eksterior Mobil":
                        add = 100000;
                        break;
                    case "Lengkap":
                        add = 110000;
                        break;
                }

                break;
            case "Medium":
                if (seljenis.equals("Standar Wash")) {
                    basic = 90000;
                } else if (seljenis.equals("Bubble Wash")) {
                    basic = 105000;
                }

                switch (seladd) {
                    case "None":
                        add = 0;
                        break;
                    case "Salon Mesin":
                        add = 30000;
                        break;
                    case "Salon Interior Mobil":
                        add = 45000;
                        break;
                    case "Salon Eskterior Mobil":
                        add = 75000;
                        break;
                    case "Salon Mesin + Interior Mobil":
                        add = 70000;
                        break;
                    case "Salon Mesin + Eksterior Mobil":
                        add = 100000;
                        break;
                    case "Salon Interior + Eksterior Mobil":
                        add = 115000;
                        break;
                    case "Lengkap":
                        add = 135000;
                        break;
                }
                break;
            case "Large":
                if (seljenis.equals("Standar Wash")) {
                    basic = 10000;
                } else if (seljenis.equals("Bubble Wash")) {
                    basic = 115000;
                }

                switch (seladd) {
                    case "None":
                        add = 0;
                        break;
                    case "Salon Mesin":
                        add = 40000;
                        break;
                    case "Salon Interior Mobil":
                        add = 60000;
                        break;
                    case "Salon Eskterior Mobil":
                        add = 90000;
                        break;
                    case "Salon Mesin + Interior Mobil":
                        add = 95000;
                        break;
                    case "Salon Mesin + Eksterior Mobil":
                        add = 120000;
                        break;
                    case "Salon Interior + Eksterior Mobil":
                        add = 140000;
                        break;
                    case "Lengkap":
                        add = 170000;
                        break;
                }
                break;
        }
    }
    public void receipt(){ //print isi ke multiline
        daftar_harga();
        if ((plat.getText().toString().matches(""))||(plat.getText().length()<=3)){
            Toast.makeText(this,"Masukan nomor kendaraan",Toast.LENGTH_SHORT).show();
        }else{
            String conv = String.valueOf(add+basic);
            harga.setText(conv);
            String multiline = "\n" +
                    "Plat Nomor   : "+plat.getText().toString()+"\n" +
                    "Ukuran Mobil : "+ukuran.getSelectedItem().toString()+"\n" +
                    "Jenis Cuci   : "+jeniscuci.getSelectedItem().toString()+"\n" +
                    "Tambahan     : "+cucitambahan.getSelectedItem().toString()+"\n" +
                    "Total harga  : "+conv+"\n" +
                    "----------------------------------------\n";
            transaksi.setText(multiline);
        }
    }
    public void save(){ //dari multi line isi akan di save ke file txt ke dalam directory android
        if (transaksi.getText().toString().matches("")){
            Toast.makeText(this,"Periksa harga terlebih dahulu",Toast.LENGTH_SHORT).show();
        }else{
            String text = transaksi.getText().toString(); //memasukan isi multiline text ke string
            FileOutputStream fos; //dekrlasi varibale untuk membuat file
            try {
                fos = openFileOutput(tanggal.getText().toString()+".txt",MODE_APPEND); //membuat file dengan nama yang ditentukan dengan mode append
                fos.write(text.getBytes()); //menulis isi string yaang berisi multiline text ke dalam file
                transaksi.setText("");
                Toast.makeText(this,"Pembayaran sukses",Toast.LENGTH_SHORT).show();
            } catch (IOException e){ //membuang pengecualian untuk memberikan file dan nomor baris
                e.printStackTrace();
            }
        }
    }

}