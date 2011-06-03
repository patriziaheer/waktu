<?php

require_once '../../domain/class.Usr.php';

class UsrController {
	
	private static  $instance = null;
	private $con;
	
	public static function getInstance() {
		if(null === self::$instance) {
			self::$instance = new self;
		}
		return self::$instance;
	}
	
	public function __construct() {
		$this->con = PersistenceController::getInstance()->connection;
	}

	public function getAllUsr() {
		
		$query = pg_query($this->con, "SELECT * FROM usr");
		
		$counter = 0;
		
		while($data = pg_fetch_object($query)) {
			$users[$counter] = new Usr($data->id, $data->username, $data->name, $data->firstname, $data->passwordhash);
			$counter++;
		}
		
		return $users;
	}

	public function getUsr($id) {
		$query = pg_query($this->con, "SELECT * FROM usr WHERE id = ".$id);
		$data = pg_fetch_object($query);
		$user = new Usr($data->id, $data->username, $data->name, $data->firstname, $data->passwordhash);
		
		return $user;
	}
	
	
}
