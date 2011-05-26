<?php

class PersistenceController {
	
	private static $instance = null;
	public static $connection = null;
	
	static public function getInstance() {
		if(null === self::$instance) {
			self::$instance = new self;
		}
		return self::$instance;
	}
		
	public function openConnection() {
		if(!isset($this->connection)) {
			$this->connection = pg_connect("host=waktu.phew.ch port=5432 dbname=waktu user=usr_waktu password=waktu!2010");
		}
	}
	
	public function closeConnection() {
		pg_close($this->connection);
		$this->connection = null;
	}
	
}
	
?>