package com.example.myaset;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilanSemuaAset extends AppCompatActivity implements ListView.OnItemClickListener{

    Button buttonHome;

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_semua_aset);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();

        //Button Home
        Button btn = (Button) findViewById(R.id.buttonHome);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TampilanSemuaAset.this,tambahAset.class);
                startActivity(i);
            }
        });

    }


    private void showAset(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(konfigurasi.TAG_ID);
                String name = jo.getString(konfigurasi.TAG_NAMA_ASET);
                String code = jo.getString(konfigurasi.TAG_KODE_ASET);
                String datepm = jo.getString(konfigurasi.TAG_TANGGAL_PM);

                HashMap<String,String> asets = new HashMap<>();
                asets.put(konfigurasi.TAG_ID,id);
                asets.put(konfigurasi.TAG_NAMA_ASET,name);
                asets.put(konfigurasi.TAG_KODE_ASET,code);
                asets.put(konfigurasi.TAG_TANGGAL_PM,datepm);
                list.add(asets);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilanSemuaAset.this, list, R.layout.activity_list_item,
                new String[]{konfigurasi.TAG_ID,konfigurasi.TAG_NAMA_ASET,konfigurasi.TAG_KODE_ASET,konfigurasi.TAG_TANGGAL_PM},
                new int[]{R.id.id, R.id.name, R.id.code, R.id.datepm});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilanSemuaAset.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showAset();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, tampilAset.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String asetId = map.get(konfigurasi.TAG_ID).toString();
        intent.putExtra(konfigurasi.ASET_ID,asetId);
        startActivity(intent);
    }
}
