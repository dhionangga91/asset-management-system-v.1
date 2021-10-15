<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Mendapatkan Nilai Variable
		
		@$code = $_POST['code'];
		@$name = $_POST['name'];
		@$cap = $_POST['cap'];
		@$type = $_POST['type'];
		@$dateman = $_POST['dateman'];
		@$datepm = $_POST['datepm'];
		
		
		//Pembuatan Syntax SQL
				
		$sql = "INSERT INTO tb_aset (kode_aset,nama_aset,kapasitas,tipe,tgl_manufaktur,tgl_pm) VALUES ('$code','$name','$cap','$type','$dateman','$datepm')";
	 if(empty($code) || empty($name) || empty($cap) || empty($type) || empty($dateman) || empty($datepm)){   
        echo 'Harap isi yang kosong terlebih dahulu';
    }
    else{
		//Import File Koneksi database
		require_once('koneksi.php');

		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Aset';
		}else{
			echo 'Gagal Menambahkan Aset';
		}

		mysqli_close($con);
	}}
?>
