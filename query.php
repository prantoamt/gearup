<?php
require "con.php";

$method = $_POST['method'];


if($method == "login")
{

$phone_no = $_POST['phone'];
$password = $_POST['password'];

$sql_query = "SELECT * FROM mechanic_table WHERE m_phone = '$phone_no' AND m_pass = '$password'";

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

$sql_query = "UPDATE mechanic_table SET m_pass = '$new_password' WHERE m_phone = '$phone_no'";

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