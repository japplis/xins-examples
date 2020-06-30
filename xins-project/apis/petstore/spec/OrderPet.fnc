<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.4//EN" "http://www.xins.org/dtd/function_1_4.dtd">

<function name="OrderPet"
rcsversion="$Revision: 1.7 $" rcsdate="$Date: 2006/04/12 09:02:38 $">

	<description>Order a pet.</description>

	<input>
		<param name="petID" required="true" type="_int32">
			<description>Pet identifier.</description>
		</param>
		<param name="quantity" required="false" type="_int32">
			<description>The number of pets the customer wants to buy.
			If no quantity is passed, one pet is ordered.
			</description>
		</param>
	</input>
	<output>
		<resultcode-ref name="NotLoggedIn" />
		<resultcode-ref name="DatabaseFailure" />
		<resultcode-ref name="ProductNotAvailable" />
	</output>
	<example resultcode="_InvalidRequest">
		<description>An invalid request.</description>
		<input-example name="quantity">a dozen</input-example>
		<output-data-example>
			<element-example name="missing-param">
				<attribute-example name="param">petID</attribute-example>
			</element-example>
			<element-example name="invalid-value-for-type">
				<attribute-example name="type">_int32</attribute-example>
				<attribute-example name="param">quantity</attribute-example>
			</element-example>
		</output-data-example>
	</example>
	<example resultcode="NotLoggedIn">
		<description>The user is not logged in.</description>
		<input-example name="petID">2</input-example>
		<input-example name="quantity">3</input-example>
	</example>
	<example>
		<description>The user is logged in and can order the pet.</description>
		<input-example name="petID">3</input-example>
		<input-example name="quantity">2</input-example>
	</example>
</function>
