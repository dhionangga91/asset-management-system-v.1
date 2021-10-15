<?php


 //Mendapatkan Nilai ID
 @$id = $_GET['id'];

 //Import File Koneksi Database
 require_once('koneksi.php');

 //Membuat SQL Query
 $sql = "DELETE FROM tb_aset WHERE id=$id;";


 //Menghapus Nilai pada Database
 if(mysqli_query($con,$sql)){
 echo 'Berhasil Menghapus Aset';
 }else{
 echo 'Gagal Menghapus Aset';
 }

 mysqli_close($con);
 ?>
