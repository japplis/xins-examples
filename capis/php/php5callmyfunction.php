<?php

require 'php5MyProject.php';
$myProject = new MyProject;

$message = 'Please select your gender and enter your last name.';
$personLastName = "";

if (isset($_GET['submit'])) {
	$personLastName = $_GET['personLastName'];
	if (!$message = $myProject->getMessage($_GET['gender'], $personLastName)) {
		$message = '<span style="color:red">The call returned the following error code: ' . $myProject->getErrorCode() . '</span>';
	}
}

?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
	<head>
		<title> MyProject Class Usage Example </title>
	</head>

	<body>
		<span style="border: 1px blue dotted; padding: 10px 10px 10px 10px">
			<?php echo $message ?>
		</span>
		<form method="get" action="<?php echo $_SERVER['PHP_SELF'] ?>">
			<table border="0" cellpadding="3" cellspacing="0">
				<tr>
					<td>Gender:</td>
					<td>
						<select name="gender">
							<option value="m">Male</option>
							<option value="f">Female</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Last name:</td>
					<td><input type="text" name="personLastName" size="15" value="<?php print $personLastName ?>" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" name="submit" value="Submit" /></td>
				</tr>
			</table>
		</form>
	</body>
</html>
