<?php

	include 'DBConfig.php';

	$kd_genre;
	$nama_genre;

	$table_name = "genre";
	$action  = $_POST['action'];

	if($action != "read" && $action != "find" && $action != "delete")
	{
        if(isset($_POST['kd_genre']))
		{
			$kd_genre = $_POST['kd_genre'];
		}
        $nama_genre = $_POST['nama_genre'];
	}

	switch ($action) {

		case 'read':
			
			$result = get_all_genres($conn);

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

			// Masukkan data genre
			$result = input_genre($nama_genre, $conn);

			if($result == 1)
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

			// Update data genre
			$result = update_genre($kd_genre, $nama_genre, $conn);

			if($result == 1)
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
			$kd_genre = (int)$_POST['kd_genre'];

			$result = delete_genre($kd_genre, $conn);

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
			$kd_genre = (int)$_POST['kd_genre'];

			$result = find_genre($kd_genre, $conn);

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

	function get_all_genres($conn)
	{
		$query = "SELECT * FROM genre";

		$result = mysqli_query($conn, $query);

		return $result;
	}

	function input_genre($nama_genre, $conn)
	{
		$query = "INSERT INTO genre (nama_genre) VALUES (?)";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("s", $nama_genre);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

        return $result;
	}

	function update_genre($kd_genre, $nama_genre, $conn)
	{
		$query = "UPDATE genre SET nama_genre = ? WHERE kd_genre = ?";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("sd", $nama_genre, $kd_genre);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

		return $result;
	}

	function delete_genre($kd_genre, $conn)
	{
		$query = "DELETE FROM genre WHERE kd_genre = ?";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("d", $kd_genre);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

		return $result;
	}

	function find_genre($kd_genre, $conn)
	{
		$query = "SELECT * FROM genre WHERE kd_genre = ? ";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("d", $kd_genre);
		$stmt->execute() or die('Error query: ' . $stmt->error);

		$result = $stmt->get_result()->fetch_assoc();

		return $result;
	}

?>