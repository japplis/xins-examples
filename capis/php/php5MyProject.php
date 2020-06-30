<?php

/**
 * This class shows an example of using XINS API's
 * with PHP. This example can be used with PHP 5
 * and requires the simplexml extension.
 *
 * See php5callmyfunction.php for an example on how
 * to use this class.
 *
 * @author Janwillem Borleffs <jw@jwscripts.com>
 */
class MyProject {
	/**
	 * Contains the error code returned by the API.
	 */
	private $errorCode = '';

	/**
	 * Contains an XSL DOM object after the
	 * XML response has been parsed.
	 */
	private $xslObject = null;

	/**
	 * The URL to the XINS API.
	 */
	private $resource = "http://localhost:8080/myproject/";


	/**
	 * Calls the XINS API. Returns <code>true</code>
	 * on success or <code>false</code> on failure.
	 *
	 * @private
	 *
	 * @param $gender
	 *     the gender, either <code>m</code> or <code>f</code>.
	 *
	 * @param $personLastName
	 *     the person's sir name.
	 *
	 * @return boolean
	 */
	private function callMyProjectAPI($gender, $personLastName) {
		// Set the $errorCode property with a default;
		// this will be overwritten when appropriate
		$this->errorCode = 'TechnicalError';

		// Construct the query
		$query = "?_function=MyFunction&gender=$gender&personLastName=" . urlencode($personLastName);

		// Retrieve the response from the remote server
		if (!$xml = @file_get_contents($this->resource . $query)) {
			return false;
		}

		if (!is_object($this->xslObject = @simplexml_load_string($xml))) {
			return false;
		}

		// See if there is an error code
		if ($errorCode = $this->xslObject->xpath("/result/@errorcode")) {
			$this->errorCode = $errorCode[0];
			return false;
		}

		// No errors; clear the $errorCode property and return
		$this->errorCode = '';
		return true;
	}

	/**
	 * Retrieves the last error code.
	 *
	 * @public
	 *
	 * @return string
	 */
	function getErrorCode() {
		return $this->errorCode;
	}
	
	/**
	 * Retrieves the the message or <code>false</code>
	 * on failure.
	 *
	 * @public
	 *
	 * @param $gender
	 *     the gender, either <code>m</code> or <code>f</code>.
	 *
	 * @param $personLastName
	 *     the person's sir name.
	 *
	 * @return string or <code>false</code> on failure.
	 */
	function getMessage($gender, $personLastName) {
		if (!$this->callMyProjectAPI($gender, $personLastName) ||
			!$message = $this->xslObject->xpath("/result/param[@name = 'message']")) {
			return false;
		} else {
			return $message[0];
		}
	}
}

?>