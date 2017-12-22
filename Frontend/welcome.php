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
    <link rel="stylesheet" href="bootstrap.css">
    <style type="text/css">
        body{ font: 14px sans-serif; text-align: center; }
    </style>
</head>
<body>
    <div class="page-header">
        <img class="img-circle" src="logo4.jpg" alt="Logo" width="5%" height="Auto";>
        <h1>GearUp. <br/> </h1>
        <h4>Search Car Repair Shop With Ease</h4>
         <h3> Hi, <b><?php echo $_SESSION['username']; ?></b>. </h3>
    </div>
  </aside>
  <section id="content" class="column-right">
  <td colspan="2" valign="top">
  <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3650.3333067272383!2d90.36636621498242!3d23.80674398456165!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3755c0d6f1d99d03%3A0xcd82050166bb03db!2sMirpur+10+Bus+Stopage!5e0!3m2!1sen!2sbd!4v1512851432292" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
  </td>
    <p><a href="logout.php" class="btn btn-danger">Sign Out of Your Account</a></p>
</body>
</html>
