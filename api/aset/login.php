<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

 include 'koneksi.php';
 
 $con = mysqli_connect($server,$username,$password,$database);
 
 @$email = $_POST['email'];
 @$password = $_POST['password'];
 
 $Sql_Query = "select * from tb_user where email = '$email' and password = '$password' ";
 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 if(isset($check)){
 
 echo "Data Matched";
 }
 else{
 echo "Invalid Username or Password Please Try Again";
 }
 mysqli_close($con);
 }


?>