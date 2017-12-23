<?php
// Include config file
require_once 'config.php';

// Define variables and initialize with empty values
$username = $password = $confirm_password = $mobileNumber = $email /*= $location*/ = $postcode = $fName = $lName = $gender =  $dob= "";


//$dob = datefmt_create( "en_US" ,IntlDateFormatter::FULL, IntlDateFormatter::FULL,
  //   'America/Los_Angeles',IntlDateFormatter::GREGORIAN  ,"MM/dd/yyyy");
//.datefmt_format( $dob , 0

$username_err = $password_err = $confirm_password_err = $mobileNumber_err = $email_err /*= $location_err*/ = $postcode_err = $fName_err = $lName_err = $dob_err = $gender_err = "";

// Processing form data when form is submitted
if($_SERVER["REQUEST_METHOD"] == "POST"){

    // Validate username
    if(empty(trim($_POST["username"]))){
        $username_err = "Please enter a username.";
    } else{
        // Prepare a select statement
        $sql = "SELECT id FROM users WHERE username = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_username);

            // Set parameters
            $param_username = trim($_POST["username"]);

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);

                if(mysqli_stmt_num_rows($stmt) == 1){
                    $username_err = "This username is already taken.";
                } else{
                    $username = trim($_POST["username"]);
                }
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

    // Validate fName
    if(empty(trim($_POST["fName"]))){
        $fName_err = "Please enter your First Name.";
    } else{
        // Prepare a select statement
        $sql = "SELECT id FROM users WHERE fName = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_fName);

            // Set parameters
            $param_fName = trim($_POST["fName"]);

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);
                $fName = trim($_POST["fName"]);

            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

    // Validate lName
    if(empty(trim($_POST["lName"]))){
        $lName_err = "Please enter your Last Name.";
    } else{
        // Prepare a select statement
        $sql = "SELECT id FROM users WHERE lName = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_lName);

            // Set parameters
            $param_lName = trim($_POST["lName"]);

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);
                $lName = trim($_POST["lName"]);
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

    // Validate gender
    if(empty(trim($_POST["gender"]))){
        $username_err = "Please enter your gender.";
    } else{
        // Prepare a select statement
        $sql = "SELECT id FROM users WHERE gender = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_gender);

            // Set parameters
            $param_gender = trim($_POST["gender"]);

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);
                $gender = trim($_POST["gender"]);
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

     // Validate dob
    if(empty(trim($_POST["dob"]))){
        $dob_err = "Please enter your date of birth.";
    } else{
        // Prepare a select statement
        $sql = "SELECT id FROM users WHERE dob = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_dob);

            // Set parameters
            $param_dob = trim($_POST["dob"]);

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);
                $dob = trim($_POST["dob"]);
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

    // Validate password
    if(empty(trim($_POST['password']))){
        $password_err = "Please enter a password.";
    } elseif(strlen(trim($_POST['password'])) < 6){
        $password_err = "Password must have atleast 6 characters.";
    } else{
        $password = trim($_POST['password']);
    }

    // Validate confirm password
    if(empty(trim($_POST["confirm_password"]))){
        $confirm_password_err = 'Please confirm password.';
    } else{
        $confirm_password = trim($_POST['confirm_password']);
        if($password != $confirm_password){
            $confirm_password_err = 'Password did not match.';
        }
    }

    // Validate mobileNumber
    if(empty(trim($_POST["mobileNumber"]))){
        $mobileNumber_err = "Please enter a mobileNumber.";
    } else{
        // Prepare a select statement
        $sql = "SELECT id FROM users WHERE mobileNumber = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_mobileNumber);

            // Set parameters
            $param_mobileNumber = trim($_POST["mobileNumber"]);

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);

                if(mysqli_stmt_num_rows($stmt) == 1){
                    $mobileNumber_err = "This mobileNumber is already taken.";
                } else{
                    $mobileNumber = trim($_POST["mobileNumber"]);
                }
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

    // Validate email
    if(empty(trim($_POST["email"]))){
        $email_err = "Please enter a email.";
    } else{
        // Prepare a select statement
        $sql = "SELECT id FROM users WHERE email = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "s", $param_email);

            // Set parameters
            $param_email = trim($_POST["email"]);

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);
                $email = trim($_POST["email"]);
            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

    // Validate postcode
    if(empty(trim($_POST["postcode"]))){
        $postcode_err = "Please enter your postcode.";
    } else{
        // Prepare a select statement
        $sql = "SELECT id FROM users WHERE postcode = ?";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "d", $param_postcode);

            // Set parameters
            $param_postcode = trim($_POST["postcode"]);

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                /* store result */
                mysqli_stmt_store_result($stmt);
                $postcode = trim($_POST["postcode"]);

            } else{
                echo "Oops! Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

    // Check input errors before inserting in database
    if(empty($username_err) && empty($fName_err)  && empty($dob_err)  && empty($gender_err) && empty($lName_err) && empty($password_err) && empty($confirm_password_err) && empty($mobileNumber_err) && empty($email_err) && empty($postcode_err)){

        // Prepare an insert statement
        $sql = "INSERT INTO users (username, fName, lName, gender, dob, password, mobileNumber, email, postcode)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        if($stmt = mysqli_prepare($link, $sql)){
            // Bind variables to the prepared statement as parameters
            mysqli_stmt_bind_param($stmt, "ssssssssd", $param_username, $param_fName, $param_lName, $param_gender, $param_dob, $param_password, $param_mobileNumber, $param_email, $param_postcode);

            // Set parameters
            $param_username = $username;
            $param_fName = $fName;
            $param_lName = $lName;
            $param_gender = $gender;
            $param_dob = $dob;
            $param_password = password_hash($password, PASSWORD_DEFAULT); // Creates a password hash
            $param_mobileNumber = $mobileNumber;
            $param_email = $email;
            //$param_location = $location;
            $param_postcode = $postcode;

            // Attempt to execute the prepared statement
            if(mysqli_stmt_execute($stmt)){
                // Redirect to login page
                header("location: login.php");
            } else{
                echo "Something went wrong. Please try again later.";
            }
        }

        // Close statement
        mysqli_stmt_close($stmt);
    }

    // Close connection
    mysqli_close($link);
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="bootstrap.css">
    <style type="text/css">
        body{ font: 14px sans-serif; }
        .wrapper{ width: 350px; padding: 20px; }
    </style>
