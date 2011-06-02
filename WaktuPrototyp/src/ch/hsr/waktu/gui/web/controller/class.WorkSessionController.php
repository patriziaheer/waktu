<?php

require_once 'domain/class.WorkSession.php';

class WorkSessionController {

	private static $uc;
	private static $instance = null;
	private $con;

	static public function getInstance() {
		if(null === self::$instance) {
			self::$instance = new self;
		}
		return self::$instance;
	}

	public function __construct() {
		$this->con = PersistenceController::getInstance()->connection;
	}


	public function getAllWorkSession() {

		$query = pg_query($this->con, "SELECT to_char(starttime,'DD.MM.YYYY HH24:MI') as starttimef, to_char(endtime,'DD.MM.YYYY HH24:MI') as endtimef, * FROM worksession ORDER BY worksessionid DESC LIMIT 6;");

		$counter = 0;

		while($data = pg_fetch_object($query)) {
			$workSession[$counter] = new WorkSession(UsrController::getInstance()->getUsr($data->usr), $data->endtimef, $data->starttimef, WorkPackageController::getInstance()->getWorkPackage($data->workpackage), $data->description, $data->worksessionid);
			$counter++;
		}

		return $workSession;
	}

	public function addWorkSession($user, $workPackage, $startTime, $endTime, $description) {

		$query = "	INSERT INTO worksession (usr, workpackage, starttime, endtime, description)
					VALUES (
					".$user->getUsrId().", 
					".$workPackage->getId().", 
					to_timestamp('".$startTime."','YYYY-MM-DD HH24:MI'), 
					to_timestamp('".$endTime."','YYYY-MM-DD HH24:MI'),
					'".$description."')";

		$return = pg_query($this->con,$query);
		if(!$return) {
			return false;
		} else {
			return true;
		}
	}



}