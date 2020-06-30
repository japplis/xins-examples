<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 2.0//EN" "http://www.xins.org/dtd/function_2_0.dtd">

<function name="GetPersonDetails"
rcsversion="$Revision: 1.1 $" rcsdate="$Date: 2007/02/22 21:38:11 $">

	<description>Gets the detail of a person.</description>

	<input>
		<param name="id" required="true" type="_int32">
			<description>The ID of the person</description>
		</param>
	</input>
	<output>
		<param name="firstName" required="true" type="_text">
			<description>The first name of the person</description>
		</param>
		<param name="lastName" required="true" type="myproject/LastName">
			<description>The last name of the person</description>
		</param>
		<param name="age" required="false" type="_int32">
			<description>The age of the person</description>
		</param>
	</output>
</function>
