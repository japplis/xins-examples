<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.4//EN" "http://www.xins.org/dtd/function_1_4.dtd">

<function name="RegisterCustomerOkay"
rcsversion="$Revision: 1.5 $" rcsdate="$Date: 2006/04/12 09:02:38 $">

	<description>Register a new client.</description>

	<input>
		<param name="salutation" required="true" type="Salutation">
			<description>The salutation of the customer.</description>
		</param>
		<param name="firstName" required="true" type="FirstName">
			<description>The first name of the customer.</description>
		</param>
		<param name="lastName" required="true" type="Surname">
			<description>The last name of the customer.</description>
		</param>
		<param name="address" required="true" type="_text">
			<description>The address of the customer.</description>
		</param>
		<param name="phoneNumber" required="false" type="PhoneNumber">
			<description>The phone number of the customer.</description>
		</param>
		<param name="email" required="true" type="EMail">
			<description>The e-mail of the customer.</description>
		</param>
		<param name="password" required="true" type="_text">
			<description>The password of the customer.</description>
		</param>
	</input>

	<output>
		<resultcode-ref name="AlreadyRegistered" />
		<resultcode-ref name="DatabaseFailure" />
	</output>

	<example resultcode="_InvalidRequest">
		<description>An invalid request</description>
		<input-example name="salutation">Mister</input-example>
		<input-example name="firstName">Mike</input-example>
		<input-example name="lastName">Doe</input-example>
		<input-example name="phoneNumber">0625</input-example>
		<input-example name="email">bla</input-example>
		<output-data-example>
			<element-example name="missing-param">
				<attribute-example name="param">address</attribute-example>
			</element-example>
			<element-example name="missing-param">
				<attribute-example name="param">password</attribute-example>
			</element-example>
			<element-example name="invalid-value-for-type">
				<attribute-example name="type">PhoneNumber</attribute-example>
				<attribute-example name="param">phoneNumber</attribute-example>
			</element-example>
			<element-example name="invalid-value-for-type">
				<attribute-example name="type">EMail</attribute-example>
				<attribute-example name="param">email</attribute-example>
			</element-example>
		</output-data-example>
	</example>
	<example resultcode="AlreadyRegistered">
		<description>The user is already registered.</description>
		<input-example name="salutation">Mister</input-example>
		<input-example name="firstName">Mike</input-example>
		<input-example name="lastName">Doe</input-example>
		<input-example name="address">Amsterdam</input-example>
		<input-example name="email">test@test.com</input-example>
		<input-example name="password">Hello</input-example>
	</example>
	<example>
		<description>The customer successfully registered.</description>
		<input-example name="salutation">Mister</input-example>
		<input-example name="firstName">Mike</input-example>
		<input-example name="lastName">Doe</input-example>
		<input-example name="address">Amsterdam</input-example>
		<input-example name="email">mike@hotmail.com</input-example>
		<input-example name="password">Hello</input-example>
	</example>
</function>
