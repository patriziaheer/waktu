<?php

require_once 'domain/class.Project.php';

class ProjectController {
	
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
	
	public function getAllProject() {
		
		$query = pg_query($this->con, "SELECT * FROM project");
		
		$counter = 0;
		
		while($data = pg_fetch_object($query)) {
			$projects[$counter] = new Project($data->projectidentifier, $data->description, $data->projectmanager, $data->active, $data->plannedtime, $data->projectid);
			$counter++;
		}
		
		return $projects;
	}
	
	public function getProject($projectid) {
		
		$query = pg_query($this->con, "SELECT * FROM project WHERE projectid = $projectid");
		
		$data = pg_fetch_object($query);
		$project = new Project($data->projectidentifier, $data->description, $data->projectmanager, $data->active, $data->plannedtime, $data->projectid);
		
		return $project;
	}
	
	public function getProjectOfUser($user) {
		$query = pg_query("SELECT p.* FROM projectstaff ps JOIN project p ON ps.projectid = p.projectid JOIN usr u ON ps.usrid = u.usrid WHERE u.usrid = ".$user->getUsrId()." AND p.active = true");

		$counter = 0;
		
		while($data = pg_fetch_object($query)) {
			$projects[$counter] = new Project($data->projectidentifier, $data->description, $data->projectmanager, $data->active, $data->plannedtime, $data->projectid);
			$counter++;
		}
		
		return $projects;
	}
	
	
	
	
}