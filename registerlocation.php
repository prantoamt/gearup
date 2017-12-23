<?php
require "con.php";

$phone_no = $_POST['phone'];
$lat = $_POST['lat'];
$lon = $_POST['lon'];
$status = $_POST['status'];

$sql_query = "UPDATE mechanic_location SET m_lat = '$lat', m_lon = '$lon', is_active = '$status' WHERE m_phone = '$phone_no'";

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