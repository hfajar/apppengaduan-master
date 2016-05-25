package com.affa.apppengaduan.apppengaduan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Fernalia on 5/25/2016.
 */
public class CustomAdapter extends BaseAdapter {
    String[] result_name;
    String[] result_kategori;
    String[] result_deskripsi;
    String[] result_datetime;
    Context context;

    private static LayoutInflater inflater=null;
    public CustomAdapter(ListPengaduanActivity listPengaduanActivity, String[] nama_pengadu, String[] kategori, String[] deskripsi, String[] datetime) {
        // TODO Auto-generated constructor stub
        result_name = nama_pengadu;
        result_kategori = kategori;
        result_deskripsi = deskripsi;
        result_datetime = datetime;
        context=listPengaduanActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result_name.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView txt_nama;
        TextView txt_kategori;
        TextView txt_deskripsi;
        TextView txt_datetime;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_list, null);
        holder.txt_nama=(TextView) rowView.findViewById(R.id.txt_nama);
        holder.txt_kategori=(TextView) rowView.findViewById(R.id.txt_kategori);
        holder.txt_deskripsi=(TextView) rowView.findViewById(R.id.txt_deskripsi);
        holder.txt_datetime=(TextView) rowView.findViewById(R.id.txt_date_time);
        holder.txt_nama.setText(result_name[position]);
        holder.txt_kategori.setText(result_kategori[position]);
        holder.txt_deskripsi.setText(result_deskripsi[position]);
        holder.txt_datetime.setText(result_datetime[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result_name[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
}
