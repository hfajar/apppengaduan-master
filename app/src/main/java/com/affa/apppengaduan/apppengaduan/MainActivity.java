package com.affa.apppengaduan.apppengaduan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText txt_username;
    EditText txt_password;
    Button btn_login;

    String username;
    String password;

    public static final String JSON_ARRAY = "result";
    private JSONArray data = null;
    private String json;

    String url = "http://10.0.2.2/pengaduan_app/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!sharedPreferences.getString("nisn", "0").equals("0")){
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
        }

        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);
        btn_login    = (Button) findViewById(R.id.btn_login);
    }

    public void login(View v){
        username = "" + txt_username.getText();
        password = "" + txt_password.getText();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String json_param) {
                json = json_param;
                parseJSON();
//                Toast.makeText(MainActivity.this, json, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void parseJSON() {
        try {
            JSONObject jsonObject = new JSONObject(json);

            data = jsonObject.getJSONArray(JSON_ARRAY);

            for(int i = 0; i < data.length(); i++) {
                if(data.getJSONObject(i).getString("status").equals("success")){
                    SavePreferences("nisn", data.getJSONObject(i).getString("nisn"));
                    SavePreferences("nama", data.getJSONObject(i).getString("nama"));
                    SavePreferences("email", data.getJSONObject(i).getString("email"));
                    SavePreferences("username", data.getJSONObject(i).getString("username"));
                } else {
                    Toast.makeText(MainActivity.this, "Login Gagal", Toast.LENGTH_LONG).show();
                }
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void SavePreferences(String key, String value){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor     = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(i);
    }
}
