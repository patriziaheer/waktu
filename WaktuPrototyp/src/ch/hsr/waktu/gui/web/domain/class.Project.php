<?php

class Project {

	private $projectid;
	private $projectIdentifier;
	private $description;
	private $projectManager;
	private $active = true;
	private $plannedTime;

	public function __construct($projectIdentifier, $description, $projectManager, $active, $plannedTime, $id=0) {
		$this->projectid = $id;
		$this->projectIdentifier = $projectIdentifier;
		$this->description = $description;
		$this->projectManager = $projectManager;
		$this->active = $active;
		$this->plannedTime = $plannedTime;
	}
	
	public function getId() {
		return $this->id;
	}
	
	public function getProjectIdentifier() {
		return $this->projectIdentifier;
	}
	
	public function getDescription() {
		return $this->description;
	}
	
	public function getProjectManager() {
		return $this->projectManager;
	}
	
	public function isActive() {
		return $this->active;
	}
	
	public function getPlannedTime() {
		return $this->plannedTime;
	}
}

?>