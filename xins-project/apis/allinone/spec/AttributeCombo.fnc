<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.4//EN" "http://xins.sourceforge.net/dtd/function_1_4.dtd">

<function name="AttributeCombo"
rcsversion="$Revision: 1.1 $" rcsdate="$Date: 2006/04/05 09:22:18 $">

	<description>A function to test the attribute-combo.</description>
	<input>
		<data>
			<contains>
				<contained element="person" />
			</contains>
			<element name="person">
				<description>A person</description>
				<attribute name="birthDate" required="false" type="_date">
					<description>The birth date.</description>
				</attribute>
				<attribute name="birthYear" required="false" type="_int32">
					<description>The birth date's year.</description>
				</attribute>
				<attribute name="birthMonth" required="false" type="_int32">
					<description>The birth date's month.</description>
				</attribute>
				<attribute name="birthDay" required="false" type="_int32">
					<description>The birth date's day.</description>
				</attribute>
				<attribute name="birthCountry" required="false" type="_text">
					<description>The country where the person is borned.</description>
				</attribute>
				<attribute name="birth-city" required="false" type="_text">
					<description>The city where the person is borned.</description>
				</attribute>
				<attribute name="age" required="false" type="Age">
					<description>An example of input for a int8 type with a minimum and maximum.</description>
				</attribute>
				<!-- One and only one of the three attributeeters must be filled -->
				<attribute-combo type="exclusive-or">
					<attribute-ref name="birthDate" />
					<attribute-ref name="birthYear" />
					<attribute-ref name="age"       />
				</attribute-combo>
				<!-- At least one of the two attributeeters must be filled -->
				<attribute-combo type="inclusive-or">
					<attribute-ref name="birthCountry" />
					<attribute-ref name="birth-city"    />
				</attribute-combo>
				<!-- These attributeeters must be filled together or not filled at all -->
				<attribute-combo type="all-or-none">
					<attribute-ref name="birthYear"  />
					<attribute-ref name="birthMonth" />
					<attribute-ref name="birthDay"   />
				</attribute-combo>
			</element>
		</data>
	</input>

	<output>
		<data>
			<contains>
				<contained element="registration" />
			</contains>
			<element name="registration">
				<description>Registration of a person.</description>
				<attribute name="registration-date" required="false" type="_date">
					<description>The registration date.</description>
				</attribute>
				<attribute name="registrationYear" required="false" type="_int32">
					<description>The registration year.</description>
				</attribute>
				<attribute name="registrationMonth" required="false" type="_int32">
					<description>The registration month.</description>
				</attribute>
				<!-- One of the two attributes must be filled but not both-->
				<attribute-combo type="exclusive-or">
					<attribute-ref name="registration-date" />
					<attribute-ref name="registrationYear" />
				</attribute-combo>
				<!-- These attributes must be filled together or not filled at all -->
				<attribute-combo type="all-or-none">
					<attribute-ref name="registrationYear" />
					<attribute-ref name="registrationMonth" />
				</attribute-combo>
			</element>
		</data>
	</output>

	<example resultcode="_InvalidRequest">
		<description>Invalid attribute.</description>
		<input-data-example>
			<element-example name="person" />
		</input-data-example>
		<output-data-example>
			<element-example name="attribute-combo">
				<attribute-example name="type">inclusive-or</attribute-example>
				<element-example name="attribute">
					<attribute-example name="name">birthCountry</attribute-example>
				</element-example>
				<element-example name="attribute">
					<attribute-example name="name">birth-city</attribute-example>
				</element-example>
			</element-example>
			<element-example name="attribute-combo">
				<attribute-example name="type">exclusive-or</attribute-example>
				<element-example name="attribute">
					<attribute-example name="name">birthDate</attribute-example>
				</element-example>
				<element-example name="attribute">
					<attribute-example name="name">birthYear</attribute-example>
				</element-example>
				<element-example name="attribute">
					<attribute-example name="name">age</attribute-example>
				</element-example>
			</element-example>
		</output-data-example>
	</example>

	<example>
		<description>Valid result.</description>
		<input-data-example>
			<element-example name="person">
				<attribute-example name="birthYear">2006</attribute-example>
				<attribute-example name="birthMonth">8</attribute-example>
				<attribute-example name="birthDay">19</attribute-example>
				<attribute-example name="birthCountry">France</attribute-example>
			</element-example>
		</input-data-example>
		<output-data-example>
			<element-example name="registration">
				<attribute-example name="registration-date">20070801</attribute-example>
			</element-example>
		</output-data-example>
	</example>
</function>
