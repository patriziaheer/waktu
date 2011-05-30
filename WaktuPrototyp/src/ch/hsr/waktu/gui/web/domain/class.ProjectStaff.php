<?php

class ProjectStaff {
	private $id;
	private $project;
	private $user;

	public function __construct($project, $user) {
		$this->project = $project;
		$this->user = $user;
	}
	
	public function getProject() {
		return $this->project;
	}
	
	public function getUser() {
		return $this->user;
	}

}

?>