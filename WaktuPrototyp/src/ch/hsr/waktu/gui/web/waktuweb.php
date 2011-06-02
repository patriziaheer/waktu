<?php

	require_once '../../controller/datacontroller/class.UsrController.php';
	require_once '../../controller/datacontroller/class.ProjectController.php';
	require_once '../../controller/datacontroller/class.WorkSessionController.php';
	require_once '../../controller/datacontroller/class.WorkPackageController.php';

	require_once '../../services/class.TimeUtil.php';

	require_once '../../controller/class.LoginController.php';
	require_once '../../controller/class.PersistenceController.php';

	$pec = PersistenceController::getInstance();
	$pec->openConnection();

	$uc = UsrController::getInstance();
	$wc = WorkSessionController::getInstance();
	$wp = WorkPackageController::getInstance();
	$lc = LoginController::getInstance();
	
	$lc->startSession();
	
	if(!empty($_POST)) {
		
		$date = TimeUtil::convertHumanToSqlDate($_POST['date']);
		$wc->addWorkSession($lc->getLoggedInUser(), WorkPackageController::getInstance()->getWorkPackage($_POST['workpackageid']), $date." ".$_POST['start'], $date." ".$_POST['end'], $_POST['desc']);
		unset($_POST);
	}
	
	$users = $uc->getAllUsr();
	$projects = $wp->getWorkPackagesOfUser($lc->getLoggedInUser());
	$worksessions = $wc->getAllWorkSession();	

	function formatString($inputString) {
		if(strlen($inputString) > 26) {
			return $inputString.".";
		}
		return $inputString;
	}
	
	
	foreach($worksessions as $key => $ws) {
		$wsList .= 
			'<tr class="ws">
				<td class="ws">'.$ws->getWorkPackage()->getProject()->getDescription().'</td>
				<td class="ws">'.$ws->getWorkPackage()->getDescription().'</td>
				<td class="ws">'.$ws->getStartTime().'</td>
				<td class="ws">'.$ws->getEndTime().'</td>
				<td class="ws">'.$ws->getDescription().'</td>
			</tr>';
	}
	
	foreach($projects as $key => $p) {
		$pList .= '
				<option value="'.$p->getId().'">'.formatString(substr($p->getProject()->getDescription()." / ".$p->getDescription(),0,27)).'</option>				
				';
	}
	
	$pec->closeConnection();
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8" /> 
<title>WaktuWeb</title>
	<link rel="stylesheet" href="waktuweb.css">
</head>
<body>
<div id="site">
	<div id="logo">
		<div style="width: 350px; text-align: right;">Eingeloggt als: <i><?php echo $_SESSION['user']->getUsername() ?></i> | <a href="logout.php">Ausloggen</a></div>
		<img style="padding-top: 40px;" width="293px" height="125px" src="img/logo.png">
	</div>
	<div id="addworksession">
		<form action="waktuweb.php" method="post">
			<table>
				<tr>
					<td>Projekt / Arbeitspaket: </td>
					<td><select name="workpackageid"><?php echo $pList ?></select></td>
				</tr>
				<tr>
					<td>Datum: </td>
					<td><input type="text" name="date" maxlength="10" size="10"> [tt.mm.jjjj]</td>
				</tr>
				<tr>
					<td>Von: </td>
					<td><input type="text" name="start" maxlength="5" size="5"> [hh:mm]</td>
				</tr>
				<tr>
					<td>Bis: </td>
					<td><input type="text" name="end" maxlength="5" size="5"> [hh:mm]</td>
				</tr>
				<tr>
					<td>Beschreibung: </td>
					<td><input type="text" name="desc" size="32"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td id="insert" align="right"><input type="reset" value="Eingabe löschen"><input type="submit" value="Eintragen"></td>
				</tr>
			</table>
			
		</form>
	</div>
	<div id="listworksession">
		<table class="ws">
			<tr class="ws">
				<th width="150">Projekt</th>
				<th width="150">Arbeitspaket</th>
				<th width="120">Von</th>
				<th width="120">Bis</th>
				<th width="300">Beschreibung</th>
			</tr>
			
			<?php echo $wsList ?>
			
		</table>
	</div>
</div>
</body>
</html>