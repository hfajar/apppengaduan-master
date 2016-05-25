package com.affa.apppengaduan.apppengaduan;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

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
}
