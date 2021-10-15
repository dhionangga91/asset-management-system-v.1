<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		//MEndapatkan Nilai Dari Variable
		$id = $_POST['id'];
		$name = $_POST['name'];
		$code = $_POST['code'];
		$cap = $_POST['cap'];
		$type = $_POST['type'];
		$dateman = $_POST['dateman'];
		$datepm = $_POST['datepm'];

		//import file koneksi database
		require_once('koneksi.php');

		//Membuat SQL Query
		if(empty($code) || empty($name) || empty($cap) || empty($type) || empty($dateman) || empty($datepm)){   
        echo 'Harap isi yang kosong terlebih dahulu';
    }
    else{
		$sql = "UPDATE tb_aset SET kode_aset = '$code', nama_aset = '$name', kapasitas = '$cap', tipe = '$type', tgl_manufaktur = '$dateman', tgl_pm = '$datepm' WHERE id = $id;";

		//Meng-update Database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data Aset';
		}else{
			echo 'Gagal Update Data Aset';
		}
		mysqli_close($con);
	}
	}
?>
