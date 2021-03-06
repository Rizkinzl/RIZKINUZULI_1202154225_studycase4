package com.example.rzknzl.rizkinuzuli_1202154225_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Deklarasi variabel
    Button btnCariGambar;
    Button btnListMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCariGambar = findViewById(R.id.btnCariGambar);
        btnListMahasiswa = findViewById(R.id.btnListMahasiswa);

        //mengaktifkan klik tombol untuk berpindah kelas ke CariGambarActivity
        btnCariGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent apsA = new Intent(MainActivity.this, CariGambarActivity.class);
                startActivity(apsA);
            }
        });

        //mengaktifkan klik tombol untuk berpindah kelas ke ListMahasiswaActivity
        btnListMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent apsB = new Intent(MainActivity.this, ListMahasiswaActivity.class);
                startActivity(apsB);
            }
        });
    }
}
