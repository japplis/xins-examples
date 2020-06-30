<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.4//EN" "http://www.xins.org/dtd/function_1_4.dtd">

<function name="SearchPetOkay"
rcsversion="$Revision: 1.2 $" rcsdate="$Date: 2006/04/12 09:02:38 $">

	<description>Get the list of pets starting with the typed letters.</description>

	<input>
		<param name="petName" required="false" type="_text">
			<description>Name of the pet to search for or the first letters of its name.</description>
		</param>
	</input>

	<output>
		<resultcode-ref name="NotLoggedIn" />
		<resultcode-ref name="DatabaseFailure" />
		<data>
			<contains>
				<contained element="Pet" />
			</contains>
			<element name="Pet">
				<description>A pet.</description>
				<attribute name="petID" type="_int32">
					<description>Pet identifier.</description>
				</attribute>
				<attribute name="petName" type="_text">
					<description>Pet identifier.</description>
				</attribute>
				<attribute name="price" type="_float32">
					<description>Price of the pet.</description>
				</attribute>
				<attribute name="age" type="Age">
					<description>Age of the pet</description>
				</attribute>
			</element>
		</data>
	</output>
	<example>
		<description>Search for an pet starting with the letters &quot;po&quot;</description>
		<input-example name="petName">do</input-example>
		<output-data-example>
			<element-example name="Pet">
				<attribute-example name="age">5</attribute-example>
				<attribute-example name="price">12000.0</attribute-example>
				<attribute-example name="petName">dolphin</attribute-example>
				<attribute-example name="petID">3</attribute-example>
			</element-example>
			<element-example name="Pet">
				<attribute-example name="age">1</attribute-example>
				<attribute-example name="price">100.0</attribute-example>
				<attribute-example name="petName">dog</attribute-example>
				<attribute-example name="petID">11</attribute-example>
			</element-example>
		</output-data-example>
	</example>
	<example>
		<description>Searching for a rabbit.</description>
		<input-example name="petName">rabbit</input-example>
		<output-data-example>
			<element-example name="Pet">
				<attribute-example name="age">0</attribute-example>
				<attribute-example name="price">20.0</attribute-example>
				<attribute-example name="petName">rabbit</attribute-example>
				<attribute-example name="petID">9</attribute-example>
			</element-example>
		</output-data-example>
	</example>
</function>
