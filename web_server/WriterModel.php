<?php

	include 'DBConfig.php';

	$kd_writer;
	$nm_writer;
	$email;
	$telepon;

	$table_name = "writers";
	$action  = $_POST['action'];

	if($action != "read" && $action != "find" && $action != "delete")
	{
        if(isset($_POST['kd_writer']))
		{
			$kd_writer = $_POST['kd_writer'];
		}
        $nm_writer = $_POST['nm_writer'];
        $email = $_POST['email'];
        $telepon = $_POST['telepon'];
	}

	switch ($action) {

		case 'read':
			
			$result = get_all_writers($conn);

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

			// Masukkan data writer
			$result = input_writer($nm_writer, $email, $telepon, $conn);

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

			// Update data writer
			$result = update_writer($kd_writer, $nm_writer, $email, $telepon, $conn);

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
			$kd_writer = (int)$_POST['kd_writer'];

			$result = delete_writer($kd_writer, $conn);

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
			$kd_writer = (int)$_POST['kd_writer'];

			$result = find_writer($kd_writer, $conn);

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

	function get_all_writers($conn)
	{
		$query = "SELECT * FROM writers";

		$result = mysqli_query($conn, $query);

		return $result;
	}

	function input_writer($nm_writer, $email, $telepon, $conn)
	{
		$query = "INSERT INTO writers (nm_writer, email, telepon) VALUES (?, ?, ?)";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("sss", $nm_writer, $email, $telepon);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

        return $result;
	}

	function update_writer($kd_writer, $nm_writer, $email, $telepon, $conn)
	{
		$query = "UPDATE writers SET nm_writer = ?, email = ?, telepon = ? WHERE kd_writer = ?";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("sssd", $nm_writer, $email, $telepon, $kd_writer);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

		return $result;
	}

	function delete_writer($kd_writer, $conn)
	{
		$query = "DELETE FROM writers WHERE kd_writer = ?";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("d", $kd_writer);
		$result = $stmt->execute() or die('Error query: ' . $stmt->error);

		return $result;
	}

	function find_writer($kd_writer, $conn)
	{
		$query = "SELECT * FROM writers WHERE kd_writer = ? ";
		$stmt = $conn->prepare($query);
		$stmt->bind_param("d", $kd_writer);
		$stmt->execute() or die('Error query: ' . $stmt->error);

		$result = $stmt->get_result()->fetch_assoc();

		return $result;
	}

?>