<?php
require "con.php";

$method = $_POST['method'];


if($method == "login")
{

$phone_no = $_POST['phone'];
$password = $_POST['password'];

$sql_query = "SELECT * FROM main WHERE phoneNumber = '$phone_no' AND password = '$password'";

$result = mysqli_query($conn, $sql_query);
if($result)
{
    while ($row = mysqli_fetch_assoc($result))
        {
            $array[] = $row;
        }
}
else
{
    echo mysqli_error($conn);
}

echo json_encode($array);

mysqli_close($conn);

}




else if($method == "changepass")
{

$phone_no = $_POST['phone'];
$new_password = $_POST['new_pass'];

$sql_query = "UPDATE main SET password = '$new_password' WHERE phoneNumber = '$phone_no'";

$result = mysqli_query($conn, $sql_query);
if($result)
{
    echo "Password Changed Successfully";
}
else
{
    echo "Opertion failed.";
}

mysqli_close($conn);

}


?>