<?php

class WorkSession {
	private $id;
	private $endTime;
	private $startTime;
	private $user;
	private $description;
	private $workPackage;

	public function __construct($user, $startTime, $endTime, $workPackage, $description, $id=0) {
		$this->id = $id;
		$this->endTime = $endTime;
		$this->startTime = $startTime;
		$this->user = $user;
		$this->description = $description;
		$this->workPackage = $workPackage;
	}
	
	public function getId() {
		return $this->id;
	}
	
	public function getEndTime() {
		return $this->endTime;
	}
	
	public function getStartTime() {
		return $this->startTime;
	}
	
	public function getUser() {
		return $this->user;
	}
	
	public function getDescription() {
		return $this->description;
	}
	
	public function getWorkPackage() {
		return $this->workPackage;
	}


}

?>