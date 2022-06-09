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

	if($action != "read" && $action != "find" && $action != "delete")
	{
		$id_movie = (int)$_POST['id_movie'];
		$nm_movie = $_POST['nm_movie'];
		$genres = str_replace(", ", ",",$_POST['genres']);
		$writers = str_replace(", ", ",",$_POST['writers']);
		$director = $_POST['director'];
		$stars = $_POST['stars'];
		$sinopsis = $_POST['sinopsis'];
	}

	switch ($action) {

		case 'read':
			
			$result = get_all_movies($conn);

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

			// Masukkan data writers bila ada yang baru terlebih dahulu
			input_writers($writers, $conn);

			// Masukkan data film sebelum data movie_has_genre
			input_movie($nm_movie, $director, $stars, $sinopsis, $conn);

			// Masukkan data movie_has_genre
			$result1 = input_movie_has_genre($genres, $nm_movie, $conn);

			// Masukkan data movie_has_writers
			$result2 = input_movie_has_writers($writers, $nm_movie, $conn);

			if($result1 == 1 && $result2 == 1)
			{
				$response['error_text'] = "Success";
			}
			else
			{
				$response['error_text'] = "Fail";
			}

			echo json_encode($response);

			mysqli_close($conn);
			
			break;

		case 'update':

			// Masukkan data genre bila ada yang baru terlebih dahulu
			input_genres($genres, $conn);

			// Hapus seluruh data genre movie lama
			delete_movie_has_genre($id_movie, $conn);

			// Masukkan data writers bila ada yang baru terlebih dahulu
			input_writers($writers, $conn);

			// Hapus seluruh data writers movie lama
			delete_movie_has_writers($id_movie, $conn);

			// Input kembali data genre movie baru
			$result1 = update_movie_has_genre($genres, $id_movie, $conn);

			// Input kembali data writers movie baru
			$result2 = update_movie_has_writers($writers, $id_movie, $conn);

			// Update data movie
			$result3 = update_movie($id_movie, $nm_movie, $director, $stars, $sinopsis, $conn);

			if($result1 == 1 && $result2 == 1 && $result3 == 1)
			{
				$response['error_text'] = "Success";
			}
			else
			{
				$response['error_text'] = "Fail";
			}

			echo json_encode($response);

			mysqli_close($conn);

			break;

		case 'delete':
			$id_movie = (int)$_POST['id_movie'];

			$result = delete_movie($id_movie, $conn);

			if($result != null)
			{
				$response['error_text'] = "Success";
			}
			else
			{
				$response['error_text'] = "Fail";
			}

			echo json_encode($response);

			mysqli_close($conn);

			break;
		
		case 'find':
			$id_movie = (int)$_POST['id_movie'];

			$result = find_movie($id_movie, $conn);

			if($result != null)
			{
				$response['error_text'] = "Success";
				$response['data'] = $result;
				
			}
			else
			{
				$response['error_text'] = "Fail";
			}

			echo json_encode($response);

			mysqli_close($conn);

			break;
	}

	function get_all_movies($conn)
	{
		$query = 
			"SELECT m.id_movie, m.nm_movie, m.director, m.stars, m.sinopsis,
				(SELECT GROUP_CONCAT(g.nama_genre ORDER BY g.nama_genre) as GENRES FROM movie_has_genre mhg JOIN genre g on mhg.kd_genre = g.kd_genre WHERE mhg.id_movie = m.id_movie) as 'Genres', 
				(SELECT GROUP_CONCAT(w.nm_writer ORDER BY w.nm_writer) as GENRES FROM movie_has_writers mhs JOIN writers w on mhs.kd_writer = w.kd_writer WHERE mhs.id_movie = m.id_movie) as 'Writers'
			FROM `movie` m";

		$result = mysqli_query($conn, $query);

		return $result;
	}

	function input_movie($nm_movie, $director, $stars, $sinopsis, $conn)
	{
		$query = "INSERT INTO movie (nm_movie, director, stars, sinopsis) VALUES (?, ?, ?, ?)";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("ssss", $nm_movie, $director, $stars, $sinopsis);
		$stmt->execute() or die('Error query: ' . $query);
	}

	function update_movie($id_movie, $nm_movie, $director, $stars, $sinopsis, $conn)
	{
		$query = "UPDATE movie SET nm_movie = ?, director = ?, stars = ?, sinopsis = ? WHERE id_movie = ?";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("ssssd", $nm_movie, $director, $stars, $sinopsis, $id_movie);
		$result = $stmt->execute() or die('Error query: ' . $query);

		return $result;
	}

	function delete_movie($id_movie, $conn)
	{
		$query = "DELETE FROM movie WHERE id_movie = ?";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("d", $id_movie);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

		return $result;
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

			$is_exist = $stmt->get_result();

			if($is_exist->fetch_assoc() != null)
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

		$result;
		
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

		return $result;
	}

	function update_movie_has_genre($genres, $id_movie, $conn)
	{
		$genre_array = explode(",", $genres);

		$result;
		
		foreach ($genre_array as $key => $value) {
			$query = 
				"INSERT INTO movie_has_genre (id_movie, kd_genre) 
					SELECT m.id_movie, g.kd_genre 
					FROM movie as m CROSS JOIN genre as g 
					WHERE m.id_movie = ? AND g.nama_genre = ?";
				$stmt = $conn->prepare($query);
				$stmt->bind_param("ds", $id_movie, $value);
				$result = $stmt->execute() or die('Error query: ' . $stmt->error);
		}

		return $result;
	}

	function delete_movie_has_genre($id_movie, $conn)
	{
		$query = "SELECT kd_genre FROM movie_has_genre WHERE id_movie = ?";	
		$stmt = $conn->prepare($query);
		$stmt->bind_param("d", $id_movie);
		$stmt->execute() or die('Error query: ' . $query);
		$genre_list = $stmt->get_result()->fetch_all();

		foreach ($genre_list as $key => $value) {
			$query = "DELETE FROM movie_has_genre WHERE id_movie = ?";
			$stmt = $conn->prepare($query);
			$stmt->bind_param("d", $id_movie);
			$result = $stmt->execute() or die('Error query: ' . $stmt->error);
		}
	}

	function input_writers($writers, $conn)
	{
		$writer_array = explode(",", $writers);

		// Validasi writer Baru
		foreach ($writer_array as $key => $value) {
			$query = "SELECT * FROM writers WHERE nm_writer = ?";
			$stmt = $conn->prepare($query);
			$stmt->bind_param("s", $value);
			$stmt->execute() or die('Error query: ' . $query);

			$is_exist = $stmt->get_result();

			if($is_exist->fetch_assoc() != null)
			{
				unset($writer_array[$key]);
			}
		}

		// Jika ada writer Baru, Masukkan!
		if($writer_array)
		{
			foreach ($writer_array as $key => $value) {
				$query = "INSERT INTO writers (nm_writer) VALUES (?)";
				$stmt = $conn->prepare($query);
				$stmt->bind_param("s", $value);
				$result = $stmt->execute() or die('Error query: ' . $query);
			}
		}
	}

	function input_movie_has_writers($writers, $nm_movie, $conn)
	{
		$writer_array = explode(",", $writers);

		$result;
		
		foreach ($writer_array as $key => $value) {
			$query = 
				"INSERT INTO movie_has_writers (id_movie, kd_writer) 
					SELECT m.id_movie, w.kd_writer 
					FROM movie as m CROSS JOIN writers AS w 
					WHERE m.nm_movie = ? AND w.nm_writer = ?";
				$stmt = $conn->prepare($query);
				$stmt->bind_param("ss", $nm_movie, $value);
				$result = $stmt->execute() or die('Error query: ' . $query);
		}

		return $result;
	}

	function update_movie_has_writers($writers, $id_movie, $conn)
	{
		$writer_array = explode(",", $writers);

		$result;
		
		foreach ($writer_array as $key => $value) {
			$query = 
				"INSERT INTO movie_has_writers (id_movie, kd_writer) 
					SELECT m.id_movie, w.kd_writer 
					FROM movie as m CROSS JOIN writers as w 
					WHERE m.id_movie = ? AND w.nm_writer = ?";
				$stmt = $conn->prepare($query);
				$stmt->bind_param("ds", $id_movie, $value);
				$result = $stmt->execute() or die('Error query: ' . $stmt->error);
		}

		return $result;
	}

	function delete_movie_has_writers($id_movie, $conn)
	{
		$query = "SELECT kd_writer FROM movie_has_writers WHERE id_movie = ?";	
		$stmt = $conn->prepare($query);
		$stmt->bind_param("d", $id_movie);
		$stmt->execute() or die('Error query: ' . $query);
		$writer_list = $stmt->get_result()->fetch_all();

		foreach ($writer_list as $key => $value) {
			$query = "DELETE FROM movie_has_writers WHERE id_movie = ?";
			$stmt = $conn->prepare($query);
			$stmt->bind_param("d", $id_movie);
			$result = $stmt->execute() or die('Error query: ' . $stmt->error);
		}
	}

	function find_movie($id_movie, $conn)
	{
		$query = 
			"SELECT m.id_movie, m.nm_movie, m.director, m.stars, m.sinopsis,
				(SELECT GROUP_CONCAT(g.nama_genre ORDER BY g.nama_genre) as GENRES FROM movie_has_genre mhg JOIN genre g on mhg.kd_genre = g.kd_genre WHERE mhg.id_movie = m.id_movie) as 'Genres', 
				(SELECT GROUP_CONCAT(w.nm_writer ORDER BY w.nm_writer) as GENRES FROM movie_has_writers mhs JOIN writers w on mhs.kd_writer = w.kd_writer WHERE mhs.id_movie = m.id_movie) as 'Writers'
			FROM `movie` m WHERE m.id_movie = ?";

		$stmt = $conn->prepare($query);
		$stmt->bind_param("d", $id_movie);
		$stmt->execute() or die('Error query: ' . $query);

		$result = $stmt->get_result()->fetch_assoc();

		return $result;
	}

?>