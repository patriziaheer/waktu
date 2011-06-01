<?php

require_once 'domain/class.WorkPackage.php';

class WorkPackageController {

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
	
	public function getAllWorkPackage() {

		$query = pg_query($this->con, "SELECT * FROM workpackage");
		
		$counter = 0;
		
		while($data = pg_fetch_object($query)) {
			$workPackages[$counter] = new WorkPackage($description, $active, $project);
			$counter++;
		}
		
		return $workPackages;
	}
	
	public function getWorkPackage($id) {

		$query = pg_query($this->con, "SELECT * FROM workpackage WHERE workpackageid = ".$id);
		
		while($data = pg_fetch_object($query)) {
			$workPackage = new WorkPackage($description, $active, $project);
		}
		
		return $workPackage;		
	}
	
	
	
}