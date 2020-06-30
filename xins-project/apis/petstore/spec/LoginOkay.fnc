<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.4//EN" "http://www.xins.org/dtd/function_1_4.dtd">

<function name="LoginOkay"
rcsversion="$Revision: 1.3 $" rcsdate="$Date: 2006/04/12 09:02:38 $">

	<description>Login page.</description>

	<input>
		<param name="email" required="true" type="EMail">
			<description>The e-mail of the customer.</description>
		</param>
		<param name="password" required="true" type="Password">
			<description>The password of the customer.</description>
		</param>
	</input>
 
	<output>
		<resultcode-ref name="DatabaseFailure" />
		<resultcode-ref name="UnknownLogin" />
		<resultcode-ref name="IncorrectPassword" />
	</output>

	<example resultcode="_InvalidRequest">
		<description>Invalid request.</description>
		<input-example name="email">bla</input-example>
		<output-data-example>
			<element-example name="missing-param">
				<attribute-example name="param">password</attribute-example>
			</element-example>
			<element-example name="invalid-value-for-type">
				<attribute-example name="type">EMail</attribute-example>
				<attribute-example name="param">email</attribute-example>
			</element-example>
		</output-data-example>
	</example>
	<example resultcode="UnknownLogin">
		<description>An invalid user.</description>
		<input-example name="email">hello@hotmail.com</input-example>
		<input-example name="password">MyPass</input-example>
	</example>
	<example resultcode="IncorrectPassword">
		<description>A correct user with an incorrect password.</description>
		<input-example name="email">test@test.com</input-example>
		<input-example name="password">Pass2</input-example>
	</example>
	<example>
		<description>The user successfully logged in.</description>
		<input-example name="email">test@test.com</input-example>
		<input-example name="password">tester</input-example>
	</example>
</function>
