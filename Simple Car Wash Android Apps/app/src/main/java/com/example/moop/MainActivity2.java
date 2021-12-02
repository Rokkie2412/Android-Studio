package com.example.moop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    EditText tglpilihan, isitransaksi;
    Button transaksi, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.History);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(1,1);
                        return true;
                    case R.id.History:
                        return true;
                }
                return false;
            }
        });
        tglpilihan = findViewById(R.id.tanggalpilihan);
        tglpilihan.setEnabled(false);
        isitransaksi = findViewById(R.id.editTextTextMultiLine2);
        isitransaksi.setEnabled(false);
        transaksi = findViewById(R.id.caritransaksi);
        tanggal = findViewById(R.id.caritanggal);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);//membuat variable untuk kalendar
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //ketika tombol ditekan akan muncul pop up calendar dan mengisi edit text
                DatePickerDialog datePickerDialog = new DatePickerDialog( //untuk memunculkan pop up calendar
                        MainActivity2.this, new DatePickerDialog.OnDateSetListener() { //set listenner baru ketika tanggal di tekan
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1; //kenapa +1 karena secara default month itu 0 jadi perlu +1 untuk ke januari
                        String date = makeDate(day, month, year);
                        tglpilihan.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                daftarTransaksi();
            }
        });

    }
    private String makeDate(int day, int month, int year) { //mengatur tampilan format tanggalnya
        return month(month) + " " + day + ", " + year;
    }

    private String month(int month) { //konversi bentuk bulan ke dalam namanya
        if (month == 1) {
            return "Jan";
        }
        if (month == 2) {
            return "Feb";
        }
        if (month == 3) {
            return "Mar";
        }
        if (month == 4) {
            return "Apr";
        }
        if (month == 5) {
            return "May";
        }
        if (month == 6) {
            return "Jun";
        }
        if (month == 7) {
            return "Jul";
        }
        if (month == 8) {
            return "Aug";
        }
        if (month == 9) {
            return "Sep";
        }
        if (month == 10) {
            return "Oct";
        }
        if (month == 11) {
            return "Nov";
        }
        if (month == 12) {
            return "Des";
        }
        return "Jan";
    }

    public void daftarTransaksi() {
        File file = new File(getApplicationContext().getFilesDir(), tglpilihan.getText().toString() + ".txt");//ini untuk mencari file exist atau tidak dengan nama yang ditentukan
        if (tglpilihan.getText().toString().matches("")){
            Toast.makeText(this,"Masukan tanggal yang dicari",Toast.LENGTH_SHORT).show();
        }else {
            if (file.exists()){
                FileInputStream fis;

                try {
                    fis = openFileInput(tglpilihan.getText().toString()+".txt");
                    InputStreamReader isr = new InputStreamReader(fis); //memasukan inputan dari fis
                    BufferedReader br =  new BufferedReader(isr); //membaca isi file dari inputan isr
                    StringBuilder sb = new StringBuilder(); //untuk menulis string ke dalam txt
                    String text;
                    while ((text = br.readLine())!= null){ //membaca isi file per baris sampai isinya kosong
                        sb.append(text).append("\n");
                    }

                    isitransaksi.setText(sb.toString()); //memasukan text ke multliline
                } catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(this,"File tidak ada",Toast.LENGTH_SHORT).show();
                isitransaksi.setText("");

            }
        }
    }
}