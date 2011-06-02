<?php
	session_name("Waktu");
	session_start();
	session_destroy();
	
	header("Location: index.php");

?>