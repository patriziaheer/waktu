<?php

class Usr {
	private $usrid;
	private $username;
	private $name;
	private $firstname;
	private $passwordhash;

	public function __construct($usrid, $username, $name, $firstname, $passwordhash) {
		$this->usrid = $usrid;
		$this->username = $username;
		$this->name = $name;
		$this->firstname = $firstname;
		$this->passwordhash = $passwordhash;
	}

	public function getUsrId() {
		return $this->usrid;
	}

	public function getUsername() {
		return $this->username;
	}

	public function getName() {
		return $this->name;
	}

	public function getFirstname() {
		return $this->firstname;
	}

	public function getPasswordHash() {
		return $this->passwordhash;
	}

}

?>