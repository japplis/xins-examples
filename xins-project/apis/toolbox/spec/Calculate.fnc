<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE function PUBLIC "-//XINS//DTD Function 2.0//EN" "http://www.xins.org/dtd/function_2_0.dtd">

<function name="Calculate"
rcsversion="$Revision: 1.1 $" rcsdate="$Date: 2007/01/24 15:40:36 $">

	<description>Make a mathematical calculation.</description>

	<input>
		<param name="calculation" required="true" type="Operation">
			<description>The simple calculation.</description>
		</param>
	</input>
	<output>
		<param name="result" required="true" type="_float64">
			<description>The result of the calculation.</description>
		</param>
	</output>
</function>
