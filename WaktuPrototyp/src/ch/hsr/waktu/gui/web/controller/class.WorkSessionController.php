<?php

require_once 'domain/class.WorkSession.php';
require_once 'class.UsrController.php';
require_once 'class.WorkPackageController.php';

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
	
	public function addWorkSession($worksession) {
	
		$query = "	INSERT INTO worksession (usr, workpackage, starttime, endtime, description) 
					VALUES (
					".$worksession->getUser()->getUsrId().", 
					".$worksession->getWorkPackage()->getId().", 
					to_timestamp('".$worksession->getStartTime()."','YYYY-MM-DD HH24:MI'), 
					to_timestamp('".$worksession->getEndTime()."','YYYY-MM-DD HH24:MI'),
					'".$worksession->getDescription()."')";
		
		$return = pg_query($this->con,$query);
		if(!$return) {
			return false;
		} else {
			return true;
		}
	}
	
	
	
}