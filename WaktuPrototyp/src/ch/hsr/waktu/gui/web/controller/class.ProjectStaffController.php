<?php

require_once 'domain/class.ProjectStaff.php';

class ProjectStaffController {

	private static  $instance = null;
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
	
	public function getAllProjectStaff() {

		$query = pg_query($this->con, "SELECT * FROM projectstaff");
		
		$counter = 0;
		
		while($data = pg_fetch_object($query)) {
			$projectStaff[$counter] = new ProjectStaff($project, $user);
			$counter++;
		}
		
		return $projectStaff;
	}
	
	
	
}