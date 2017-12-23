<?php
require "con.php";

$name = $_POST['name'];
$password =  $_POST['password'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$service = $_POST['service'];


$sql_query = "INSERT INTO mechanic_table (m_phone, m_full_name, m_pass, m_email, m_service) VALUES ('$phone','$name','$password','$email','$service')";

$result = mysqli_query($conn, $sql_query);
if ($result) 
{
    echo "Registration Successful";
}
else {

    echo "Your user name or email or password is already registered to our server";
}

mysqli_close($conn);
?>