<?php
require "con.php";

$phone_no = $_POST['phone'];
$lat = $_POST['lat'];
$lon = $_POST['lon'];

$sql_query = "UPDATE currentLoc SET lat = '$lat',`long`= '$lon' WHERE phoneNumber = '$phone_no'";

$result = mysqli_query($conn, $sql_query);

if ($result) 
{
    echo "Location traced";
}

else {

    echo "Error: " . $sql_query . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>