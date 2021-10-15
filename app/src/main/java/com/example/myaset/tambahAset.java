package com.example.myaset;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class tambahAset extends AppCompatActivity implements View.OnClickListener{

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText editTextNameAset;
    private EditText editTextCode;
    private EditText editTextCap;
    private EditText editTextType;
    private EditText editTextDateman;
    private EditText editTextDatepm;

    private ImageButton buttonAdd;
    private ImageButton buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_aset);


        //Inisialisasi dari View
        editTextNameAset = (EditText) findViewById(R.id.editTextNameAset);
        editTextCode = (EditText) findViewById(R.id.editTextCode);
        editTextCap = (EditText) findViewById(R.id.editTextCap);
        editTextType = (EditText) findViewById(R.id.editTextType);
        editTextDateman = (EditText) findViewById(R.id.editTextDateman);
        editTextDatepm = (EditText) findViewById(R.id.editTextDatepm);

        buttonAdd = (ImageButton) findViewById(R.id.buttonAdd);
        buttonView = (ImageButton) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }


    //Dibawah ini merupakan perintah untuk Menambahkan Aset (CREATE)
    private void addAset(){

        final String name = editTextNameAset.getText().toString().trim();
        final String code = editTextCode.getText().toString().trim();
        final String cap = editTextCap.getText().toString().trim();
        final String type = editTextType.getText().toString().trim();
        final String dateman = editTextDateman.getText().toString().trim();
        final String datepm = editTextDatepm.getText().toString().trim();

        class AddAset extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(tambahAset.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tambahAset.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_ASET_NAMA_ASET,name);
                params.put(konfigurasi.KEY_ASET_KODE_ASET,code);
                params.put(konfigurasi.KEY_ASET_KAPASITAS,cap);
                params.put(konfigurasi.KEY_ASET_TIPE,type);
                params.put(konfigurasi.KEY_ASET_TANGGAL_MANUFAKTUR,dateman);
                params.put(konfigurasi.KEY_ASET_TANGGAL_PM,datepm);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddAset ae = new AddAset();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addAset();
        }

        if(v == buttonView){
            startActivity(new Intent(this,TampilanSemuaAset.class));
        }
    }
}