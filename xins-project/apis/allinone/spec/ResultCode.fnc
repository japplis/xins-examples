<?xml version="1.0" encoding="US-ASCII"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 2.0//EN" "http://xins.sourceforge.net/dtd/function_2_0.dtd">

<function name="ResultCode"
rcsversion="$Revision: 1.6 $" rcsdate="$Date: 2007/06/14 07:40:15 $">

	<description>A function that may return different result codes.</description>

	<input>
		<param name="inputText" required="true" type="_text">
			<description>An example of input for a text.</description>
		</param>
	</input>
	<output>
		<resultcode-ref name="AlreadySet" />
		<resultcode-ref name="myproject/NoVowel" />

		<param name="outputText" required="true" type="_text">
			<description>An example of output for a text.</description>
		</param>
	</output>

	<example resultcode="_InvalidRequest">
		<description>Missing parameter.</description>
		<data-example>
			<element-example name="missing-param">
				<attribute-example name="param">inputText</attribute-example>
			</element-example>
		</data-example>
	</example>
	<example resultcode="AlreadySet">
		<description>The text has already been set.</description>
		<input-example name="inputText">hello</input-example>
		<output-example name="count">1</output-example>
	</example>
	<example resultcode="myproject/NoVowel">
		<description>The text contains no vowels.</description>
		<input-example name="inputText">hsl</input-example>
	</example>
	<example>
		<description>A new text was sent.</description>
		<input-example name="inputText">hello you!</input-example>
		<output-example name="outputText">hello you! added.</output-example>
	</example>

</function>