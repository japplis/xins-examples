<?xml version="1.0" encoding="US-ASCII"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.1//EN" "http://xins.sourceforge.net/dtd/function_1_1.dtd">

<function name="MyFunction"
rcsversion="$Revision: 1.8 $" rcsdate="$Date: 2006/10/20 14:36:19 $">

	<description>A simple function that return a "hello" message to the person.</description>

	<input>
		<param name="gender" required="true" type="Gender">
			<description>The gender of the person.</description>
		</param>
		<param name="personLastName" required="true" type="LastName">
			<description>The last name of the person.</description>
		</param>
	</input>
	<output>
		<resultcode-ref name="NoVowel" />
		<param name="message" required="true" type="_text">
			<description>The message returned to this person.</description>
		</param>
	</output>

	<!-- Some examples for this function -->
	<example resultcode="_InvalidRequest">
		<description>Missing parameter : lastName</description>
		<input-example name="personLastName">Bond 007</input-example>
		<output-data-example>
			<element-example name="missing-param">
				<attribute-example name="param">gender</attribute-example>
			</element-example>
			<element-example name="invalid-value-for-type">
				<attribute-example name="type">LastName</attribute-example>
				<attribute-example name="param">personLastName</attribute-example>
			</element-example>
		</output-data-example>
	</example>
	<example resultcode="NoVowel">
		<description>The name does not contain any vowels.</description>
		<input-example name="gender">f</input-example>
		<input-example name="personLastName">cqfd</input-example>
	</example>
	<example>
		<description>Message returned.</description>
		<input-example name="gender">f</input-example>
		<input-example name="personLastName">Lee</input-example>
		<output-example name="message">Hello Miss Lee</output-example>
	</example>

</function>