</head>
<body>
    <div class="wrapper">
        <h2>Sign Up</h2>
        <p>Please fill this form to create an account.</p>
        <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
            <div class="form-group <?php echo (!empty($username_err)) ? 'has-error' : ''; ?>">
                <label>Username:<sup>*</sup></label>
                <input type="text" name="username"class="form-control" value="<?php echo $username; ?>">
                <span class="help-block"><?php echo $username_err; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($fName_err)) ? 'has-error' : ''; ?>">
                <label>First Name:<sup>*</sup></label>
                <input type="text" name="fName"class="form-control" value="<?php echo $fName; ?>">
                <span class="help-block"><?php echo $fName_err; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($lName_err)) ? 'has-error' : ''; ?>">
                <label>Last Name:<sup>*</sup></label>
                <input type="text" name="lName"class="form-control" value="<?php echo $lName; ?>">
                <span class="help-block"><?php echo $lName_err; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($gender_err)) ? 'has-error' : ''; ?>">
                <label>Gender:<sup>*</sup></label>
                <input type="text" name="gender"class="form-control" value="<?php echo $gender; ?>">
                <span class="help-block"><?php echo $gender_err; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($dob_err)) ? 'has-error' : ''; ?>">
                <label>Date of Birth:<sup>*</sup></label>
                <input type="date" name="dob"class="form-control" value="<?php echo $dob; ?>">
                <span class="help-block"><?php echo $dob_err; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($password_err)) ? 'has-error' : ''; ?>">
                <label>Password:<sup>*</sup></label>
                <input type="password" name="password" class="form-control" value="<?php echo $password; ?>">
                <span class="help-block"><?php echo $password_err; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($confirm_password_err)) ? 'has-error' : ''; ?>">
                <label>Confirm Password:<sup>*</sup></label>
                <input type="password" name="confirm_password" class="form-control" value="<?php echo $confirm_password; ?>">
                <span class="help-block"><?php echo $confirm_password_err; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($mobileNumber_err)) ? 'has-error' : ''; ?>">
                <label>Mobile Number:<sup>*</sup></label>
                <input type="text" name="mobileNumber"class="form-control" value="<?php echo $mobileNumber; ?>">
                <span class="help-block"><?php echo $mobileNumber_err; ?></span>
            </div>
            <div class="form-group <?php echo (!empty($email_err)) ? 'has-error' : ''; ?>">
                <label>Email:</label>
                <input type="text" name="email"class="form-control" value="<?php echo $email; ?>">
                <span class="help-block"><?php echo $email_err; ?></span>
            </div>
            <!--<div class="form-group </*?php echo (!empty($location_err)) ? 'has-error' : ''; ?>"*/>
                <label>Location:<sup>*</sup></label>
                <input type="text" name="location"class="form-control" value="<//?php echo $location; ?>">
                <span class="help-block"><//?php echo $location_err; ?></span>
            </div>-->
            <div class="form-group <?php echo (!empty($postcode_err)) ? 'has-error' : ''; ?>">
                <label>Post Code:<sup>*</sup></label>
                <input type="text" name="postcode"class="form-control" value="<?php echo $postcode; ?>">
                <span class="help-block"><?php echo $postcode_err; ?></span>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-primary" value="Submit">
                <input type="reset" class="btn btn-default" value="Reset">
            </div>
            <p>Already have an account? <a href="login.php">Login here</a>.</p>
        </form>
    </div>
</body>
</html>
