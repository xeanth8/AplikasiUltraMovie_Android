<?php

	include 'DBConfig.php';

	$id_movie;
	$nm_movie;
	$genres;
	$writers;
	$director;
	$stars;
	$sinopsis;

	$table_name = "movie";
	$action  = $_POST['action'];

	if($action != "read" && $action != "create")
	{
		$id_movie = $_POST['id_movie'];
		$nm_movie = $_POST['nm_movie'];
		$genres = str_replace(" ", "",$_POST['genres']);
		$writers = $_POST['writers'];
		$director = $_POST['director'];
		$stars = $_POST['stars'];
		$sinopsis = $_POST['sinopsis'];
	}

	switch ($action) {
		case 'read':
			$query = 
			"SELECT m.id_movie, 
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

			mysqli_close($conn);
			break;

		case 'create':

			// Masukkan data genre bila ada yang baru terlebih dahulu
			input_genres($genres, $conn);

			// Masukkan data film sebelum data movie_has_genre
			$query = 
				"INSERT INTO movie (nm_movie, director, stars, sinopsis) VALUES (?, ?, ?, ?)";
				$stmt = $conn->prepare($query);
				$stmt->bind_param("ssss", $nm_movie, $director, $stars, $sinopsis);
				$result = $stmt->execute() or die('Error query: ' . $query);

			// Masukkan data movie_has_genre
			input_movie_has_genre($genres, $nm_movie, $conn);

			if($result == 1)
			{
				$response['error_text'] = "Success";
				echo json_encode($response);
			}
			else
			{
				$response['error_text'] = "Fail";
				echo json_encode($response);
			}
			mysqli_close($conn);
			
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

	function input_genres($genres, $conn)
	{
		$genre_array = explode(",", $genres);
		

		// Validasi Genre Baru
		foreach ($genre_array as $key => $value) {
			$query = "SELECT * FROM genre WHERE nama_genre = ?";
			$stmt = $conn->prepare($query);
			$stmt->bind_param("s", $value);
			$stmt->execute() or die('Error query: ' . $query);

			$result = $stmt->get_result();

			if($result->fetch_assoc() != null)
			{
				unset($genre_array[$key]);
			}
		}

		// Jika ada Genre Baru, Masukkan!
		if($genre_array)
		{
			foreach ($genre_array as $key => $value) {
				$query = "INSERT INTO genre (nama_genre) VALUES (?)";
				$stmt = $conn->prepare($query);
				$stmt->bind_param("s", $value);
				$result = $stmt->execute() or die('Error query: ' . $query);
			}
		}

	}

	function input_movie_has_genre($genres, $nm_movie, $conn)
	{
		$genre_array = explode(",", $genres);
		
		foreach ($genre_array as $key => $value) {
			$query = 
				"INSERT INTO movie_has_genre (id_movie, kd_genre) 
					SELECT m.id_movie, g.kd_genre 
					FROM movie as m CROSS JOIN genre as g 
					WHERE m.nm_movie = ? AND g.nama_genre = ?";
				$stmt = $conn->prepare($query);
				$stmt->bind_param("ss", $nm_movie, $value);
				$result = $stmt->execute() or die('Error query: ' . $query);
		}

	}

?>