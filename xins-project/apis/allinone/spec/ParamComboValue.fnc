<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.5//EN" "http://xins.sourceforge.net/dtd/function_1_5.dtd">

<function name="ParamComboValue"
rcsversion="$Revision: 1.1 $" rcsdate="$Date: 2006/10/27 08:48:43 $">

	<description>A function to test the param-combo based on a parameter value.</description>
	<input>
		<param name="salutation" required="true" type="Salutation">
			<description>The gender of the person.</description>
		</param>
		<param name="maidenName" required="false" type="_text">
			<description>The maiden name.</description>
		</param>
		<param name="surname" required="false" type="_text">
			<description>The name of the person.</description>
		</param>
		<param name="age" required="false" type="_int32">
			<description>The age of the person.</description>
		</param>
		<param name="country" required="false" type="_text">
			<description>The country from which this person comes from.</description>
		</param>
		<param name="nationality" required="false" type="_text">
			<description>The nationality of the person.</description>
		</param>
		<param name="passportNumber" required="false" type="_text">
			<description>The passport number of the person.</description>
		</param>
		<param name="passportValidityDate" required="false" type="_int32">
			<description>The expiration year of the passport.</description>
		</param>

		<!-- If the salutation is Madam, the maiden name is required -->
		<param-combo type="inclusive-or">
			<param-ref name="salutation" value="Madam" />
			<param-ref name="maidenName" />
		</param-combo>

		<!-- If the country is Canada, the nationality should not be filled, otherwise the nationality should be filled -->
		<param-combo type="exclusive-or">
			<param-ref name="country" value="Canada" />
			<param-ref name="nationality" />
		</param-combo>

		<!-- Passport number and validity date are required if country is Other. If country is not Other, the passport number and the validity date should not be set. -->
		<param-combo type="all-or-none">
			<param-ref name="country" value="Other" />
			<param-ref name="passportNumber" />
			<param-ref name="passportValidityDate" />
		</param-combo>

		<!-- If the salutation is Miss, the age should not be set -->
		<param-combo type="not-all">
			<param-ref name="salutation" value="Miss" />
			<param-ref name="age" />
		</param-combo>
	</input>

	<output>
	</output>

	<example resultcode="_InvalidRequest">
		<description>Empty request.</description>
		<output-data-example>
			<element-example name="missing-param">
				<attribute-example name="param">salutation</attribute-example>
			</element-example>
			<element-example name="param-combo">
				<attribute-example name="type">exclusive-or</attribute-example>
				<element-example name="param">
					<attribute-example name="name">country</attribute-example>
				</element-example>
				<element-example name="param">
					<attribute-example name="name">nationality</attribute-example>
				</element-example>
			</element-example>
		</output-data-example>
	</example>

	<example resultcode="_InvalidRequest">
		<description>Inclusive-or invalid request.</description>
		<input-example name="salutation">Madam</input-example>
		<input-example name="surname">Doe</input-example>
		<input-example name="nationality">French</input-example>
		<input-example name="country">France</input-example>
		<output-data-example>
			<element-example name="param-combo">
				<attribute-example name="type">inclusive-or</attribute-example>
				<element-example name="param">
					<attribute-example name="name">salutation</attribute-example>
				</element-example>
				<element-example name="param">
					<attribute-example name="name">maidenName</attribute-example>
				</element-example>
			</element-example>
		</output-data-example>
	</example>

	<example resultcode="_InvalidRequest">
		<description>Exclusive-or invalid request.</description>
		<input-example name="salutation">Madam</input-example>
		<input-example name="surname">Doe</input-example>
		<input-example name="maidenName">Martin</input-example>
		<input-example name="nationality">French</input-example>
		<input-example name="country">Canada</input-example>
		<output-data-example>
			<element-example name="param-combo">
				<attribute-example name="type">exclusive-or</attribute-example>
				<element-example name="param">
					<attribute-example name="name">country</attribute-example>
				</element-example>
				<element-example name="param">
					<attribute-example name="name">nationality</attribute-example>
				</element-example>
			</element-example>
		</output-data-example>
	</example>

	<example resultcode="_InvalidRequest">
		<description>all-or-none invalid request.</description>
		<input-example name="salutation">Mister</input-example>
		<input-example name="surname">Doe</input-example>
		<input-example name="nationality">French</input-example>
		<input-example name="country">Other</input-example>
		<output-data-example>
			<element-example name="param-combo">
				<attribute-example name="type">all-or-none</attribute-example>
				<element-example name="param">
					<attribute-example name="name">country</attribute-example>
				</element-example>
				<element-example name="param">
					<attribute-example name="name">passportNumber</attribute-example>
				</element-example>
				<element-example name="param">
					<attribute-example name="name">passportValidityDate</attribute-example>
				</element-example>
			</element-example>
		</output-data-example>
	</example>

	<example resultcode="_InvalidRequest">
		<description>Not-all invalid request.</description>
		<input-example name="salutation">Miss</input-example>
		<input-example name="surname">Lee</input-example>
		<input-example name="maidenName">Martin</input-example>
		<input-example name="age">25</input-example>
		<input-example name="nationality">French</input-example>
		<input-example name="country">France</input-example>
		<output-data-example>
			<element-example name="param-combo">
				<attribute-example name="type">not-all</attribute-example>
				<element-example name="param">
					<attribute-example name="name">salutation</attribute-example>
				</element-example>
				<element-example name="param">
					<attribute-example name="name">age</attribute-example>
				</element-example>
			</element-example>
		</output-data-example>
	</example>

	<example>
		<description>Succesful request.</description>
		<input-example name="salutation">Miss</input-example>
		<input-example name="passportNumber">123ID558</input-example>
		<input-example name="passportValidityDate">2010</input-example>
		<input-example name="surname">Lee</input-example>
		<input-example name="nationality">English</input-example>
		<input-example name="country">Other</input-example>
	</example>
</function>
