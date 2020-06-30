<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 1.5//EN" "http://www.xins.org/dtd/function_1_5.dtd">

<function name="DeleteUser"
rcsversion="$Revision: 1.1 $" rcsdate="$Date: 2007/01/05 09:05:19 $">

	<description>Delete a user.</description>

	<input>
		<param name="accountNumber" required="true" type="_int32">
			<description>The account to delete.</description>
		</param>
	</input>
	<output>
		<resultcode-ref name="AccountNotFound" />
	</output>
</function>
