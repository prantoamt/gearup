<?php
require "con.php";

$name = $_POST['name'];
$password = $_POST['password'];
$email = $_POST['email'];
$phone = $_POST['phone'];
$service = $_POST['service'];
$address = $_POST['address'];


$sql_query = "INSERT INTO main (fullName, password, phoneNumber, email, sType, Address) VALUES ('$name','$password','$phone','$email','$service','$address')";

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