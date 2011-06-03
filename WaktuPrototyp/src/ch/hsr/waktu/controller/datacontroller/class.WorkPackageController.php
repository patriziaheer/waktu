<?php

require_once '../../domain/class.WorkPackage.php';

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
			$workPackages[$counter] = new WorkPackage($data->description, $data->active, $data->project, $data->id);
			$counter++;
		}
		
		return $workPackages;
	}
	 
	public function getWorkPackage($id) {

		$query = pg_query($this->con, "SELECT * FROM workpackage WHERE id = ".$id);
		
		$data = pg_fetch_object($query);
		$workPackage = new WorkPackage($data->description, $data->active, ProjectController::getInstance()->getProject($data->project), $data->id);
				
		return $workPackage;		
	}
	
	public function getWorkPackagesByProject($project) {

		$query = pg_query($this->con, "SELECT * FROM workpackage WHERE id = ".$project->getId());
		
		$data = pg_fetch_object($query);

		$counter = 0;
		
		while($data = pg_fetch_object($query)) {
			$workPackages[$counter] = new WorkPackage($data->description, $data->active, ProjectController::getInstance()->getProject($data->project), $data->id);
			$counter++;
		}
		
		return $workPackages;		
	}
	
	public function getWorkPackagesOfUser($user) {
		$query = "SELECT wp.* FROM projectstaff ps JOIN project p ON ps.id = p.id JOIN usr u ON ps.usr = u.id RIGHT JOIN workpackage wp ON wp.project = p.id WHERE u.id = ".$user->getUsrId()." AND p.active = true ORDER BY p.description, wp.description ASC";
		$query = pg_query($query);
		$counter = 0;
		
		while($data = pg_fetch_object($query)) {
			$workpackages[$counter] = new WorkPackage($data->description, $data->activ, ProjectController::getInstance()->getProject($data->project), $data->id);
			$counter++;
		}
		
		return $workpackages;
	}
	
	
	
	
}
