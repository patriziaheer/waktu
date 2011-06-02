<?php

class TimeUtil {

	public static function convertHumanToSqlDate($humanDate) {
		$sqlDate = substr($humanDate, 8,2).substr($humanDate, 5,2).substr($humanDate,0,4);
		return $sqlDate;
	}

}

?>