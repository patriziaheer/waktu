<?php

	require_once 'controller/class.UsrController.php';

	require_once 'controller/class.LoginController.php';
	require_once 'controller/class.PersistenceController.php';

	$pec = PersistenceController::getInstance();
	$pec->openConnection();

	$lc = LoginController::getInstance();
	
	if(!empty($_POST)) {
		if($result = $lc->login($_POST['user'],$_POST['password'])) {

		} else {
			$errormsg = "Benutzer / Passwort falsch";
		}
	}

	$pec->closeConnection();

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8" /> 
<title>WaktuWeb</title>
	<link rel="stylesheet" href="login.css">
</head>
<body>
	<div id="all">
		<div id="logo">
			<img width="234px" height="101px" src="img/logo.png">
		</div>
	<div id="site">
			<form action="index.php" method="post">
				<table>
					<tr>
						<td width="100">Benutzername: </td>
						<td><input type="text" name="user" maxlength="35" size="35"></td>
					</tr>
					<tr>
						<td>Passwort: </td>
						<td><input type="password" name="password" maxlength="35" size="35"></td>
					</tr>
					<tr>
						<td id="insert" align="right" colspan="2"><font color="red"> <?php echo $errormsg; ?></font>&nbsp;&nbsp;<input type="reset" value="Abbrechen"> &nbsp; <input type="submit" value="Anmelden"></td>
					</tr>
				</table>
				
			</form>
	</div>
</div>
</body>
</html>