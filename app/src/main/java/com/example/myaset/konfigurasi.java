package com.example.myaset;

public class konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    //Settingan IP Hotspot
//    public static final String URL_ADD = "http://192.168.43.148/aset/tambahAset.php";
//    public static final String URL_GET_ALL = "http://192.168.43.148/aset/tampilSemuaAset.php";
//    public static final String URL_GET_ASET = "http://192.168.43.148/aset/tampilAset.php?id=";
//    public static final String URL_UPDATE_ASET = "http://192.168.43.148/aset/updateAset.php";
//    public static final String URL_DELETE_ASET = "http://192.168.43.148/aset/hapusAset.php?id=";

    //Settingan IP Wifi
    public static final String URL_ADD="http://192.168.0.109/aset/tambahAset.php";
    public static final String URL_GET_ALL = "http://192.168.0.109/aset/tampilSemuaAset.php";
    public static final String URL_GET_ASET = "http://192.168.0.109/aset/tampilAset.php?id=";
    public static final String URL_UPDATE_ASET = "http://192.168.0.109/aset/updateAset.php";
    public static final String URL_DELETE_ASET = "http://192.168.0.109/aset/hapusAset.php?id=";

    //Settingan Hosting
//    public static final String URL_ADD="https://myasetforskripsi.000webhostapp.com/Aset/tambahAset.php";
//    public static final String URL_GET_ALL = "https://myasetforskripsi.000webhostapp.com/Aset/tampilSemuaAset.php";
//    public static final String URL_GET_ASET = "https://myasetforskripsi.000webhostapp.com/Aset/tampilAset.php?id=";
//    public static final String URL_UPDATE_ASET = "https://myasetforskripsi.000webhostapp.com/Aset/updateAset.php";
//    public static final String URL_DELETE_ASET = "https://myasetforskripsi.000webhostapp.com/Aset/hapusAset.php?id=";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_ASET_ID = "id";
    public static final String KEY_ASET_NAMA_ASET = "name"; //name itu variabel untuk nama_aset
    public static final String KEY_ASET_KODE_ASET = "code"; //code itu variabel untuk kode_aset
    public static final String KEY_ASET_KAPASITAS = "cap"; //salary itu variabel untuk kapasitas
    public static final String KEY_ASET_TIPE = "type"; //type itu variabel untuk tipe
    public static final String KEY_ASET_TANGGAL_MANUFAKTUR = "dateman"; //code itu variabel untuk tgl_manufaktur
    public static final String KEY_ASET_TANGGAL_PM = "datepm"; //salary itu variabel untuk tgl_pm

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID= "id";
    public static final String TAG_NAMA_ASET = "name";
    public static final String TAG_KODE_ASET = "code";
    public static final String TAG_KAPASITAS = "cap";
    public static final String TAG_TIPE = "type";
    public static final String TAG_TANGGAL_MANUFAKTUR = "dateman";
    public static final String TAG_TANGGAL_PM = "datepm";

    //ID Aset

    public static final String ASET_ID = "id";
}
