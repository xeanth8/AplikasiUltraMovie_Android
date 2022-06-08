<?php

	$server = "sql312.epizy.com";
	$user = "epiz_31909233";
	$pass = "ZEQHJYVZgdEW";
	$db = "epiz_31909233_ultramovie";
	$conn = mysqli_connect($server, $user, $pass, $db);

	if(!$conn)
	{
		die("Connection failed: " . mysqli_connect_error());
	}

?>