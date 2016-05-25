package com.affa.apppengaduan.apppengaduan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Fernalia on 5/25/2016.
 */
public class PengaduanActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();



    }

    public void go_to_umum(View view){
        Intent i = new Intent(this, FasilitasUmum.class);
        startActivity(i);
    }

    public void go_to_sekolah(View view){
        Intent i = new Intent(this, FasilitasSekolah.class);
        startActivity(i);
    }

    public void go_to_pengajar(View view){
        Intent i = new Intent(this, TimPengajar.class);
        startActivity(i);
    }

    public void go_to_lain2(View view){
        Intent i = new Intent(this, LainLain.class);
        startActivity(i);
    }
}
