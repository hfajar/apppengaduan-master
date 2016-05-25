package com.affa.apppengaduan.apppengaduan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

/**
 * Created by Fernalia on 5/25/2016.
 */
public class HomeActivity extends AppCompatActivity {
    TextView txt_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        txt_name = (TextView) findViewById(R.id.txt_name);
        txt_name.setText("" + sharedPreferences.getString("nama", "0"));
    }

    public void go_to_list_pengaduan(View v){
        Intent i = new Intent(HomeActivity.this, ListPengaduanActivity.class);
        startActivity(i);
    }

    public void go_to_pengaduan(View v){
        Intent i = new Intent(HomeActivity.this, PengaduanActivity.class);
        startActivity(i);
    }
}
