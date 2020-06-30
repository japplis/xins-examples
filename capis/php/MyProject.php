<?php

/**
 * This class shows an example of using XINS API's
 * with PHP. This example can be used with PHP 4 and
 * up and works with the default XML extension.
 *
 * See callmyfunction.php for an example on how
 * to use this class.
 *
 * @author Janwillem Borleffs <jw@jwscripts.com>
 */
class MyProject {
	/**
	 * Contains the error code returned by the API.
	 */
	var $errorCode = '';

	/**
	 * Contains an array of values after the
	 * XML response has been parsed.
	 */
	var $xml_values;

	/**
	 * The URL to the XINS API.
	 */
	var $resource = "http://localhost:8080/myproject/";


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
	function callMyProjectAPI($gender, $personLastName) {
		// Set the $errorCode property with a default;
		// this will be overwritten when appropriate
		$this->errorCode = 'TechnicalError';

		// Construct the query
		$query = "?_function=MyFunction&gender=$gender&personLastName=" . urlencode($personLastName);

		// Retrieve the response from the remote server
		if (!$xml = @file_get_contents($this->resource . $query)) {
			return false;
		}

		if (!$this->xml_convert($xml)) {
			return false;
		}

		// See if there is an error code
		if (isset($this->xml_values[0]['attributes']) &&
			isset($this->xml_values[0]['attributes']['errorcode'])) {
			$this->errorCode = $this->xml_values[0]['attributes']['errorcode'];
			return false;
		} else if (!isset($this->xml_values[1]) ||
				   $this->xml_values[1]['tag'] != 'param' ||
				   !isset($this->xml_values[1]['value'])) {
			return false;
		}

		// No errors; clear the $errorCode property and return
		$this->errorCode = '';
		return true;
	}

	/**
	 * Converts the XML document into an associative array
	 *
	 * @private
	 *
	 * @param $xml
	 *     the source XML.
	 *
	 * @return boolean
	 */
	function xml_convert($xml) {
		if ($p = xml_parser_create()) {
			// Preserve the element's case
			if (!xml_parser_set_option ($p, XML_OPTION_CASE_FOLDING, 0)) {
				xml_parser_free($p);
				return false;
			}
			if (!$xml_parsed = @xml_parse_into_struct($p, $xml, $this->xml_values)) {
				xml_parser_free($p);
				return false;
			}
			return $xml_parsed;
		} else {
			return false;
		}
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
		if (!$this->callMyProjectAPI($gender, $personLastName)) {
			return false;
		} else {
			return $this->xml_values[1]['value'];
		}
	}
}

?>