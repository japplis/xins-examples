<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.5//EN" "http://www.xins.org/dtd/function_1_5.dtd">

<function name="AddUser"
rcsversion="$Revision: 1.1 $" rcsdate="$Date: 2007/01/05 09:05:19 $">

	<description>Add the data of a new user.</description>

	<input>
		<param name="firstName" required="true" type="myproject/LastName">
			<description>The first name of the user.</description>
		</param>
		<param name="lastName" required="true" type="myproject/LastName">
			<description>The surname of the user.</description>
		</param>
	</input>
	<output>
		<param name="accountNumber" required="true" type="_int32">
			<description>The account number created for the user.</description>
		</param>
	</output>
</function>
