<?php

class WorkPackage {
	private $id;
	private $description;
	private $active = true;
	private $project;

	public function __construct($description, $active, $project, $id=0) {
		$this->id = $id;
		$this->description = $description;
		$this->active = $active;
		$this->project = $project;
	}
	
	public function getId() {
		return $this->id;
	}
	
	public function getDescription() {
		return $this->description;
	}
	
	public function isActive() {
		return $this->active;
	}
	
	public function getProject() {
		return $this->project;
	}

}

?>