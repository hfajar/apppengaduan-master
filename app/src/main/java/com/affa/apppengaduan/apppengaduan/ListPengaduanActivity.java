package com.affa.apppengaduan.apppengaduan;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Fernalia on 5/24/2016.
 */
public class ListPengaduanActivity extends AppCompatActivity {
    public static String[] id_pengaduan;
    public static String[] kategori;
    public static String[] nama_pengadu;
    public static String[] date_time;
    public static String[] foto;
    public static String[] deskripi;

    ListView lv_pengaduan;
    Context context;

    public static final String JSON_ARRAY = "result";
    private JSONArray data = null;
    private String json;

    String url = "http://10.0.2.2:81/pengaduan_app/list_all_pengaduan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pengaduan);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.hide();

        lv_pengaduan = (ListView) findViewById(R.id.lv_pengaduan);
        context = this;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String json_param) {
                json = json_param;
                parseJSON();
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                        ListPengaduanActivity.this,
//                        android.R.layout.simple_list_item_1,
//                        nama_pengadu);

//                lv_pengaduan.setAdapter(adapter);
                lv_pengaduan.setAdapter(new CustomAdapter(ListPengaduanActivity.this, nama_pengadu,kategori, deskripi, date_time));
//                Toast.makeText(ListPengaduanActivity.this, nama_pengadu[0], Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ListPengaduanActivity.this);
        requestQueue.add(stringRequest);
    }

    private void parseJSON() {
        try {
            JSONObject jsonObject = new JSONObject(json);

            data = jsonObject.getJSONArray(JSON_ARRAY);

            id_pengaduan = new String[data.length()];
            kategori     = new String[data.length()];
            nama_pengadu = new String[data.length()];
            date_time    = new String[data.length()];
            foto         = new String[data.length()];
            deskripi     = new String[data.length()];

            for(int i = 0; i < data.length(); i++) {
                id_pengaduan[i] = data.getJSONObject(i).getString("id_pengaduan");
                kategori[i]     = data.getJSONObject(i).getString("kat");
                nama_pengadu[i] = data.getJSONObject(i).getString("nama");
                date_time[i]    = data.getJSONObject(i).getString("date_time");
                foto[i]         = data.getJSONObject(i).getString("foto");
                deskripi[i]     = data.getJSONObject(i).getString("deskripsi");
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
