<?php

	include 'DBConfig.php';

	$table_name = "movie";
	$action  = $_POST['action'];

	$id_movie;
	$nm_movie;
	$kd_writer;
	$director;
	$stars;
	$sinopsis;

	if($action != "read")
	{
		$id_movie = $_POST['id_movie'];
		$nm_movie = $_POST['nm_movie'];
		$kd_writer = $_POST['kd_writer'];
		$director = $_POST['director'];
		$stars = $_POST['stars'];
		$sinopsis = $_POST['sinopsis'];
	}

	switch ($action) {
		case 'read':
			$query = "SELECT m.id_movie, 
			(SELECT GROUP_CONCAT(g.nama_genre ORDER BY g.nama_genre) as GENRES FROM movie_has_genre mhg JOIN genre g on mhg.kd_genre = g.kd_genre WHERE mhg.id_movie = m.id_movie) as 'Genres', 
			(SELECT GROUP_CONCAT(w.nm_writer ORDER BY w.nm_writer) as GENRES FROM movie_has_writers mhs JOIN writers w on mhs.kd_writer = w.kd_writer WHERE mhs.id_movie = m.id_movie) as 'Writers'
			FROM `movie` m";

			$result = mysqli_query($conn, $query);

			if(mysqli_num_rows($result) > 0)
			{
				$items = array();
				while($row = mysqli_fetch_object($result))
				{
					array_push($items, $row);
				}

				$response['error_text'] = "Berhasil";
				$response['data'] = $items;
			}
			else
			{
				$response['error_text'] = "No products found";
			}

			echo json_encode($response);

			break;

		case 'create':
			# code...
			break;

		case 'update':
			# code...
			break;

		case 'delete':
			# code...
			break;
		
		default:
			# code...
			break;
	}

?>