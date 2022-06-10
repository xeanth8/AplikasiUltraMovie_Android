<?php

	include 'DBConfig.php';

	$username;
	$password;
    $level;
    $nama;
    $jenis_kelamin;
    $asal_negara;

	$table_name = "user";
	$action  = $_POST['action'];

	if($action != "read" && $action != "find" && $action != "delete")
	{
        if(isset($_POST['username']))
		{
			$username = $_POST['username'];
		}
        $password = $_POST['password'];
        $level = $_POST['level'];
        $nama = $_POST['nama'];
        $jenis_kelamin = $_POST['jenis_kelamin'];
        $asal_negara = $_POST['asal_negara'];
	}

	switch ($action) {

		case 'read':
			
			$result = get_all_users($conn);

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
				$response['error_text'] = "No users found";
			}

			echo json_encode($response);

			mysqli_close($conn);

			break;

		case 'create':

			// Register
			$result = input_user($username, $password, $level, $nama, $jenis_kelamin, $asal_negara, $conn);

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

			// Update data user
			$result = update_user($username, $password, $level, $nama, $jenis_kelamin, $asal_negara, $conn);

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
			$username = $_POST['username'];

			$result = delete_user($username, $conn);

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
            
            // Login
			$username = $_POST['username'];
            $password = $_POST['password'];

			$result = find_user($username, $password, $conn);

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

	function get_all_users($conn)
	{
		$query = "SELECT * FROM user";

		$result = mysqli_query($conn, $query);

		return $result;
	}

	function input_user($username, $password, $level, $nama, $jenis_kelamin, $asal_negara, $conn)
	{
		$query = "INSERT INTO user (username, password, level, nama, jenis_kelamin, asal_negara) VALUES (?, ?, ?, ?, ?, ?)";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("ssssss", $username, $password, $level, $nama, $jenis_kelamin, $asal_negara);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

        return $result;
	}

	function update_user($username, $password, $level, $nama, $jenis_kelamin, $asal_negara, $conn)
	{
		$query = "UPDATE user SET password = ?, level = ?, nama = ?, jenis_kelamin = ?, asal_negara = ? WHERE username = ?";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("ssssss", $password, $level, $nama, $jenis_kelamin, $asal_negara, $username);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

		return $result;
	}

	function delete_user($username, $conn)
	{
		$query = "DELETE FROM user WHERE username = ?";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("s", $username);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

		return $result;
	}

	function find_user($username, $password, $conn)
	{
		$query = "SELECT * FROM user WHERE username = ? AND password = ?";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("ss", $username, $password);
		$stmt->execute() or die('Error query: ' . $stmt->error);

		$result = $stmt->get_result()->fetch_assoc();

		return $result;
	}

?>