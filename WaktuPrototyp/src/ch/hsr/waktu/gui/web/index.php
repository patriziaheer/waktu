<?php

	require_once 'controller/class.UsrController.php';
	require_once 'controller/class.ProjectController.php';
	require_once 'controller/class.WorkSessionController.php';
	require_once 'controller/class.WorkPackageController.php';

	require_once 'controller/class.LoginController.php';
	require_once 'controller/class.PersistenceController.php';

	$pec = PersistenceController::getInstance();
	$pec->openConnection();

	$uc = UsrController::getInstance();
	$pc = ProjectController::getInstance();
	$wc = WorkSessionController::getInstance();
	$wp = WorkPackageController::getInstance();
	
	$lc = LoginController::getInstance();
	
		
	$users = $uc->getAllUsr();
	$projects = $pc->getAllProject();
	$worksessions = $wc->getAllWorkSession();
	
	if(!empty($_POST)) {
		if($lc->login($_POST['user'],$_POST['password'])) {
			header("Location: waktuweb.php");
		} else {
			$errormsg = "Benutzer / Passwort falsch";
		}
	}

	
//	print_r($users);
//	print_r($projects);
//	print_r($worksessions);

//	$workP = $wp->getWorkPackage(1);
//	$user = $uc->getUsr(2);
//	print_r($user);
	
	//$workS = new WorkSession($user, "2011-05-25 02:00", '2011-05-25 00:01', 'Neuer Eintrag', $workP);	
	
//	print_r($workS);

//	echo $wc->addWorkSession($workS);

	
	
	$pec->closeConnection();

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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