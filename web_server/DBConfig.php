<?php

	$server = "localhost";
	$user = "id19075684_root";
	$pass = "!R#{FV2*#i0bP2mC";
	$db = "id19075684_ultramovie";
	$conn = mysqli_connect($server, $user, $pass, $db);

	if(!$conn)
	{
		die("Connection failed: " . mysqli_connect_error());
	}

?>
