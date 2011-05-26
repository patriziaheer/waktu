<?php

class LoginController {
	
	private static $instance = null;
	private static $loggedInUser = null;
	
	static public function getInstance() {
		if(null === self::$instance) {
			self::$instance = new self;
		}
		return self::$instance;
	}
	
	private function __construct(){}
	private function __clone(){}
	
	public function login($user, $password) {
		$uc = new UsrController();
		$users = $uc->getAllUsr();
		foreach($users as $key=>$value) {
			if($value->getUsername() == $user && $value->getPasswordHash() == md5($password)) {
				$usr = new Usr($value->getUsrId(), $value->getUsername(), $value->getName(), $value->getFirstname(), $value->getPasswordHash());
				$this->loggedInUser = $usr;
				session_start();
				$_SESSION['user'] = $usr;
				return true;
			} 
		}
		return false;
	}
	
	public function getLoggedInUser() {
		return $this->loggedInUser;
	}
	
	public function setLoggedInUser($user) {
		$this->loggedInUser = $user;
		return true;
	}
}

?>