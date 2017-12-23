<?php
// Initialize the session
session_start();

// If session variable is not set it will redirect to login page
if(!isset($_SESSION['username']) || empty($_SESSION['username'])){
  header("location: login.php");
  exit;
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link rel="stylesheet" href="bootstrap.css"  type="text/css">


</head>
<body>
    <div class="page-header">
        <img class="img-circle" src="logo4.jpg" alt="Logo" width="5%" height="Auto";>
        <h1>GearUp. <br/> </h1>
        <h4>Search Car Repair Shop With Ease</h4>
         <h3> Hi, <b><?php echo $_SESSION['username']; ?></b>. </h3>
    </div>

  <section id="content" class="column-right">
  <td colspan="2" valign="top">
    <iframe src="codee.html" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
  </td>
</section>
    <p><a href="logout.php" class="btn btn-danger">Sign Out of Your Account</a></p>
</body>
</html>
