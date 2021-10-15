package com.example.myaset;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class tampilAset extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextId;
    private EditText editTextNameAset;
    private EditText editTextCode;
    private EditText editTextCap;
    private EditText editTextType;
    private EditText editTextDateman;
    private EditText editTextDatepm;

    private ImageButton buttonUpdate;
    private ImageButton buttonDelete;
    private ImageButton buttonView;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_aset);

        Intent intent = getIntent();

        id = intent.getStringExtra(konfigurasi.ASET_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNameAset = (EditText) findViewById(R.id.editTextNameAset);
        editTextCode = (EditText) findViewById(R.id.editTextCode);
        editTextCap = (EditText) findViewById(R.id.editTextCap);
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextDateman = (EditText) findViewById(R.id.editTextDateman);
        editTextDatepm = (EditText) findViewById(R.id.editTextDatepm);

        buttonUpdate = (ImageButton) findViewById(R.id.buttonUpdate);
        buttonDelete = (ImageButton) findViewById(R.id.buttonDelete);
        buttonView = (ImageButton) findViewById(R.id.buttonView);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonView.setOnClickListener(this);

        editTextId.setText(id);

        getAset();
    }

    private void getAset(){
        class GetAset extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampilAset.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showAset(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_GET_ASET,id);
                return s;
            }
        }
        GetAset ge = new GetAset();
        ge.execute();
    }

    private void showAset(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(konfigurasi.TAG_NAMA_ASET);
            String code = c.getString(konfigurasi.TAG_KODE_ASET);
            String cap = c.getString(konfigurasi.TAG_KAPASITAS);
            String type = c.getString(konfigurasi.TAG_TIPE);
            String dateman = c.getString(konfigurasi.TAG_TANGGAL_MANUFAKTUR);
            String datepm = c.getString(konfigurasi.TAG_TANGGAL_PM);

            editTextNameAset.setText(name);
            editTextCode.setText(code);
            editTextCap.setText(cap);
            editTextType.setText(type);
            editTextDateman.setText(dateman);
            editTextDatepm.setText(datepm);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateAset(){
        final String name = editTextNameAset.getText().toString().trim();
        final String code = editTextCode.getText().toString().trim();
        final String cap = editTextCap.getText().toString().trim();
        final String type = editTextType.getText().toString().trim();
        final String dateman = editTextDateman.getText().toString().trim();
        final String datepm = editTextDatepm.getText().toString().trim();

        class UpdateAset extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampilAset.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tampilAset.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_ASET_ID,id);
                hashMap.put(konfigurasi.KEY_ASET_NAMA_ASET,name);
                hashMap.put(konfigurasi.KEY_ASET_KODE_ASET,code);
                hashMap.put(konfigurasi.KEY_ASET_KAPASITAS,cap);
                hashMap.put(konfigurasi.KEY_ASET_TIPE,type);
                hashMap.put(konfigurasi.KEY_ASET_TANGGAL_MANUFAKTUR,dateman);
                hashMap.put(konfigurasi.KEY_ASET_TANGGAL_PM,datepm);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_ASET,hashMap);

                return s;
            }
        }

        UpdateAset ue = new UpdateAset();
        ue.execute();
    }

    private void deleteAset(){
        class DeleteAset extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tampilAset.this, "Updating...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tampilAset.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_ASET, id);
                return s;
            }
        }

        DeleteAset de = new DeleteAset();
        de.execute();
    }

    private void confirmDeleteAset(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Aset ini?");

        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteAset();
                        startActivity(new Intent(tampilAset.this,TampilanSemuaAset.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateAset();
        }

        if(v == buttonDelete){
            confirmDeleteAset();
        }

        if(v == buttonView){
            startActivity(new Intent(this,TampilanSemuaAset.class));
        }
    }
}
